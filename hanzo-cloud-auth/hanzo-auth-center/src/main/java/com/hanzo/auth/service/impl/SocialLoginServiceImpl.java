package com.hanzo.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.hanzo.auth.config.param.JwtParamConfig;
import com.hanzo.auth.entity.SocialBindUser;
import com.hanzo.auth.entity.SysSocialUser;
import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.manager.UserManager;
import com.hanzo.auth.service.ISysSocialUserService;
import com.hanzo.auth.service.SocialLoginService;
import com.hanzo.common.api.CommonResult;
import com.hanzo.common.constant.GrantTypeConstant;
import com.hanzo.common.constant.ParamsConstant;
import com.hanzo.common.constant.SocialConstant;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.common.exception.HanZoException;
import com.hanzo.common.util.HanZoUtil;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author thy
 * @Date 2020/11/22 22:04
 * @Description:第三方登录服务类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SocialLoginServiceImpl implements SocialLoginService {

    private final AuthRequestFactory factory;
    private final ISysSocialUserService sysSocialUserService;
    private final UserManager userManager;
    private final JwtParamConfig jwtParamConfig;
    private final RedisClientDetailsService redisClientDetailsService;
    private final ResourceOwnerPasswordTokenGranter granter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CommonResult resolveLogin(String oauthType, AuthCallback callback) throws HanZoException {
        CommonResult commonResult = new CommonResult();
        AuthRequest authRequest = factory.get(oauthType);
        AuthResponse response = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(response));
        if (response.ok()){
            AuthUser authUser = (AuthUser) response.getData();
            SysSocialUser sysSocialUser =sysSocialUserService.selectBySocialInfo(authUser.getUuid(),authUser.getSource());
            if (sysSocialUser == null){
                commonResult.setMessage(SocialConstant.NOT_BIND);
                commonResult.setData(authUser);
            }else {
                SysUser sysUser = userManager.findSysUserByName(sysSocialUser.getUsername());
                if (sysUser == null) {
                    throw new HanZoException("系统中未找到与第三方账号对应的账户");
                }else {
                    OAuth2AccessToken oAuth2AccessToken = getOauth2AccessToken(sysUser);
                    commonResult.setMessage(SocialConstant.SOCIAL_LOGIN_SUCCESS);
                    commonResult.setData(oAuth2AccessToken);
                }
            }
        }else {
            throw new HanZoException(String.format("第三方登录失败，%s", response.getMsg()));
        }
        return commonResult;
    }

    @Override
    public OAuth2AccessToken bindLogin(SocialBindUser bindUser, AuthUser authUser) throws HanZoException {
        SysUser sysUser = userManager.findSysUserByName(bindUser.getBindUsername());
        if (sysUser == null || !passwordEncoder.matches(bindUser.getBindPassword(), sysUser.getPassword())) {
            throw new HanZoException("绑定系统账号失败，用户名或密码错误！");
        }
        this.saveSocialBindInfo(sysUser, authUser);
        return this.getOauth2AccessToken(sysUser);
    }

    @Override
    public OAuth2AccessToken signLogin(SocialBindUser registerUser, AuthUser authUser) throws HanZoException {
        SysUser sysUser = userManager.findSysUserByName(registerUser.getBindUsername());
        if (sysUser != null) {
            throw new HanZoException("该用户名已存在！");
        }
        String password = passwordEncoder.encode(registerUser.getBindPassword());
        SysUser newUser = this.userManager.registerUser(registerUser.getBindUsername(), password);
        this.saveSocialBindInfo(newUser, authUser);
        return this.getOauth2AccessToken(newUser);
    }

    @Override
    public void bind(SocialBindUser bindUser, AuthUser authUser) throws HanZoException {
        String username = bindUser.getBindUsername();
        if (isCurrentUser(username)) {
            SysSocialUser sysSocialUser = sysSocialUserService.selectBySocialInfo(authUser.getUuid(),authUser.getSource());
            if (sysSocialUser != null) {
                throw new HanZoException("绑定失败，该第三方账号已绑定" + sysSocialUser.getUsername() + "系统账户");
            }
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            this.saveSocialBindInfo(sysUser, authUser);
        } else {
            throw new HanZoException("绑定失败，您无权绑定别人的账号");
        }
    }

    @Override
    public void unbind(SocialBindUser bindUser, String oauthType) throws HanZoException {
        String username = bindUser.getBindUsername();
        if (isCurrentUser(username)) {
            sysSocialUserService.deleteByUserInfo(username, oauthType);
        } else {
            throw new HanZoException("解绑失败，您无权解绑别人的账号");
        }
    }

    @Override
    public List<SysSocialUser> findUserSocialBindInfo(String username) {
        return sysSocialUserService.findUserSocialBindInfo(username);
    }

    /**
     * 保存第三方系统与hanzo系统账号的绑定信息
     * @param sysUser
     * @param authUser
     */
    private void saveSocialBindInfo(SysUser sysUser, AuthUser authUser) {
        SysSocialUser sysSocialUser = SysSocialUser.builder()
                .username(sysUser.getUsername())
                .socialName(authUser.getSource())
                .socialUuid(authUser.getUuid())
                .socialUserName(authUser.getUsername())
                .socialNickName(authUser.getNickname())
                .socialImageUrl(authUser.getAvatar())
                .location(authUser.getLocation())
                .remake(authUser.getRemark())
                .build();
        sysSocialUserService.saveSysSocialUser(sysSocialUser);
    }

    /**
     * 生成token
     * @param user
     * @return
     * @throws HanZoException
     */
    private OAuth2AccessToken getOauth2AccessToken(SysUser user) throws HanZoException {
        final HttpServletRequest httpServletRequest = HanZoUtil.getHttpServletRequest();
        httpServletRequest.setAttribute(ParamsConstant.LOGIN_TYPE, SocialConstant.SOCIAL_LOGIN);
        String socialLoginClientId = jwtParamConfig.getSocialClientId();
        ClientDetails clientDetails = null;
        try {
            clientDetails = redisClientDetailsService.loadClientByClientId(socialLoginClientId);
        } catch (Exception e) {
            throw new HanZoException("获取第三方登录可用的Client失败");
        }
        if (clientDetails == null) {
            throw new HanZoException("未找到第三方登录可用的Client");
        }
        Map<String, String> requestParameters = new HashMap<>(5);
        requestParameters.put(ParamsConstant.GRANT_TYPE, GrantTypeConstant.PASSWORD);
        requestParameters.put(SocialConstant.USERNAME, user.getUsername());
        requestParameters.put(SocialConstant.PASSWORD, SocialConstant.setSocialLoginPassword());

        String grantTypes = String.join(StringConstants.COMMA, clientDetails.getAuthorizedGrantTypes());
        TokenRequest tokenRequest = new TokenRequest(requestParameters, clientDetails.getClientId(), clientDetails.getScope(), grantTypes);
        return granter.grant(GrantTypeConstant.PASSWORD, tokenRequest);
    }

    /**
     * 是否是当前用户
     * @param username
     * @return
     */
    private boolean isCurrentUser(String username) {
        //String currentUsername = HanZoUtil.getCurrentUsername();
        return StringUtils.equalsIgnoreCase(username, null);
    }
}
