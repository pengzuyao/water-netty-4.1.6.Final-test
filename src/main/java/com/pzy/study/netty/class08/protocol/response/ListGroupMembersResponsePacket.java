package com.pzy.study.netty.class08.protocol.response;

import com.pzy.study.netty.class08.protocol.Packet;
import com.pzy.study.netty.class08.session.Session;
import lombok.Data;

import java.util.List;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-07
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
