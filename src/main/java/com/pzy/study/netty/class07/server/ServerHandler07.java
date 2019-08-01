package com.pzy.study.netty.class07.server;

import com.pzy.study.netty.class07.protocol.Packet07;
import com.pzy.study.netty.class07.protocol.PacketCodeC07;
import com.pzy.study.netty.class07.protocol.request.LoginRequestPacket07;
import com.pzy.study.netty.class07.protocol.request.MessageRequestPacket07;
import com.pzy.study.netty.class07.protocol.response.LoginResponsePacket07;
import com.pzy.study.netty.class07.protocol.response.MessageResponsePacket07;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author chao.yu
 * chao.yu@dianping.com
 * @date 2018/08/04 06:21.
 */
public class ServerHandler07 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet07 packet = PacketCodeC07.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket07) {
            System.out.println(new Date() + ": 收到客户端登录请求……");
            // 登录流程
            LoginRequestPacket07 loginRequestPacket = (LoginRequestPacket07) packet;

            LoginResponsePacket07 loginResponsePacket = new LoginResponsePacket07();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC07.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket07) {
            // 客户端发来消息
            MessageRequestPacket07 messageRequestPacket = ((MessageRequestPacket07) packet);

            MessageResponsePacket07 messageResponsePacket = new MessageResponsePacket07();
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC07.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket07 loginRequestPacket) {
        return true;
    }
}
