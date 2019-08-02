package com.pzy.study.netty.class08.serialize;

import com.pzy.study.netty.class08.serialize.impl.JSONSerializer;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
