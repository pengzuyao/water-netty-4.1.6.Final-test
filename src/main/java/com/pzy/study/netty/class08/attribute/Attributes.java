package com.pzy.study.netty.class08.attribute;

import io.netty.util.AttributeKey;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
