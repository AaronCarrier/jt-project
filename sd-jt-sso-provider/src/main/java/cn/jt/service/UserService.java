package cn.jt.service;

import cn.jt.pojo.User;

public interface UserService {
	public Boolean check(String param, Integer typeVal) ;
	public String query(String ticket);
	public String register(User user);
	public String login(String username, String password);
}
