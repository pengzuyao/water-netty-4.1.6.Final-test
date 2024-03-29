package com.pzy.study.netty.class08.protocol.request;

import com.pzy.study.netty.class08.protocol.Packet;
import lombok.Data;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
