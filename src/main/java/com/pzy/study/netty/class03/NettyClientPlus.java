package com.pzy.study.netty.class03;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/07/31
 */
public class NettyClientPlus {


    public static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        bootstrap.
                //指定线程模型
                 group(workGroup).
                //指定 IO 类型为 NIO
                 channel(NioSocketChannel.class).
                //IO 处理逻辑
                 handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new FirstClientHandler() );
                }
                });
        Channel channel = bootstrap.
                connect("127.0.0.1", 8000).
                addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功！");
                    } else {
                        System.out.println("连接失败！");
                        //失败重连
                        connect(bootstrap, "127.0.0.1", 8000, 5);
                    }
                }).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ":hello netty!");
            TimeUnit.SECONDS.sleep(2);
        }

    }

    //指数退避的客户端重连
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃链接！");
            } else {
                //第几次重连
                int order = (MAX_RETRY - retry) + 1;
                //本次重连的时间间隔
                int delay = 1 << order;
                System.err.println(new Date() + ":连接失败，第" + order + "次重连......");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
