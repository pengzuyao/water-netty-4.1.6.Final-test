package com.pzy.study.netty.class07.protocol.request;

import com.pzy.study.netty.class07.protocol.Packet07;
import lombok.Data;

import static com.pzy.study.netty.class07.protocol.command.Command07.*;

@Data
public class MessageRequestPacket07 extends Packet07 {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
