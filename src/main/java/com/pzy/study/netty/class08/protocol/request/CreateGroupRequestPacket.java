package com.pzy.study.netty.class08.protocol.request;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
