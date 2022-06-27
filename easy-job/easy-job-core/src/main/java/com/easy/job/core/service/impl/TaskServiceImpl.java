package com.easy.job.core.service.impl;

import com.easy.job.core.Strategy.LoadBalancer;
import com.easy.job.core.dao.TaskDao;
import com.easy.job.core.dao.WorkerDao;
import com.easy.job.core.model.Group;
import com.easy.job.core.model.Task;
import com.easy.job.core.model.Worker;
import com.easy.job.core.service.TaskService;
import com.easy.job.core.service.WorkerService;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hkal_mark
 */
public class TaskServiceImpl implements TaskService {
    @Resource
    TaskDao taskDao;
    WorkerDao workerDao;
    WorkerService workerService;

    @Override
    public Task insert(Task task) {
        Task newTask = taskDao.save(task);
        return newTask;
    }

    @Override
    public Task register(Task task, Long groupId) {
        // 调用负载均衡获得应该worker节点
        WorkerService workerService = new WorkerServiceImpl(groupId);
        LoadBalancer loadBalancer = new LoadBalancer(workerService);
        Long workerId = loadBalancer.getBalanceWorker(groupId);
        task.setWorkerId(workerId);
        return insert(task);
    }

    @Override
    public void disable(Long taskId) {
        Task task = this.queryByTaskId(taskId);
        task.setStatus(1);
        taskDao.save(task);

    }

    @Override
    public void enable(Long taskId) {
        Task task = this.queryByTaskId(taskId);
        task.setStatus(0);
        taskDao.save(task);
    }

    @Override
    public void finish(Long taskId) {
        Task task = this.queryByTaskId(taskId);
        task.setStatus(2);
        taskDao.save(task);
    }

    @Override
    public Task queryByTaskId(Long taskId) {
        return this.taskDao.queryByTaskId(taskId);
    }

    @Override
    public int updateStatus(Long id,int status){
        return this.taskDao.updateStatus(id,status);
    }

    @Override
    public List<Task> getNotStartedList(String ip) {
        List<Task> res = new ArrayList<>();

        //根据ip查询任务运行节点
        Worker worker = workerDao.getByIp(ip);

        if (null == worker || worker.getDisable()==1) {
            return res;
        }
        //根据节点查询所属群组
        List<Group> groups = taskDao.findListByWorkerId(worker.getWorkId());

        if (CollectionUtils.isEmpty(groups)) {
            return res;
        }
        Set<Long> groupIds = groups.stream().map(Group::getId).collect(Collectors.toSet());


        //根据群组查询所有代执行的任务
        res.addAll(taskDao.findTasksByGroupIds(groupIds));

        return res;
    }

    @Override
    public void retry(Long id) {
        Task task = this.queryByTaskId(id);
        task.setRetry(1);
        taskDao.save(task);
    }

}
