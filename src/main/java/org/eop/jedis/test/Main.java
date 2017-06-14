package org.eop.jedis.test;

import org.eop.jedis.DefaultRedis;
import org.eop.jedis.serde.ISerde;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

/**
 * @author lixinjie
 * @since 2017-06-13
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		DefaultRedis dr = (DefaultRedis)ac.getBean("redis");
		ISerde serde = dr.getSerde();
		JedisCluster jc = dr.getJedisCluster();
		String result = jc.set("lixinjie".getBytes(), serde.serialize(new User("李新杰", "000000")));
		System.out.println(result);
		
		User lxj = serde.deserialize(jc.get("lixinjie".getBytes()));
		System.out.println(lxj);
		
		System.out.println(jc.del("lixinjie".getBytes()));
		
		System.out.println(jc.exists("lixinjie".getBytes()));
	}

}
