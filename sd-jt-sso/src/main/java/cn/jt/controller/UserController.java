package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jt.common.util.SysResult;
import cn.jt.feign.UserFeign;
import cn.jt.sso.pojo.User;

@RestController
public class UserController {
	@Autowired
	private UserFeign userFeign;
	
	@RequestMapping("/user/check/{param}/{typeVal}")
	public SysResult check(@PathVariable String param,@PathVariable Integer typeVal) {
		return userFeign.check(param, typeVal);
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult query(@PathVariable String ticket) {
		return userFeign.query(ticket);
	}
	
	@RequestMapping("/user/register")
	public SysResult register(User user) {
		return userFeign.register(user);
	}
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username,@RequestParam("p")  String password) {
		return userFeign.login(username, password);
	}
}
