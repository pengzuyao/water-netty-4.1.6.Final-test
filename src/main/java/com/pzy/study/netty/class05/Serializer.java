package com.pzy.study.netty.class05;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-07-31
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz , byte[] bytes);
}
