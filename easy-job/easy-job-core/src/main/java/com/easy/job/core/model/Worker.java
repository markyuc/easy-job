package com.easy.job.core.model;


import lombok.*;

/**
 * @author hkal_mark
 */
@Data
public class Worker {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 节点id
     */
    private Long workId;
    /**
     * 节点ip
     */
    private String ip;
    /**
     * 节点端口号
     */
    private int port = 9090;
    /**
     * 节点名
     */
    private String name;
    /**
     * 节点是否禁用
     */
    private int disable = 0;
    /**
     * 节点所属分组
     */
    private Long groupId;

}
