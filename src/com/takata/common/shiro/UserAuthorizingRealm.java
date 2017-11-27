package com.takata.common.shiro;

import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.service.SystemUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author lzf
 **/

public class UserAuthorizingRealm extends AuthorizingRealm {

    private Logger logger = LogManager.getLogger(UserAuthorizingRealm.class.getName());

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal)this.getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userCode = token.getUsername();
        SystemUser systemUser = new SystemUser();
        systemUser.setUserCode(userCode);
        systemUser = this.systemUserService.querySystemUserByUserCode(systemUser);
        if(systemUser.getId() != null){
            byte[] salt = systemUser.getUserPassword().getBytes();
            return new SimpleAuthenticationInfo(new Principal(systemUser), systemUser.getUserPassword(), ByteSource.Util.bytes(salt), this.getName());
        }else {
            return null;
        }
    }

}
