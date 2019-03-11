package com.takata.login;

import com.success.common.Constant;
import com.success.sys.menu.domain.UserMenu;
import com.success.sys.menu.query.UserMenuQuery;
import com.success.sys.module.domain.Module;
import com.success.sys.user.domain.User;
import com.success.sys.user.query.UserQuery;
import com.success.sys.webservice.service.AsmxClient;
import com.success.sys.webservice.service.serviceImpl.AsmxClientImpl;
import com.success.web.framework.util.ServletAPIUtil;
import com.takata.system.menu.service.SystemMenuService;
import com.takata.system.user.domain.SystemUser;
import com.takata.system.user.service.SystemUserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lzf
 **/
@Controller
public class LoginController {

    private Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Resource(name = "systemMenuService")
    private SystemMenuService systemMenuService;

    @RequestMapping("/login.do")
    public String login(){
        return "login/login";
    }

    /**
     * 登录认证
     * @return
     */
    @RequestMapping("/loginAuthenticate.do")
    @ResponseBody
    public Object loginAuthenticate(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(2);
        try {
            String userCode = request.getParameter("userName");
            String password = request.getParameter("password");
            SystemUser systemUser = new SystemUser();
            systemUser.setUserCode(userCode);
            boolean isExist = this.systemUserService.checkUserCode(systemUser);
            if(isExist){
                try {
                    Subject currentUser = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
                    currentUser.login(token);
                    map.put("loginState", "success");
                }catch (Exception e){
                    logger.error(e.getMessage());
                    map.put("loginState", "WrongPassword");
                }
            }else {
                map.put("loginState", "UnknowUser");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("loginState", "error");
        }
        return map;
    }

    @RequestMapping("/loginAuthenticateFromOA.do")
    public String loginAuthenticateFromOA(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model){
        String userCode = ServletAPIUtil.getStringParameter("account_staffid", request);
        System.out.println("UserCode:"+userCode);
        try {
            AsmxClient asmxClient = new AsmxClientImpl();
            String staffInfo = asmxClient.staffInfoService(userCode);
            System.out.println("staffInfo:"+staffInfo);
            if(staffInfo.length() > 20){
                UserQuery userQuery = new UserQuery();
                userQuery.setUserCode(userCode);
                SystemUser systemUser = new SystemUser();
                systemUser.setUserCode(userCode);
                systemUser = this.systemUserService.querySystemUserByUserCode(systemUser);
                if (systemUser != null) {
                    try {
                        Subject currentUser = SecurityUtils.getSubject();
                        UsernamePasswordToken token = new UsernamePasswordToken(userCode, systemUser.getUserPassword());
                        currentUser.login(token);
                        model.put("loginState", "success");
                    }catch (Exception e){
                        logger.error(e.getMessage());
                        model.put("loginState", "WrongPassword");
                    }
                }else {
                    model.put("loginState", "用户不存在！");
                    return "login/login";
                }
            }else {
                model.put("loginState", "系统警告：您没有权限登录！");
                return "login/login";
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return "login/login";
        }
        return "mainframe/mainframe";
    }

    @RequestMapping("/loginOut.do")
    public Object loginOut(){
        return "login/login";
    }

    @RequestMapping("/getMainframeDialog.do")
    public String getMainframeDialog(){
        return "mainframe/mainframe";
    }

}
