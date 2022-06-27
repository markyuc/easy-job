package com.easy.job.core.handler.model;

import com.easy.job.core.model.Task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author hkal_mark
 */
public class DelayJob implements Delayed {

    private final long delay;
    private final Task task;

    public DelayJob(Task task) {
        this.task = task;
        this.delay = task.getInterval();
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delay , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    public Task getJob() {
        return this.task;
    }
}
