package com.pzy.study.netty.class08.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-05
 */
@Data
@NoArgsConstructor
public class Session {

    //用户唯一标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
