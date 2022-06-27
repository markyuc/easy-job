package com.easy.job.core.service;

import com.easy.job.core.model.Task;
import com.easy.job.core.model.TaskLog;

public interface TaskLogService {

    /**
     * 保存任务运行记录
     * @param taskLog
     */
    void save(TaskLog taskLog);

    /**
     * 查看节点信息
     * @param taskLogId
     * @return
     */
    TaskLog queryByTaskLogId(Long taskLogId);

    /**
     * 更新taskLog状态
     * @param taskLogId，status
     * @return
     */
    void updateTaskStatus(Long taskLogId, int status);
}
