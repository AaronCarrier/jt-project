package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jt.common.util.SysResult;
import cn.jt.pojo.User;
import cn.jt.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/check/{param}/{typeVal}")
	public SysResult check(@PathVariable String param, @PathVariable Integer typeVal) {
		Boolean b = userService.check(param, typeVal);
		return SysResult.ok(b);
	}
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult query(@PathVariable String ticket) {
		String userJson = userService.query(ticket);
		return SysResult.ok(userJson);
	}
	
	//不能直接利用浏览器方式，浏览器输入地址访问形式get请求，只能post请求，利用工具类，模拟post请求
	@RequestMapping("/user/register")
	public SysResult register( User user) {		//接收一个json字符串对象，底层内部Json传输
		String username = userService.register(user);
		return SysResult.ok(username);
	}
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password) {
		String ticket = userService.login(username, password);
		return SysResult.ok(ticket);
	}
}
