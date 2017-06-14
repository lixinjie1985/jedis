package org.eop.jedis.exception;

/**
 * @author lixinjie
 * @since 2017-06-14
 */
public class RedisException extends RuntimeException {

	private static final long serialVersionUID = -7495976256848137319L;

	public RedisException(String message) {
        super(message);
    }
	
	public RedisException(Throwable cause) {
        super(cause);
    }
	
	public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
