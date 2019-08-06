package com.pzy.study.netty.class08.protocol.response;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
