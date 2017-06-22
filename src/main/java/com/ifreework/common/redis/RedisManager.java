package com.ifreework.common.redis;

import java.io.Serializable;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 描述：Redis管理类
 * @author：wangyh
 * @createDate：2017年5月18日
 * @modify：wangyh    
 * @modifyDate：2017年5月18日 
 * @version 1.0
 */
public class RedisManager implements Serializable {

	private static final long serialVersionUID = 5721072650603869145L;

	private String host = "127.0.0.1"; // redis地址

	private int port = 6379; // 端口号

	private int expire = 0; // 超时时间，0永不超时

	private int timeout = 0; // 链接超时时长，0永不超时

	private String password = ""; // 链接密码

	private static JedisPool jedisPool = null;

	public RedisManager() {

	}
	

	/**
	 * 
	 * 创建一个新的实例 RedisManager，并初始化Redis连接池
	 * @param host redis地址
	 * @param port redis端口号
	 * @param expire 全局缓存超时时间
	 * @param timeout  连接超时时间
	 * @param password  redis密码
	 */
	public RedisManager(String host, int port, int expire, int timeout, String password) {
		this.expire = expire;
		if (jedisPool == null) {
			jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password);
		}
	}
	/**
	 * 
	 * 创建一个新的实例 RedisManager，并初始化Redis连接池
	 * @param host redis地址
	 * @param port redis端口号
	 * @param expire 全局缓存超时时间
	 * @param timeout  连接超时时间
	 */
	public RedisManager(String host, int port, int expire, int timeout) {
		this.expire = expire;
		jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
	}

	/**
	 * 
	 * 创建一个新的实例 RedisManager，并初始化Redis连接池
	 * @param host redis地址
	 * @param port redis端口号
	 * @param expire 全局缓存超时时间
	 */
	public RedisManager(String host, int port, int expire) {
		this.expire = expire;
		jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
	}

	/**
	 * 描述：获取jedis
	 * @return jedis
	 */
	public Jedis getJedis(){
		return jedisPool.getResource();
	}

	/**
	 * 
	 * 描述:通过Key从redis中获取对象
	 * @param 
	 * 			key 序列化后的key
	 * @return   
	 * 			序列化后的值
	 */
	public byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try {
			value = jedis.get(key);
		} finally {
			jedis.close();
		}
		return value;
	}

	/**
	 * 
	 * 描述：采用全局超时设定，将序列化后的对象以key : value的形式存入redis中
	 * @param key 序列化后的key
	 * @param value 序列化的value
	 * @return
	 */
	public String set(byte[] key, byte[] value) {
		Jedis jedis = jedisPool.getResource();
		String result;
		try {
			result = jedis.set(key, value);
			if (this.expire != 0) {
				jedis.expire(key, this.expire);
			}
		} finally {
			jedis.close();
		}

		return result;
	}

	/**
	 * 
	 * 描述：将序列化后的对象以key : value的形式存入redis中，并设定超时时间
	 * @param key  序列化后的key
	 * @param value 序列化后的value
	 * @param expire  超时时间  0永不超时
	 * @return 
	 */
	public String set(byte[] key, byte[] value, int expire) {
		Jedis jedis = jedisPool.getResource();
		String result;
		try {
			result = jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} finally {
			jedis.close();
		}
		return result;
	}

	/**
	 * 
	 * 描述：根据key删除对象
	 * @param key 序列化后的key
	 * @return 删除的数据条数 
	 */
	public Long del(byte[] key) {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.del(key);
		} finally {
			jedis.close();
		}
	}

	/**
	 * 
	 * 描述：清楚redis中所有数据
	 * @return 
	 */
	public String flushDB() {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.flushDB();
		} finally {
			jedis.close();
		}
	}


	/**
	 * 
	 * 描述：获取redis中key的数量
	 * @return redis中key的数量
	 */
	public Long dbSize() {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.dbSize();
		} finally {
			jedis.close();
		}
	}

	/**
	 * 描述：获取redis中所有的key
	 * @param regex 用于验证的正则表达式
	 * @return 获取key的集合
	 */
	public Set<byte[]> keys(String pattern) {
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try {
			keys = jedis.keys(pattern.getBytes());
		} finally {
			jedis.close();
		}
		return keys;
	}

	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
