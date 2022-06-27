package com.easy.job.core.service.impl;

import com.easy.job.core.dao.TaskLogDao;
import com.easy.job.core.model.Task;
import com.easy.job.core.model.TaskLog;
import com.easy.job.core.service.TaskLogService;

import javax.annotation.Resource;
/**
 * @author hkal_mark
 */
public class TaskLogServiceImpl implements TaskLogService {
    @Resource
    TaskLogDao taskLogDao;

    @Override
    public void save(TaskLog taskLog) {
        taskLogDao.save(taskLog);
    }

    @Override
    public TaskLog queryByTaskLogId(Long taskLogId) {
        return this.taskLogDao.queryByTaskLogId(taskLogId);
    }

    @Override
    public void updateTaskStatus(Long taskLogId, int status) {

    }
}
