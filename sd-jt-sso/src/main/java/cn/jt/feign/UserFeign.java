package cn.jt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jt.common.util.SysResult;
import cn.jt.sso.pojo.User;

@FeignClient("jt-sso-provider")
public interface UserFeign {
	@RequestMapping("/user/check/{param}/{typeVal}")
	public SysResult check(@PathVariable String param, @PathVariable Integer typeVal);
	
	@RequestMapping("/user/query/{ticket}")
	public SysResult query(@PathVariable String ticket) ;
	
	@RequestMapping("/user/register")
	public SysResult register(User user);
	
	@RequestMapping("/user/login")
	public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password) ;
}
