package com.easy.job.core.dao;

import com.easy.job.core.model.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hkal_mark
 */
@Mapper
public interface WorkerDao {
    public Worker save(Worker worker);

    public Worker queryByWorkerID(@Param("id") Long id);

    public List<Worker> queryByGroupId(@Param("id") Long id);

    Worker getByIp(String ip);

    int queryTotalWorkerNoByGroupId(Long groupId);
}
