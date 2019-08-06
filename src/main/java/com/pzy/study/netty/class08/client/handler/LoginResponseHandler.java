package com.pzy.study.netty.class08.client.handler;



import com.pzy.study.netty.class08.protocol.request.LoginResponsePacket;
import com.pzy.study.netty.class08.session.Session;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

   /* @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("pzy");
        loginRequestPacket.setPassword("123456");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();
        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + ": 客户端登录成功");
            System.out.println("[" + userName + "]登录成功 ， userId 为" + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId ,userName) ,ctx.channel());
        } else {
            System.out.println("["+ userName + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭！");
    }
}
