package cn.jt.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.jt.common.service.RedisService;
import cn.jt.mapper.UserMapper;
import cn.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService{
	private static final ObjectMapper MAPPER = new ObjectMapper();	//转换的工具类
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;

	public Boolean check(String param, Integer typeVal) {
		//页面类型值，转成对应的字段
		String typeName = "";
		if(typeVal==1) {
			typeName = "username";
		}else if(typeVal == 2) {
			typeName = "phone";
		}else if(typeVal == 3) {
			typeName = "email";
		}
		
		int count = userMapper.check(param, typeName);
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}

	public String query(String ticket) {
		//到redis中查询，如果查询到返回这个值，没查到空串
		String userJson = redisService.get(ticket);

		return userJson;
	}

	public String register(User user) {
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		
		//密码加密，md5
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		
		userMapper.insert(user);
		return user.getUsername();
	}

	public String login(String username, String password) {
		User user = userMapper.login(username);	 //通常习惯只使用用户名去查询
		if(user!=null) {		//如果查询到用户
			//还要进行密码的比较
			password = DigestUtils.md5Hex(password);
			if(user.getPassword().equals(password)) {
				//这就是系统用户，写redis（key,value)
				//把Java序列化json，把user对象转成json字符串
				try {
					user.setPassword(null);
					String userJson = MAPPER.writeValueAsString(user);
					//三个要求：唯一性、动态性、混淆性
					String ticket = DigestUtils.md5Hex("JT_TICKET_" + System.currentTimeMillis() + username);
					
					System.out.println(ticket);
					
					//存入redis中，过期时间是7天
					redisService.set(ticket, userJson, 60*60*24*7 );	//不用，javac编译优化，它自动算出结果
					
					return ticket;
				}catch(Exception e) {
					
				}
			}
		}
		return null;
	}

}
