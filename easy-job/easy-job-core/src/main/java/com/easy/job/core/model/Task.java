package com.easy.job.core.model;


import lombok.*;

import java.util.Date;

/**
 * @author hkal_mark
 */
@Data
public class Task {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 任务名
     */
    private String name;
    /**
     * bean名
     */
    private String beanName;

    /**
     * 所属节点id
     */
    private Long workerId;

    /**
     * 0正常执行，1是禁用，2是正常执行且已完成
     */
    private int status;

    /**
     * 间隔时长，单位为ms
     */
    private int interval;

    /**
     * 手动执行：0否，1是
     * 用于手动重试
     */
    private int retry = 0;

    public Date getNextStartTime(){
        if (this.retry == 1){
            return new Date();
        }

        return new Date(System.currentTimeMillis() + this.interval);
    }
}
