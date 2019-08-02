package com.pzy.study.netty.class08.codec;

import com.pzy.study.netty.class08.protocol.Packet;
import com.pzy.study.netty.class08.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.getInstance().encode(byteBuf ,packet);
    }
}
