package com.pzy.study.netty.class08.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;


public class FirstClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            for (int i = 0; i < 1000; i++) {
                ByteBuf buffer = getByteBuf(ctx);
                ctx.channel().writeAndFlush(buffer);
            }
            super.channelActive(ctx);
        }

        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(Charset.forName("utf-8"));
            ByteBuf buffer = ctx.alloc().buffer();
            buffer.writeBytes(bytes);
            return buffer;
        }
}
