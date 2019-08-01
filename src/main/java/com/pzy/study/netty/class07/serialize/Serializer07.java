package com.pzy.study.netty.class07.serialize;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.pzy.study.netty.class07.serialize.impl.JSONSerializer07;

public interface Serializer07 {

    Serializer07 DEFAULT = new JSONSerializer07();

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
