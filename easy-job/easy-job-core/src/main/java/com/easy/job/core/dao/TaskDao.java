package com.easy.job.core.dao;

import com.easy.job.core.model.Group;
import com.easy.job.core.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author hkal_mark
 */
@Mapper
public interface TaskDao {
    public Task save(Task task);

    public int updateStatus(Long id,int status);

    public Task queryByTaskId(@Param("id") Long taskId);


    Collection<? extends Task> findTasksByGroupIds(Set<Long> groupIds);

    List<Group> findListByWorkerId(Long workId);
}
