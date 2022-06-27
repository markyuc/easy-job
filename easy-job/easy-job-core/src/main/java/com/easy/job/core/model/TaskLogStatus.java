package com.easy.job.core.model;


/**
 * @author hkal_mark
 */

public enum TaskLogStatus {
    //未开始
    NotStarted(0),
    //待执行
    Waiting(1),
    //执行中
    Doing(2),
    //异常
    Error(3),
    //已完成
    Done(4),
    //已停止
    Stop(5);

    int value;

    TaskLogStatus(int id) {
        this.value = id;
    }

    public int getValue() {
        return value;
    }

    public  static TaskLogStatus  valueOf(int id) {
        switch (id) {
            case 1:
                return Waiting;
            case 2:
                return Doing;
            case 3:
                return Error;
            case 4:
                return Done;
            case 5:
                return Stop;
            default:
                return NotStarted;
        }
    }
}
