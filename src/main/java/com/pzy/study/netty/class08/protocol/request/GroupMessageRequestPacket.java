package com.pzy.study.netty.class08.protocol.request;


import static com.pzy.study.netty.class08.protocol.command.Command.*;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
