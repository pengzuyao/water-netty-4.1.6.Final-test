package com.pzy.study.netty.class08.protocol.request;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
