package com.pzy.study.netty.class05;

import lombok.Data;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-07-31
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
