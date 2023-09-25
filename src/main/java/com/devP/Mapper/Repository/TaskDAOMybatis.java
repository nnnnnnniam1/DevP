package com.devP.Mapper.Repository;

import com.devP.VO.IssueVO;
import com.devP.VO.TaskVO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;
    
    public List<TaskVO> getTask(TaskVO task) {
    	return mybatis.selectList("TaskDAO.getTask", task);
    }
}
