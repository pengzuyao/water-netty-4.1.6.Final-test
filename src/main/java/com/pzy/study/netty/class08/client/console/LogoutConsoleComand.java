package com.pzy.study.netty.class08.client.console;

import com.pzy.study.netty.class08.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
public class LogoutConsoleComand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
