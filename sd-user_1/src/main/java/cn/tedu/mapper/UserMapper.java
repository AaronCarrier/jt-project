package cn.tedu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.tedu.pojo.User;

//mybatis持久层访问数据库CRUD操作
public interface UserMapper {
	//查询所有的方法
	@Select("select * from user")	//SQL语句
	public List<User> find();
}
