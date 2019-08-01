package com.pzy.study.netty.class07.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.pzy.study.netty.class07.serialize.Serializer07;
import com.pzy.study.netty.class07.serialize.SerializerAlogrithm;


public class JSONSerializer07 implements Serializer07 {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
