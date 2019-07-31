package com.pzy.study.netty.class01;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Destription: 升级版(NIO)
 * Author: pengzuyao
 * Time: 2019-07-30
 */
public class IOServerPlus {

    public static void main(String[] args) throws IOException {

        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();
        new Thread(()->{
            try {
           //对应IO编程中的服务端启动
            ServerSocketChannel listenerChannel = ServerSocketChannel.open();
            listenerChannel.socket().bind(new InetSocketAddress(8000));
            listenerChannel.configureBlocking(false);
            listenerChannel.register(serverSelector , SelectionKey.OP_ACCEPT);

            while (true){
                //检测是否有新的连接，这里的1指的是阻塞时间为1ms
                if (serverSelector.select(1) > 0){
                    Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()){
                            try {
                            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(clientSelector ,SelectionKey.OP_READ);
                            }finally {
                                iterator.remove();
                            }
                        }

                    }
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    //批量轮询是否有哪些连接有数据可读，这里的1指是阻塞的时间为1ms
                    if (clientSelector.select(1) > 0){
                        Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()){
                            SelectionKey key = iterator.next();
                            if (key.isReadable()){
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    //面向Buffer
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                }finally {
                                    iterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }


}
