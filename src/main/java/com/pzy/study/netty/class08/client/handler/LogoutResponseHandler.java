package com.pzy.study.netty.class08.client.handler;

import com.pzy.study.netty.class08.protocol.response.LogoutResponsePacket;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
