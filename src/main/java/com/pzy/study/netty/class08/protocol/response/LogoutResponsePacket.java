package com.pzy.study.netty.class08.protocol.response;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
