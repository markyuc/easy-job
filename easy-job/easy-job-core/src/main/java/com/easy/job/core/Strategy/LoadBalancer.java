package com.easy.job.core.Strategy;

import com.easy.job.core.model.Worker;
import com.easy.job.core.service.WorkerService;

import java.util.List;
import java.util.Random;

/**
 * @author hkal_mark
 */
public class LoadBalancer {

    WorkerService workerService;

    public LoadBalancer(WorkerService workerService) {
        this.workerService = workerService;
    }

    public  Long getBalanceWorker(Long groupId) {
        int totalNo = workerService.queryTotalWorkerNoByGroupId(groupId);
        List<Worker> list = workerService.queryByGroupId(groupId);
        Random rand = new Random();
        int randomNo = rand.nextInt(totalNo);
        return list.get(randomNo).getWorkId();

    }
}
