package com.devP.Mapper.Repository;

import com.devP.VO.ProjectVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public ProjectVO getProject(ProjectVO vo){ return mybatis.selectOne("ProjectDAO.getProject", vo); }

    public String getProjectName(ProjectVO vo){ return mybatis.selectOne("ProjectDAO.getProjectName", vo); }

    public int getProjectProgress(ProjectVO vo){ return mybatis.selectOne("ProjectDAO.getProjectProgress", vo); }

    public int insertProject(ProjectVO vo) {
        return mybatis.insert("ProjectDAO.insertProject", vo);
    }

}
