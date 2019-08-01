package com.pzy.study.netty.class07.client;

import com.pzy.study.netty.class07.protocol.Packet07;
import com.pzy.study.netty.class07.protocol.PacketCodeC07;
import com.pzy.study.netty.class07.protocol.request.LoginRequestPacket07;
import com.pzy.study.netty.class07.protocol.response.LoginResponsePacket07;
import com.pzy.study.netty.class07.protocol.response.MessageResponsePacket07;
import com.pzy.study.netty.class07.util.LoginUtil07;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:23.
 */
public class ClientHandler07 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket07 loginRequestPacket = new LoginRequestPacket07();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        // 编码
        ByteBuf buffer = PacketCodeC07.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet07 packet = PacketCodeC07.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket07) {
            LoginResponsePacket07 loginResponsePacket = (LoginResponsePacket07) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
                LoginUtil07.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket07) {
            MessageResponsePacket07 messageResponsePacket = (MessageResponsePacket07) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
