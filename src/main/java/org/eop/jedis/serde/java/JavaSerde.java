package org.eop.jedis.serde.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eop.jedis.serde.ISerde;
import org.eop.jedis.serde.exception.SerdeException;

/**
 * @author lixinjie
 * @since 2017-06-14
 */
public class JavaSerde implements ISerde {

	@Override
	public <T> byte[] serialize(T object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			throw new SerdeException(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			T object = (T)ois.readObject();
			return object;
		} catch (Exception e) {
			throw new SerdeException(e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					
				}
			}
		}
	}

}
