package com.easy.job.core.service.impl;

import com.easy.job.core.dao.GroupDao;
import com.easy.job.core.model.Group;
import com.easy.job.core.service.GroupService;

import javax.annotation.Resource;

/**
 * @author hkal_mark
 */
public class GroupServiceImpl implements GroupService {
    @Resource
    GroupDao groupDao;

    @Override
    public int insert(Group group) {
        groupDao.save(group);
        return 0;
    }


    @Override
    public Group query(Long groupId) {
        Group group = groupDao.query(groupId);
        return group;
    }

}
