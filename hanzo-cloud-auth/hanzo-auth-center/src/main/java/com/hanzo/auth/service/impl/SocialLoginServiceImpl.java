package com.hanzo.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.hanzo.auth.config.param.JwtParamConfig;
import com.hanzo.auth.entity.SocialBindUser;
import com.hanzo.auth.entity.SocialUserAuth;
import com.hanzo.auth.entity.SysSocialUser;
import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.manager.UserManager;
import com.hanzo.auth.service.ISocialUserAuthService;
import com.hanzo.auth.service.ISysSocialUserService;
import com.hanzo.auth.service.SocialLoginService;
import com.hanzo.auth.util.CustomTokenUtil;
import com.hanzo.auth.vo.AccessTokenVo;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    private final ISocialUserAuthService socialUserAuthService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult resolveLogin(String oauthType, AuthCallback callback){
        CommonResult commonResult = new CommonResult();
        AuthRequest authRequest = factory.get(oauthType);
        AuthResponse response = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(response));
        AccessTokenVo accessTokenVo;
        if (response.ok()){
            AuthUser authUser = (AuthUser) response.getData();
            SysSocialUser sysSocialUser =sysSocialUserService.selectBySocialInfo(authUser.getUuid(),authUser.getSource());
            if (sysSocialUser == null){
                //如果没有这个第三方账户 需要先保存下这个第三方账户
                sysSocialUser= this.saveSocialInfo(authUser);
                accessTokenVo = getTokenInfoBySocialLogin(sysSocialUser,authUser);
            }else {
                accessTokenVo = getTokenInfoBySocialLogin(sysSocialUser,authUser);
            }
        }else {
            throw new HanZoException(String.format("第三方登录失败，%s", response.getMsg()));
        }
        commonResult.setMessage(SocialConstant.SOCIAL_LOGIN_SUCCESS);
        commonResult.setData(accessTokenVo);
        return commonResult;
    }

    /**
     * 第三方登录验证后 根据信息生成token
     * @param sysSocialUser
     * @param authUser
     * @return
     */
    private AccessTokenVo getTokenInfoBySocialLogin(SysSocialUser sysSocialUser, AuthUser authUser) {
        AccessTokenVo accessTokenVo;
        //然后查询social_user_auth 表看有无关联信息
        SocialUserAuth socialUserAuth = socialUserAuthService.getBySocialUserId(sysSocialUser.getSocialUserId());
        if (socialUserAuth == null){
            //如果等于空说明这个第三方账号没有与本地账号关联 需要先自动创建一个本地账号 给一个注册账号的角色
            SysUser sysUser = userManager.createLocalUser(authUser);
            //然后social_user_auth 插入一条第三方账号与本地账号关联的信息
            if (sysUser == null) {
                throw new HanZoException("系统中未找到与第三方账号对应的账户");
            }else {
                socialUserAuthService.addSocialUserAuth(sysSocialUser.getSocialUserId(),sysUser.getUserId());
                OAuth2AccessToken oAuth2AccessToken = getOauth2AccessToken(sysUser);
                accessTokenVo = CustomTokenUtil.custom(oAuth2AccessToken);
            }
        }else {
            //如果有关联账号 继续查出来关联的本地账号的信息  --这种情况 基本不可能存在 没有第三方账号 基本不可能有关联账号
            SysUser sysUser = userManager.findSysUserByUserId(socialUserAuth.getUserId());
            if (sysUser == null) {
                throw new HanZoException("系统中未找到与第三方账号对应的账户");
            }else {
                OAuth2AccessToken oAuth2AccessToken = getOauth2AccessToken(sysUser);
                accessTokenVo = CustomTokenUtil.custom(oAuth2AccessToken);
            }
        }
        return accessTokenVo;
    }

    /**
     * 保存第三方系统信息
     * @param authUser
     */
    private SysSocialUser saveSocialInfo(AuthUser authUser) {
        SysSocialUser sysSocialUser = SysSocialUser.builder()
                .socialName(authUser.getSource())
                .socialUuid(authUser.getUuid())
                .socialUserName(authUser.getUsername())
                .socialNickName(authUser.getNickname())
                .socialImageUrl(authUser.getAvatar())
                .location(authUser.getLocation())
                .remake(authUser.getRemark())
                .build();
        sysSocialUserService.saveSysSocialUser(sysSocialUser);
        return sysSocialUser;

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


    /*@Override
    public OAuth2AccessToken bindLogin(SocialBindUser bindUser, AuthUser authUser) throws HanZoException {
        SysUser sysUser = userManager.findSysUserByName(bindUser.getBindUsername());
        if (sysUser == null || !passwordEncoder.matches(bindUser.getBindPassword(), sysUser.getPassword())) {
            throw new HanZoException("绑定系统账号失败，用户名或密码错误！");
        }
        //this.saveSocialBindInfo(sysUser, authUser);
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
        //this.saveSocialBindInfo(newUser, authUser);
        return this.getOauth2AccessToken(newUser);
    }*/
}
