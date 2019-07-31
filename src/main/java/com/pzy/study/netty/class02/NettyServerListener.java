package com.pzy.study.netty.class02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/07/31
 */
public class NettyServerListener {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap.
                group(bossGroup, workerGroup).
                channel(NioServerSocketChannel.class).
                childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nsc) throws Exception {
                nsc.pipeline().addLast(new StringDecoder());
                nsc.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                        System.out.println(msg);
                    }
                });
            }
        }).bind(8000).
                //异步监听端口是否绑定成功
                addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("端口绑定成功！");
                        } else {
                            System.out.println("端口绑定失败！");
                            bind(serverBootstrap ,8001);
                        }
                    }
                });

        /*serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                System.out.println("服务启动中");
            }
        });*/

        serverBootstrap.attr(AttributeKey.newInstance("serverName") ,"nettyServer");
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey") , "clientValue");
        serverBootstrap.option(ChannelOption.SO_BACKLOG , 1024);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE , true).
                        childOption(ChannelOption.TCP_NODELAY , true);
    }

    public static void bind(final ServerBootstrap serverBootstrap , final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功！");
                } else {
                    System.out.println("端口[" + port + "]绑定失败！");
                    bind(serverBootstrap ,port + 1);
                }
            }
        });
    }

}
