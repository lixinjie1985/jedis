package org.eop.jedis.serde;

/**
 * @author lixinjie
 * @since 2017-06-14
 */
public interface ISerde {

	<T> byte[] serialize(T object);
	
	<T> T deserialize(byte[] bytes);
}
