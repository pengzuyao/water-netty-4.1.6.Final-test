package com.pzy.study.netty.class08.server.handler;

import com.pzy.study.netty.class08.protocol.request.MessageRequestPacket;
import com.pzy.study.netty.class08.protocol.response.MessageResponsePacket;
import com.pzy.study.netty.class08.session.Session;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //1.拿到消息发送方的会话消息
        Session session = SessionUtil.getSession(ctx.channel());

        //2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        //3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        //将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else{
            System.out.println("[" + messageRequestPacket.getToUserId() + "]不在线 ，发送失败！");
        }
    }
}
