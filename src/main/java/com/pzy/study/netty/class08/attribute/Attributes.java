package com.pzy.study.netty.class08.attribute;

import com.pzy.study.netty.class08.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
