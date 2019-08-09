package com.pzy.study.netty.class08.server.handler;

import com.pzy.study.netty.class08.protocol.request.QuitGroupRequestPacket;
import com.pzy.study.netty.class08.protocol.response.QuitGroupResponsePacket;
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
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        //1.获取群对应的 channelGroup,然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        //2.构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);

    }
}
