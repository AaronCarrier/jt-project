package cn.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jt.mapper")		//包扫描所有接口
public class JtSsoProviderRunApp {
	public static void main(String[] args) {
		SpringApplication.run(JtSsoProviderRunApp.class, args);
	}
}
