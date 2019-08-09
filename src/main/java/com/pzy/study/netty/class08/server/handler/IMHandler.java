package com.pzy.study.netty.class08.server.handler;


import com.pzy.study.netty.class08.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import static com.pzy.study.netty.class08.protocol.command.Command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/09
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    private Map<Byte , SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private static class Inner{
        private static IMHandler instance = new IMHandler();
    }

    public static IMHandler getInstance(){
        return Inner.instance;
    }

    private IMHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(MESSAGE_REQUEST , MessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST , CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST ,JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST ,QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST ,ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST ,GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST ,LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx , packet);
    }
}
