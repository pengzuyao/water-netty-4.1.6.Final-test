package com.pzy.study.netty.class06.protocol.request;

import com.pzy.study.netty.class06.protocol.Packet06;
import lombok.Data;

import static com.pzy.study.netty.class06.protocol.command.Command06.LOGIN_REQUEST;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
@Data
public class LoginRequestPacket06 extends Packet06 {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
