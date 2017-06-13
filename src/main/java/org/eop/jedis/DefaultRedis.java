package org.eop.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisCluster;

/**
 * @author lixinjie
 * @since 2017-06-13
 */
public class DefaultRedis extends AbstractRedis {

	@Override
	protected GenericObjectPoolConfig buildGenericObjectPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		applyPropertiesToGenericObjectPoolConfig(poolConfig);
		return poolConfig;
	}

	@Override
	protected void initializeJedisCluster() {
		jedisCluster = new JedisCluster(buildHostAndPortSet(), getConnectionTimeout(), getSoTimeout(), getMaxAttempts(), getPassword(), buildGenericObjectPoolConfig());
	}

}
