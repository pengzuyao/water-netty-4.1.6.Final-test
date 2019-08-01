package com.pzy.study.netty.class06.serialize;

import com.pzy.study.netty.class06.serialize.impl.JSONSerializer06;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
public interface Serializer06 {

    Serializer06 DEFAULT = new JSONSerializer06();

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
