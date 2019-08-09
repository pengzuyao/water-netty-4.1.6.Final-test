package com.pzy.study.netty.class08.server.handler;

import com.pzy.study.netty.class08.protocol.request.JoinGroupRequestPacket;
import com.pzy.study.netty.class08.protocol.response.JoinGroupResponsePacket;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/09
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler  INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        //2.构造加群相应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        ctx.writeAndFlush(responsePacket);
    }
}
