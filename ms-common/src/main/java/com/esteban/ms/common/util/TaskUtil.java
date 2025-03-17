package com.esteban.ms.common.util;

public class TaskUtil {

    public static String getTaskResultKey(String channel, String taskId) {
        return channel + "::result::" + taskId;
    }

}
