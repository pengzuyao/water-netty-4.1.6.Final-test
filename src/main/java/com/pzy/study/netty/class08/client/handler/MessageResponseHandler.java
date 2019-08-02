package com.pzy.study.netty.class08.client.handler;

import com.pzy.study.netty.class08.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + ":收到服务端的消息:" + messageResponsePacket.getMessage());
    }
}
