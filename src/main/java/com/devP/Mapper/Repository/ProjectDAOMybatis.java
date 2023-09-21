package com.devP.Mapper.Repository;

import com.devP.VO.MemberVO;
import com.devP.VO.ProjectGroupVO;
import com.devP.VO.ProjectListVO;
import com.devP.VO.ProjectVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDAOMybatis {

    @Autowired
    private SqlSessionTemplate mybatis;

    public int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3) {
        return mybatis.insert("ProjectDAO.insertProject", vo);
    }

    public int getProjectId(ProjectVO vo) { return mybatis.selectOne("ProjectDAO.getProjectId", vo);}

    public void insertProject(ProjectVO vo) {
    }
}
