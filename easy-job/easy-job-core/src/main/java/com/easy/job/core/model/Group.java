package com.easy.job.core.model;

import lombok.Data;

/**
 * @author hkal_mark
 */
@Data
public class Group {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 分组名
     */
    private String groupName;
}
