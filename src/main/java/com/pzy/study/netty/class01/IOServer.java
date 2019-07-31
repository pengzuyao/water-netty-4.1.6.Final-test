package com.pzy.study.netty.class01;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Destription: 传统IO
 * Author: pengzuyao
 * Time: 2019-07-30
 */
public class IOServer {

    public static void main(String[] args) throws IOException {

        final ServerSocket socket = new ServerSocket(8000);
        //接收新连接线程
        new Thread(()->{
            while (true){
                try {
                    //阻塞方法获取新的连接
                    Socket accept = socket.accept();
                    new Thread(()->{
                        int len;
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = accept.getInputStream();
                            while ((len = inputStream.read(data)) != -1){
                                System.out.println(new String(data  ,0 ,len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
