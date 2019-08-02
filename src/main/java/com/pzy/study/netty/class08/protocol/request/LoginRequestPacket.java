package com.pzy.study.netty.class08.protocol.request;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */

@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;


    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
