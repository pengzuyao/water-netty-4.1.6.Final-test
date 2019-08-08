package com.pzy.study.netty.class08.protocol.response;


import com.pzy.study.netty.class08.protocol.Packet;
import com.pzy.study.netty.class08.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
