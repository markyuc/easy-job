package com.easy.job.core.model;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hkal_mark
 */

@Data
public class TaskLog {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 记录id
     */
    private Long taskLogId;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 任务状态
     */
    private int taskStatus;

    /**
     * 任务开始时间
     */
    private Long startTime;

    /**
     * 任务结束时间
     */
    private Long endTime;

    /**
     * 任务运行的节点信息
     */
    private Long taskWorkerId;

    /**
     * 全链路唯一id
     */
    private Long traceId;

}
