package com.devP.Mapper.Impl;

import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Service.*;
import com.devP.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.*;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAOMybatis projectDAO;
    @Autowired
    private MemberDAOMybatis memberDAO;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private MailService mailService;

    @Autowired
    private LeaderService leaderService;

    @Override
    public Map<String, String> getMemberMap(List<String> members){
        Map<String, String> memberMap = new HashMap<String, String>();
        for(String member: members){
            String[] m = member.split(",");
            memberMap.put(m[0], m[1]);
        }
        return memberMap;
    }



    @Override
    public ProjectVO getProject(ProjectVO vo){
        return projectDAO.getProject(vo);
    }

    @Override
    public String getProjectName(int projectId){
        return projectDAO.getProjectName(projectId);
    }

    @Override
    public int getProjectProgress(ProjectVO vo){
        return projectDAO.getProjectProgress(vo);
    }

    @Override
     public MemberVO getMyProjectData(MemberVO vo){return memberDAO.getMyProjectData(vo);}

    @Override
    public int insertProjectColor(MemberVO vo){
        memberDAO.insertProjectColor(vo);
        return 200;
    }

    @Override
    public String getProjectColor(MemberVO vo){return memberDAO.getProjectColor(vo);}

    @Override
    public int insertProject(ProjectVO vo, MemberVO vo2, ProjectGroupVO vo3, HttpSession session) throws Exception {
    	UserVO userData = (UserVO) session.getAttribute("user");
        if(userData.getId() != "") {
            String leader = userData.getId();
            vo.setLeader(leader);
            vo.setProgress(0);
            if(vo.getEmail()!="") {
                String members = vo.getEmail();
                projectDAO.insertProject(vo, vo2, vo3);
                session.removeAttribute("projectName");
                session.setAttribute("projectName", vo.getProjectName());
                vo3.setProjectId(getProjectId(vo));
                leaderService.insertLeader(vo2,vo3.getProjectId(), session);
                leaderService.insertMember(members, vo, vo2, vo3, session);
                return 200;
            }
            return 0;
        }
        else{
            return 405;
        }
    }

    @Override
    public int insertProjectView(HttpSession session) {
        if(session.getAttribute("user") != null) return 200;
        else return 405;

    }
    @Override
    public int getProjectList(Model model, HttpSession session){
    	UserVO userData = (UserVO) session.getAttribute("user");
        if(userData.getId() != null) {
            ProjectListVO vo = new ProjectListVO();
            String userId = userData.getId();
            vo.setUserId(userId);

            model.addAttribute("projectList", projectDAO.getOnGoingProjectList(userId));

            return 200;
        }else {
            return 405;
        }
    }
    @Override
    public int getCompleteProjectList(Model model,HttpSession session){
    	UserVO userData = (UserVO) session.getAttribute("user");
        if(userData.getId() != null) {
            ProjectListVO vo = new ProjectListVO();
            String userId = userData.getId();
            vo.setUserId(userId);

            model.addAttribute("completeProjectList", projectDAO.getCompleteProjectList(userId));
            model.addAttribute("deleteProjectList", projectDAO.getDeleteProjectList(userId));

            return 200;
        }else {
            return 405;
        }
    }

    @Override
    public 	int getProjectDetail(ProjectVO vo, MemberVO member, Model model,HttpSession session){
    	UserVO userData = (UserVO) session.getAttribute("user");
        //이슈 리스트 가져오기
        issueService.getIssuelist(vo.getProjectId(), model);
        //멤버 리스트 가져오기
        model.addAttribute("project",(ProjectVO) session.getAttribute("project"));
        model.addAttribute("myData",getMyProjectData(member));
        model.addAttribute("memberList", getProjectMemberList(vo.getProjectId()));
        model.addAttribute("myTask", taskService.getMyTasks());
//        taskService.getTaskCount(model);
//
        TaskVO task = new TaskVO();
        task.setProjectId(vo.getProjectId());
        task.setUserId(userData.getId());

        model.addAttribute("pastTaskCnt",taskService.getPastTaskCnt(task));
        model.addAttribute("progressTaskCnt",taskService.getProgressTaskCnt(task));
        model.addAttribute("completeTaskCnt",taskService.getCompleteTaskCnt(task));


        return 200;
    }


    @Override
    public int getMyTaskView(ProjectVO project, MemberVO member, TaskVO task, Model model,HttpSession session){
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		UserVO userData = (UserVO) session.getAttribute("user");
        // 프로젝트 및 본인 진행률 가져오기
//        project.setProjectId(projectData.getProjectId());
        member.setProjectId(projectData.getProjectId());
        member.setUserId(userData.getId());
//        project.setProgress(getProjectProgress(project));   // 프로젝트 진행률
//        project.setProjectName(getProjectName(project.getProjectId()));

        // 업무가져오기
        task.setProjectId(projectData.getProjectId());
        task.setUserId(member.getUserId());

//        model.addAttribute("project", project);
        model.addAttribute("member", getMyProjectData(member));

        List<TaskVO> taskVOList;
        taskVOList = taskService.getMyProjectTaskList(task);

        model.addAttribute("taskList",taskVOList);

        return 200;
    }

    @Override
    public int getProjectMemberList(MemberVO vo, Model model,HttpSession session){
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
        vo.setProjectId(projectData.getProjectId());
        model.addAttribute("memberList", getProjectMemberList(vo.getProjectId()));
        return 200;
    }

    @Override
    public int getProjectId(ProjectVO vo){
        return projectDAO.getProjectId(vo);
    }

    @Override
    public List<MemberVO> getProjectMemberList(int projectId) {
        return memberDAO.getProjectMemberList(projectId);
    }

    @Override
    public List<String> getMemberNames(int projectId){
        return projectDAO.getMemberNames(projectId);
    }

    @Override
    public int updateReviewStatus(MemberVO vo){
        projectDAO.updateReviewStatus(vo);
        return 200;
    }

    @Override
    public int updateReview(ReviewVO vo){
        projectDAO.updateReview(vo);
        return 200;
    }

    @Override
    public ReviewVO getReview(ReviewVO vo){
        return projectDAO.getReview(vo);
    }

}