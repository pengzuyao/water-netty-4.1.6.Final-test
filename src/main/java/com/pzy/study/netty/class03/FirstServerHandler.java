package com.pzy.study.netty.class03;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/07/31
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "服务端读到数据 ->" + byteBuf.toString(Charset.forName("utf-8")));
        //回复数据到客户端
        System.out.println(new Date() + ":服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        //1.获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        //2.准备数据，指定字符串的字符集为 utf-8
        byte[] bytes = "你好，欢迎关注我的微信公众号,《一个大木头》".getBytes(Charset.forName("utf-8"));
        //3.填充数据到ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }
}
