package org.eop.jedis;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lixinjie
 * @since 2017-06-13
 */
public class DefaultRedis extends AbstractRedis {

	@Override
	protected JedisPoolConfig buildJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		applyPropertiesToJedisPoolConfig(poolConfig);
		return poolConfig;
	}

	@Override
	protected void initializeJedisCluster() {
		jedisCluster = new JedisCluster(buildHostAndPortSet(), getConnectionTimeout(), getSoTimeout(), getMaxAttempts(), getPassword(), buildJedisPoolConfig());
	}

}
