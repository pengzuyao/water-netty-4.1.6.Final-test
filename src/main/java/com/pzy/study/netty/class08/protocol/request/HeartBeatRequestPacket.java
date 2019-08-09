package com.pzy.study.netty.class08.protocol.request;


import com.pzy.study.netty.class08.protocol.Packet;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
