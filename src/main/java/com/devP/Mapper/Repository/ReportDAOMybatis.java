package com.devP.Mapper.Repository;

import com.devP.VO.ReportVO;
import com.devP.VO.TaskListVO;
import com.devP.VO.TaskVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public List<ReportVO> getReportTaskData(int projectId) {
        return mybatis.selectList("ReportDAO.getReportTaskData", projectId);
    }

}
