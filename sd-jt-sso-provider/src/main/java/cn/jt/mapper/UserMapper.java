package cn.jt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.jt.pojo.User;

public interface UserMapper {
	//#会自动给两边加单撇，$原样输出
	//SELECT COUNT(*) FROM tb_user WHERE username = 'tonychen'
	@Select("select count(*) from tb_user where ${type}=#{param}")
	public int check(String param, String type);
	
	//新增
	@Insert("insert into tb_user (username, password, phone, email, created, updated)"
			+ " values(#{username}, #{password}, #{phone}, #{email}, #{created}, #{updated})")
	public void insert(User user) ;
	
	//查询用户是否是系统的用户
	@Select("select * from tb_user where username=#{username}")
	public User login(String username);
}
