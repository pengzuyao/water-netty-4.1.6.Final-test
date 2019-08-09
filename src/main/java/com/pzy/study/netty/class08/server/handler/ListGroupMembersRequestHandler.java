package com.pzy.study.netty.class08.server.handler;

import com.pzy.study.netty.class08.protocol.request.ListGroupMembersRequestPacket;
import com.pzy.study.netty.class08.protocol.response.ListGroupMembersResponsePacket;
import com.pzy.study.netty.class08.session.Session;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/09
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
         //1.获取群的 ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        //2.遍历群成员的 channel , 对应的session ,构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }
        //3.构建获取成员列表响应写回到客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.writeAndFlush(responsePacket);
    }
}
