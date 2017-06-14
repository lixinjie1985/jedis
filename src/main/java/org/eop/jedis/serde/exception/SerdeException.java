package org.eop.jedis.serde.exception;

import org.eop.jedis.exception.RedisException;

/**
 * @author lixinjie
 * @since 2017-06-14
 */
public class SerdeException extends RedisException {

	private static final long serialVersionUID = -5091938900791451655L;

	public SerdeException(String message) {
        super(message);
    }
	
	public SerdeException(Throwable cause) {
        super(cause);
    }
	
	public SerdeException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
