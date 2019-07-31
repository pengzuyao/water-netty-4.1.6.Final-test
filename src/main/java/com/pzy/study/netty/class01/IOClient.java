package com.pzy.study.netty.class01;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-07-30
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Socket socket = new Socket("127.0.0.1" , 8000);
                while (true){
                    socket.getOutputStream().write((new Date() + ":hello world!").getBytes());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
