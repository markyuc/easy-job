package com.easy.job.core.service;

import com.easy.job.core.model.Worker;

import java.util.List;

/**
 * @author hkal_mark
 */
public interface WorkerService {
    /**
     * 新增一个节点
     * @param worker
     */
    Long insert(Worker worker);

    /**
     * 禁用
     * @param id
     * @return
     */
    void disable(Long id);


    /**
     * 重新启用节点
     * @param workerId
     * @return
     */
    void enable(Long workerId);

    /**
     * 查看节点信息
     * @param workerId
     * @return
     */
    Worker queryByWorkerID(Long workerId);

    /**
     * 查询所属groupId下所有worker
     * @param groupId
     * @return
     */
    List<Worker> queryByGroupId(Long groupId);

    int queryTotalWorkerNoByGroupId(Long groupId);

}
