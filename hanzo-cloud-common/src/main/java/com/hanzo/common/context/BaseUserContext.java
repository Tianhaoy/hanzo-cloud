package com.hanzo.common.context;

import com.hanzo.common.model.HanZoLoginUser;

/**
 * @Author thy
 * @Date 2020/11/17 10:49
 * @Description:用户信息上下文
 */
public class BaseUserContext {

    private static ThreadLocal<HanZoLoginUser> userHolder = new ThreadLocal<HanZoLoginUser>();

    public static void setUser(HanZoLoginUser loginUser) {
        userHolder.set(loginUser);
    }

    public static HanZoLoginUser getUser() {
        return userHolder.get();
    }
}
