package com.easy.job.core.service;

import com.easy.job.core.model.Group;
import java.util.List;

/**
 * @author hkal_mark
 */
public interface GroupService {
    /**
     * 新增一个组
     * @param group
     * @return
     */
    int insert(Group group);


    /**
     * 查询一个组
     * @param groupId
     * @return
     */
    Group query(Long groupId);
}
