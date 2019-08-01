package com.pzy.study.netty.class07.protocol.request;

import com.pzy.study.netty.class07.protocol.Packet07;
import lombok.Data;

import static com.pzy.study.netty.class07.protocol.command.Command07.LOGIN_REQUEST;

@Data
public class LoginRequestPacket07 extends Packet07 {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
