package com.pzy.study.netty.class05;

import com.alibaba.fastjson.JSON;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-07-31
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.Json;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes ,clazz);
    }
}
