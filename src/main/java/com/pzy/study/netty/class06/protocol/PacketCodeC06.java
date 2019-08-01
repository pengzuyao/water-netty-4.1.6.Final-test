package com.pzy.study.netty.class06.protocol;

import com.google.common.collect.Maps;
import com.pzy.study.netty.class06.protocol.request.LoginRequestPacket06;
import com.pzy.study.netty.class06.serialize.Serializer06;
import com.pzy.study.netty.class06.serialize.impl.JSONSerializer06;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.pzy.study.netty.class06.protocol.command.Command06.LOGIN_REQUEST;
import static com.pzy.study.netty.class06.protocol.command.Command06.LOGIN_RESPONSE;

import java.util.Map;

/**
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/08/01
 */
public class PacketCodeC06 {

    private static final int MAGIC_NUMBER = 0x12345678;

    private final Map<Byte, Class<? extends Packet06>> packetTypeMap;
    private final Map<Byte, Serializer06> serializerMap;

    public static class Instance{
        public static PacketCodeC06 getInstance = new PacketCodeC06();
    }

    private PacketCodeC06(){
        packetTypeMap = Maps.newHashMap();
        packetTypeMap.put(LOGIN_REQUEST , LoginRequestPacket06.class);
        packetTypeMap.put(LOGIN_RESPONSE , LoginRequestPacket06.class);

        serializerMap = Maps.newHashMap();
        Serializer06 serializer06 = new JSONSerializer06();
        serializerMap.put(serializer06.getSerializerAlogrithm() , serializer06);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator , Packet06 packet06){
        //1.创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        //2.序列化 java 对象
        byte[] bytes = Serializer06.DEFAULT.serialize(packet06);
        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet06.getVersion());
        byteBuf.writeByte(Serializer06.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet06.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet06 decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet06> requestType = getRequestType(command);
        Serializer06 serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer06 getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet06> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

}
