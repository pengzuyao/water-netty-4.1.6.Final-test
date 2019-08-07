package com.pzy.study.netty.class08.client;

import com.pzy.study.netty.class08.client.console.ConsoleCommand;
import com.pzy.study.netty.class08.client.console.ConsoleCommandManager;
import com.pzy.study.netty.class08.client.handler.CreateGroupResponseHandler;
import com.pzy.study.netty.class08.client.handler.LoginResponseHandler;
import com.pzy.study.netty.class08.client.handler.LogoutResponseHandler;
import com.pzy.study.netty.class08.client.handler.MessageResponseHandler;
import com.pzy.study.netty.class08.codec.PacketDecoder;
import com.pzy.study.netty.class08.codec.PacketEncoder;
import com.pzy.study.netty.class08.codec.Spliter;
import com.pzy.study.netty.class08.protocol.request.LoginRequestPacket;
import com.pzy.study.netty.class08.protocol.request.MessageRequestPacket;
import com.pzy.study.netty.class08.protocol.response.LogoutResponsePacket;
import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/02
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS ,5000)
                .option(ChannelOption.SO_KEEPALIVE ,true)
                .option(ChannelOption.TCP_NODELAY , true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap ,HOST ,PORT , MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        /*Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String userName = sc.nextLine();
                    loginRequestPacket.setUsername(userName);
                    //密码使用默认的
                    loginRequestPacket.setPassword("123456");
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                }else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId ,message));
                }
            }
        }).start();*/

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

    }

    private static void waitForLoginResponse() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

