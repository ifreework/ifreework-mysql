package com.ifreework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 描述：序列化与反序列化    
 * @author：wangyh
 * @createDate：2017年5月18日
 * @modify：wangyh    
 * @modifyDate：2017年5月18日 
 * @version 1.0
 */
public class SerializeUtils {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);
	
	/**
	 * 反序列化
	 * @param object
	 * @return
	 */
	public static Object deserialize(byte[] bytes) {
		Object result = null;

		if (isEmpty(bytes)) {
			logger.debug("deserialize bytes is null !");
			return null;
		}
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);

			result = objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error("deserialize bytes is error! {}", e);
		} catch (IOException e) {
			logger.error("deserialize bytes is error! {}", e);
		}
		return result;
	}

	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		byte[] result = null;

		if (object == null) {
			return new byte[0];
		}
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
			if (!(object instanceof Serializable)) {
				throw new IllegalArgumentException(
						SerializeUtils.class.getSimpleName() + " requires a Serializable payload "
								+ "but received an object of type [" + object.getClass().getName() + "]");
			}
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			result = byteStream.toByteArray();
		} catch (Exception ex) {
			logger.error("Failed to serialize", ex);
		}
		return result;
	}
	
}
