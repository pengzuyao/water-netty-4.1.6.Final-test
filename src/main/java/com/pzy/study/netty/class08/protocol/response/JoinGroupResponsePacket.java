package com.pzy.study.netty.class08.protocol.response;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-07
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
