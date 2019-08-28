package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer					//标识这是一个EurekaServer
public class EurekaServerRunApp {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerRunApp.class, args);
	}

}
