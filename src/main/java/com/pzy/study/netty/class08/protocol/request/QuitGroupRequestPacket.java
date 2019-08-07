package com.pzy.study.netty.class08.protocol.request;


import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-07
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
