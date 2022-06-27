package com.easy.job.client;

import com.easy.job.core.common.utils.IpUtil;
import com.easy.job.core.handler.EasyJobHandler;
import com.easy.job.core.handler.model.DelayJob;
import com.easy.job.core.model.Task;
import com.easy.job.core.model.TaskLog;
import com.easy.job.core.model.TaskLogStatus;
import com.easy.job.core.service.TaskLogService;
import com.easy.job.core.service.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author hkal_mark
 */
@Component
public class EasyJobExecutor<DelayItem> implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;
    @Resource
    private TaskService taskService;

    @Resource
    private TaskLogService taskLogService;

    private TaskLog taskLog;

    //创建任务到期延时队列
    private DelayQueue<DelayJob> taskQueue = new DelayQueue<>();

    //存放所有等待执行的线程
    private ExecutorService masterPool = Executors.newFixedThreadPool(1);

    //运行任务的线程池
    private ThreadPoolExecutor workerPool;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        workerPool = new ThreadPoolExecutor(8, 512, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));

        //加载待处理的任务
        masterPool.execute(new TaskLoader());

        //任务调度
        workerPool.execute(new WorkerThread());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    class  TaskLoader implements Runnable{
        public TaskLoader(){}

        @Override
        public void run() {
            for(;;) {
                try {
                    //查询当前节点的所有任务
                    List<Task> taskList = taskService.getNotStartedList(IpUtil.getIp());
                    if(taskList == null || taskList.isEmpty()) {
                        Thread.sleep(200);
                        continue;
                    }

                    long expectedTime=System.currentTimeMillis() + (5 * 60 *1000);
                    taskList=taskList.stream().filter(ele->ele.getNextStartTime().getTime()<=expectedTime).collect(Collectors.toList());
                    if(taskList == null || taskList.isEmpty()) {
                        continue;
                    }

                    for(Task task:taskList) {
                        //将任务设置为待执行
                        taskLog.setTaskStatus(TaskLogStatus.Waiting.getValue());

                        DelayJob delayTask = new DelayJob(task);
                        taskQueue.offer(delayTask);

                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class WorkerThread implements Runnable {
        @Override
        public void run() {
            for (;;) {
                try {
                    //时间到了就可以从延时队列拿出任务对象
                    DelayJob job = taskQueue.take();
                    if(job != null && job.getJob() != null) {
                        Task task = job.getJob();

                        //写入taskLog
                        taskLog.setStartTime(System.currentTimeMillis());
                        taskLog.setTaskId(task.getTaskId());
                        taskLog.setTaskWorkerId(task.getWorkerId());
                        taskLog.setTaskStatus(2);
                        //需要工具生成trace_id，未完成
                        taskLog.setTraceId(System.currentTimeMillis());
                        taskLogService.save(taskLog);

                        String beanName=task.getBeanName();

                        EasyJobHandler jobHandler=applicationContext.getBean(beanName,EasyJobHandler.class);
                        jobHandler.execute();

                        //任务执行完成
                        taskLogService.updateTaskStatus(task.getId(), TaskLogStatus.Done.getValue());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
