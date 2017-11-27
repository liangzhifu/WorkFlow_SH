package com.takata.common.shiro;

import org.apache.shiro.authc.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author lzf
 **/
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    public AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        if (password==null){
            password = "";
        }
        return new UsernamePasswordToken(username, password.toCharArray());
    }

    @Override
    public String getSuccessUrl() {
        return super.getSuccessUrl();
    }

}
