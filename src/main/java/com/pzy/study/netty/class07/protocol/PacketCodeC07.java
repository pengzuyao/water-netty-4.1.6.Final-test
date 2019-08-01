package com.pzy.study.netty.class07.protocol;

import com.pzy.study.netty.class07.protocol.request.LoginRequestPacket07;
import com.pzy.study.netty.class07.protocol.request.MessageRequestPacket07;
import com.pzy.study.netty.class07.protocol.response.LoginResponsePacket07;
import com.pzy.study.netty.class07.protocol.response.MessageResponsePacket07;
import com.pzy.study.netty.class07.serialize.Serializer07;
import com.pzy.study.netty.class07.serialize.impl.JSONSerializer07;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.pzy.study.netty.class07.protocol.command.Command07.*;

public class PacketCodeC07 {

    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC07 INSTANCE = new PacketCodeC07();

    private final Map<Byte, Class<? extends Packet07>> packetTypeMap;
    private final Map<Byte, Serializer07> serializerMap;


    private PacketCodeC07() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket07.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket07.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket07.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket07.class);

        serializerMap = new HashMap<>();
        Serializer07 serializer = new JSONSerializer07();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }


    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet07 packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer07.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer07.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet07 decode(ByteBuf byteBuf) {
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

        Class<? extends Packet07> requestType = getRequestType(command);
        Serializer07 serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer07 getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet07> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
