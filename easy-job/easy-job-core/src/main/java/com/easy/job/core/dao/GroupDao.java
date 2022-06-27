package com.easy.job.core.dao;

import com.easy.job.core.model.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hkal_mark
 */
@Mapper
public interface GroupDao {

    public int save(Group group);

    public Group query(@Param("id") Long id);
}






