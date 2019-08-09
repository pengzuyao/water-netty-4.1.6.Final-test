package com.pzy.study.netty.class08.codec;

import com.pzy.study.netty.class08.protocol.Packet;
import com.pzy.study.netty.class08.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-08
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf , Packet> {

    private PacketCodecHandler(){}

    private static class Inner{
        private static PacketCodecHandler instance = new PacketCodecHandler();
    }

    public static PacketCodecHandler getInstance(){
        return Inner.instance;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.channel().alloc().buffer();
        PacketCodeC.getInstance().encode(buffer ,packet);
        out.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(PacketCodeC.getInstance().decode(byteBuf));
    }
}
