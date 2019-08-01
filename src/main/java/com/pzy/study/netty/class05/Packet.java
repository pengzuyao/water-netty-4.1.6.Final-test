package com.pzy.study.netty.class05;

import lombok.Data;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/07/31
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
