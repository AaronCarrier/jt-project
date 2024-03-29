package util;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

//SpringBoot 2.x 用lettuce替代掉了jedis
public class TestRedis {
	public static void main(String[] args) {
		//设置连接服务器IP地址和访问端口
		RedisClient client = RedisClient.create("redis://192.168.163.128:6379");
		StatefulRedisConnection<String, String> connect = client.connect();
		RedisCommands<String, String> commands = connect.sync(); 
		 
		commands.set("jt-ticket", "lasjdfoaijefo;ijae;ofijaoweijf;oaijsf;oawiejfo;aije;ij");
		String s = commands.get("jt-ticket");
		System.out.println(s);
	}
}
