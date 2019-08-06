package com.pzy.study.netty.class08.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-08-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    //用户唯一标识
    private String userId;
    private String userName;


}
