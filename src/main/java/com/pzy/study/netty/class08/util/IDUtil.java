package com.pzy.study.netty.class08.util;

import java.util.UUID;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
