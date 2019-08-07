package com.pzy.study.netty.class08.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
public interface ConsoleCommand {

    void exec(Scanner scanner , Channel channel);
}
