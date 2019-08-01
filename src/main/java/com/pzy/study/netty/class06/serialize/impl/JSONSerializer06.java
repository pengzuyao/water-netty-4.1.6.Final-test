package com.pzy.study.netty.class06.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.pzy.study.netty.class06.serialize.Serializer06;
import com.pzy.study.netty.class06.serialize.SerializerAlogrithm06;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
public class JSONSerializer06 implements Serializer06 {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm06.JSON;
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
