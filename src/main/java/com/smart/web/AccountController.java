package com.smart.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.entity.User;
import com.smart.service.UserService;
import com.smart.util.JsonResult;
import com.smart.util.Utils;

@Controller
@RequestMapping("/account")
@Scope("prototype")
public class AccountController {

	@Resource(name = "userService")
	//@Resource(name="target")
	private UserService userService;

	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(String name, String password) {
			User user = userService.login(name, password);
			System.out.println(user);
			String pwd = Utils.md5salt(password);
			System.out.println(pwd);
			return new JsonResult<User>(user);
		
	}

}
