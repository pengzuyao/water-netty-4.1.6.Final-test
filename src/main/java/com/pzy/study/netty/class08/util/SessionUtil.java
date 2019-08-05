package com.pzy.study.netty.class08.util;

import com.pzy.study.netty.class08.attribute.Attributes;
import com.pzy.study.netty.class08.session.Session;
import io.netty.channel.Channel;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-05
 */
public class SessionUtil {

    private static final Map<String , Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session , Channel channel){
        userIdChannelMap.put(session.getUserId() , channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if (hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }
}
