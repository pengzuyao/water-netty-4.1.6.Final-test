package com.pzy.study.netty.class07.protocol.response;

import com.pzy.study.netty.class07.protocol.Packet07;
import lombok.Data;
import static com.pzy.study.netty.class07.protocol.command.Command07.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket07 extends Packet07 {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
