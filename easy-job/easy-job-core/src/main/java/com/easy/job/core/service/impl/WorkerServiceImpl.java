package com.easy.job.core.service.impl;
import com.easy.job.core.dao.WorkerDao;
import com.easy.job.core.model.Worker;
import com.easy.job.core.service.WorkerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hkal_mark
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    @Resource
    WorkerDao workerDao;

    public Long groupId;

    public WorkerServiceImpl (Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public Long insert(Worker worker) {
         Worker newWorker = this.workerDao.save(worker);
         return newWorker.getWorkId();
    }

    @Override
    public void disable(Long workerId) {
        Worker worker = this.queryByWorkerID(workerId);
        worker.setDisable(1);
        this.workerDao.save(worker);
    }

    @Override
    public void enable(Long workerId) {
        Worker worker = this.queryByWorkerID(workerId);
        worker.setDisable(0);
        this.workerDao.save(worker);
    }

    @Override
    public Worker queryByWorkerID(Long workerId) {
        return this.workerDao.queryByWorkerID(workerId);
    }

    @Override
    public List<Worker> queryByGroupId(Long groupId){
        List<Worker> list = this.workerDao.queryByGroupId(groupId);
        return list;
    }

    @Override
    public int queryTotalWorkerNoByGroupId(Long groupId) {
        return this.workerDao.queryTotalWorkerNoByGroupId(groupId);
    }
}
