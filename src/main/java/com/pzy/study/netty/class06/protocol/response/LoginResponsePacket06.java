package com.pzy.study.netty.class06.protocol.response;

import com.pzy.study.netty.class06.protocol.Packet06;
import lombok.Data;

import static com.pzy.study.netty.class06.protocol.command.Command06.LOGIN_RESPONSE;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */

@Data
public class LoginResponsePacket06 extends Packet06 {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
