package com.success.sys.fun.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.success.sys.fun.domain.UserFun;
import com.success.sys.fun.query.UserFunQuery;
import com.success.sys.fun.service.FunService;
import com.success.web.framework.exception.DaoException;
import com.success.web.framework.exception.ServiceException;
import com.success.web.framework.util.AjaxUtil;
import com.success.web.framework.util.JSONUtil;

@Controller
@RequestMapping("/fun")
public class FunController {

	@Resource(name = "funService")
	private FunService funService;

	@RequestMapping("/queryUserFun.do")
	public void queryUserFun(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询菜单权限>>>>>>>");
		try {
			UserFunQuery query = funService.getUserFunQuery(request);
			System.out.println("UserFunQuery:" + query.toString());
			List<UserFun> userFunList = funService.selectUserFun(query);
			System.out.println(userFunList.toString());
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(userFunList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/queryFunByUserId.do")
	public void queryFunByUserId(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("查询菜单权限>>>>>>>");
		try {
			UserFunQuery query = funService.getUserFunQuery(request);
			System.out.println("UserFunQuery:" + query.toString());
			List<UserFun> userFunList = funService.selectFunByUserId(query);
			System.out.println(userFunList.toString());
			AjaxUtil.ajaxResponse(response, JSONUtil
					.getJSONObject(userFunList).toString(),
					AjaxUtil.RESPONCE_TYPE_TEXT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("/addUserFun.do")
	public void addUserFun(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("增加菜单权限>>>>>>>");
		String funIdArray = request.getParameter("funIdArray");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String[] arrFunId = null;
		if (funIdArray != null && !funIdArray.equals("")) {
			arrFunId = funIdArray.split(",");
		}
		String jsonStr = "";
		if (arrFunId.length > 0) {

			for (int i = 0; i < arrFunId.length; i++) {
				UserFun userFun = new UserFun();
				userFun.setFunId(Integer.parseInt(arrFunId[i]));
				userFun.setUserId(userId);
				funService.insertUserFun(userFun);
			}
		}
		jsonStr = "{'success':true,'message':'增加成功'}";
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/delUserFun.do")
	public void delUserfun(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		System.out.println("删除菜单权限>>>>>>>");
		String funIdArray = request.getParameter("funIdArray");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String[] arrFunId = null;
		if (funIdArray != null && !funIdArray.equals("")) {
			arrFunId = funIdArray.split(",");
		}
		String jsonStr = "";
		int r_i=0;
		if (arrFunId.length > 0) {

			for (int i = 0; i < arrFunId.length; i++) {
				UserFun userFun = new UserFun();
				userFun.setFunId(Integer.parseInt(arrFunId[i]));
				userFun.setUserId(userId);
				r_i += funService.deleteUserFun(userFun);
			}
		}
		if(r_i==arrFunId.length){
			jsonStr = "{'success':true,'message':'删除成功'}";
		}else{
			jsonStr = "{'success':false,'message':'删除失败'}";
		}
			
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}