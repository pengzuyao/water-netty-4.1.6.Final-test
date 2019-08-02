package com.pzy.study.netty.class06.server;

import com.pzy.study.netty.class06.protocol.Packet06;
import com.pzy.study.netty.class06.protocol.PacketCodeC06;
import com.pzy.study.netty.class06.protocol.request.LoginRequestPacket06;
import com.pzy.study.netty.class06.protocol.response.LoginResponsePacket06;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
public class ServerHandler06 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 客户端开始登录……");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet06 packet06 = PacketCodeC06.getInstance().decode(requestByteBuf);

        if (packet06 instanceof LoginRequestPacket06) {
            // 登录流程
            LoginRequestPacket06 loginRequestPacket06 = (LoginRequestPacket06) packet06;

            LoginResponsePacket06 loginResponsePacket06 = new LoginResponsePacket06();
            loginResponsePacket06.setVersion(packet06.getVersion());
            if (valid(loginRequestPacket06)) {
                loginResponsePacket06.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket06.setReason("账号密码校验失败");
                loginResponsePacket06.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC06.getInstance().encode(ctx.alloc(), loginResponsePacket06);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket06 loginRequestPacket06) {
        return true;
    }
}
