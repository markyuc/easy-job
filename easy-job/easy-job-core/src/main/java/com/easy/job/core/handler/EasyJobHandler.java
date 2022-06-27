package com.easy.job.core.handler;

import com.easy.job.core.handler.model.TaskResult;


/**
 * @author hkal_mark
 */
public interface EasyJobHandler {
    /**
     * 作业的接口，需要使用方实现
     * @return
     * @throws Exception
     */
    TaskResult execute();
}
