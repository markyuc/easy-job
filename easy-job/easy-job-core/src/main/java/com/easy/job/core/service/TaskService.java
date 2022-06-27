package com.easy.job.core.service;

import com.easy.job.core.model.Task;
import com.easy.job.core.model.Worker;

import java.util.List;

/**
 * @author hkal_mark
 */
public interface TaskService {
    /**
     * 新增一个任务
     * @param task
     */
    Task insert(Task task);

    Task register(Task task, Long groupId);

    /**
     * 禁用
     * @param taskId
     * @return
     */
    void disable(Long taskId);


    /**
     * 重新启用任务
     * @param taskId
     * @return
     */
    void enable(Long taskId);

    /**
     * 更新任务为完成，且不会再次运行
     * @param taskId
     * @return
     */
    void finish(Long taskId);

    /**
     * 查看节点信息
     * @param taskId
     * @return
     */
    Task queryByTaskId(Long taskId);

    int updateStatus(Long id,int status);


    List<Task> getNotStartedList(String ip);

    void retry(Long id);
}
