package com.hanzo.auth.service.impl;

import com.hanzo.auth.entity.HanZoAuthUser;
import com.hanzo.auth.entity.SysUser;
import com.hanzo.auth.manager.UserManager;
import com.hanzo.common.constant.AuthConstants;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.ParamsConstant;
import com.hanzo.common.constant.SocialConstant;
import com.hanzo.common.util.HanZoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/11 16:10
 * @Description:
 */
@Slf4j
@Service("userDetailsService")
public class HanZoUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpServletRequest httpServletRequest = HanZoUtil.getHttpServletRequest();
        SysUser sysUser = userManager.findSysUserByName(username);
        if (sysUser != null) {
            String permissions = userManager.findUserPermissions(sysUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(CommonConstants.STATUS_VALID, sysUser.getStatus())) {
                notLocked = true;
            }
            String password = sysUser.getPassword();
            String loginType = (String) httpServletRequest.getAttribute(ParamsConstant.LOGIN_TYPE);
            if (StringUtils.equals(loginType, SocialConstant.SOCIAL_LOGIN)) {
                password = passwordEncoder.encode(SocialConstant.getSocialLoginPassword());
            }

            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.NO_AUTHORITIES;
            if (StringUtils.isNotBlank(permissions)) {
                grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissions);
            }
            sysUser.setType(AuthConstants.LOGIN_USERNAME_TYPE);
            HanZoAuthUser authUser = new HanZoAuthUser(sysUser.getUserId(),sysUser.getType(),sysUser.getMobile(),sysUser.getRoleId(),sysUser.getDeptName(),sysUser.getAvatar(),sysUser.getUsername(), password, true, true, true, notLocked,
                    grantedAuthorities);
           /* HanZoAuthUser authUser = new HanZoAuthUser(sysUser.getUsername(), password, true, true, true, notLocked,
                    grantedAuthorities);*/
            //BeanUtils.copyProperties(sysUser, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
