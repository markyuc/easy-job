package com.easy.job.core.dao;

import com.easy.job.core.model.TaskLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hkal_mark
 */
@Mapper
public interface TaskLogDao {
    public int save(TaskLog taskLog);

    public TaskLog queryByTaskLogId(@Param("id") Long taskLogId);
}
