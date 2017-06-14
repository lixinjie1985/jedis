package org.eop.jedis;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.eop.jedis.serde.ISerde;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author lixinjie
 * @since 2017-06-13
 */
public abstract class AbstractRedis implements IRedis {

	//redis.clients.jedis.JedisCluster
	private String clusterNodes;
	private Integer connectionTimeout = 2000;
	private Integer soTimeout = 2000;
    private Integer maxAttempts = 5;
    private String password;
    //org.apache.commons.pool2.impl.BaseObjectPoolConfig
    private Boolean lifo;
    private Boolean fairness;
    private Long maxWaitMillis;
    private Long minEvictableIdleTimeMillis;
    private Long softMinEvictableIdleTimeMillis;
    private Integer numTestsPerEvictionRun;
    private String evictionPolicyClassName;
    private Boolean testOnCreate;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean testWhileIdle;
    private Long timeBetweenEvictionRunsMillis;
    private Boolean blockWhenExhausted;
    //org.apache.commons.pool2.impl.GenericObjectPoolConfig
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    //
    private Map<String, ISerde> serdeMap;
    private String defaultSerde;
    
    protected JedisCluster jedisCluster;
    
    protected abstract GenericObjectPoolConfig buildGenericObjectPoolConfig();
    
    protected abstract void initializeJedisCluster();
    
    protected Set<HostAndPort> buildHostAndPortSet() {
    	String[] nodes = clusterNodes.split(",");
    	Set<HostAndPort> hps = new HashSet<>();
    	for (String node : nodes) {
    		hps.add(HostAndPort.parseString(node));
    	}
    	return hps;
    }
    
    protected void applyPropertiesToGenericObjectPoolConfig(GenericObjectPoolConfig poolConfig) {
    	String[] properties = {"lifo", "fairness", "maxWaitMillis", "minEvictableIdleTimeMillis", "softMinEvictableIdleTimeMillis", "numTestsPerEvictionRun", "evictionPolicyClassName", "testOnCreate", "testOnBorrow", "testOnReturn", "testWhileIdle", "timeBetweenEvictionRunsMillis", "blockWhenExhausted", "maxTotal", "maxIdle", "minIdle"};
    	for (String property : properties) {
    		try {
				Object value = PropertyUtils.getProperty(this, property);
				if (value != null) {
					PropertyUtils.setProperty(poolConfig, property, value);
				}
			} catch (Exception e) {
				
			}
    	}
    }
    
    public ISerde getSerde() {
    	return getSerde(getDefaultSerde());
    }
    
    public ISerde getSerde(String name) {
    	return getSerdeMap().get(name);
    }

	public String getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public Integer getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(Integer soTimeout) {
		this.soTimeout = soTimeout;
	}

	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLifo() {
		return lifo;
	}

	public void setLifo(Boolean lifo) {
		this.lifo = lifo;
	}

	public Boolean getFairness() {
		return fairness;
	}

	public void setFairness(Boolean fairness) {
		this.fairness = fairness;
	}

	public Long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public Long getSoftMinEvictableIdleTimeMillis() {
		return softMinEvictableIdleTimeMillis;
	}

	public void setSoftMinEvictableIdleTimeMillis(Long softMinEvictableIdleTimeMillis) {
		this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
	}

	public Integer getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public String getEvictionPolicyClassName() {
		return evictionPolicyClassName;
	}

	public void setEvictionPolicyClassName(String evictionPolicyClassName) {
		this.evictionPolicyClassName = evictionPolicyClassName;
	}

	public Boolean getTestOnCreate() {
		return testOnCreate;
	}

	public void setTestOnCreate(Boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Boolean getBlockWhenExhausted() {
		return blockWhenExhausted;
	}

	public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Map<String, ISerde> getSerdeMap() {
		return serdeMap;
	}

	public void setSerdeMap(Map<String, ISerde> serdeMap) {
		this.serdeMap = serdeMap;
	}

	public String getDefaultSerde() {
		return defaultSerde;
	}

	public void setDefaultSerde(String defaultSerde) {
		this.defaultSerde = defaultSerde;
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
    
}
