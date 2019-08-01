package com.pzy.study.netty.class06.client;

import com.pzy.study.netty.class06.protocol.Packet06;
import com.pzy.study.netty.class06.protocol.PacketCodeC06;
import com.pzy.study.netty.class06.protocol.request.LoginRequestPacket06;
import com.pzy.study.netty.class06.protocol.response.LoginResponsePacket06;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
public class ClientHandler06 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket06 loginRequestPacket06 = new LoginRequestPacket06();
        loginRequestPacket06.setUserId(UUID.randomUUID().toString());
        loginRequestPacket06.setUsername("pzy");
        loginRequestPacket06.setPassword("123456");
        // 编码
        ByteBuf buffer = PacketCodeC06.Instance.getInstance.encode(ctx.alloc(), loginRequestPacket06);
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet06 packet06 = PacketCodeC06.Instance.getInstance.decode(byteBuf);

        if (packet06 instanceof LoginResponsePacket06) {
            LoginResponsePacket06 loginResponsePacket06 = (LoginResponsePacket06) packet06;

            if (loginResponsePacket06.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket06.getReason());
            }
        }
    }
}
