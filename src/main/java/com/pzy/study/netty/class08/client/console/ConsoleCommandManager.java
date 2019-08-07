package com.pzy.study.netty.class08.client.console;

import com.pzy.study.netty.class08.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/07
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String ,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser" ,new SendToUserConsoleCommand());
        consoleCommandMap.put("sendToUser" ,new LogoutConsoleComand());
        consoleCommandMap.put("sendToUser" ,new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 获取第一个指令
        String command = scanner.next();
        if (!SessionUtil.hasLogin(channel)){
            return;
        }
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null){
            consoleCommand.exec(scanner , channel);
        }else {
            System.err.println("无法识别[" + command + "]指令，请重新输入！");
        }

    }
}
