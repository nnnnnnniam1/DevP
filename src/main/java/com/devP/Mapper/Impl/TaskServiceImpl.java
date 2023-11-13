package com.devP.Mapper.Impl;


import com.devP.Mapper.Repository.MemberDAOMybatis;
import com.devP.Mapper.Repository.ProjectDAOMybatis;
import com.devP.Mapper.Repository.TaskDAOMybatis;
import com.devP.Service.ProjectService;
import com.devP.Service.TaskService;
import com.devP.VO.MemberVO;
import com.devP.VO.ProjectVO;
import com.devP.VO.TaskListVO;
import com.devP.VO.TaskVO;
import com.devP.VO.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

@Service("taskService")
public class TaskServiceImpl implements TaskService {


	@Autowired
	private ProjectService projectService;
    @Autowired
    private TaskDAOMybatis taskDAO;

	@Autowired
	private ProjectDAOMybatis projectDAO;

	@Autowired
	private MemberDAOMybatis memberDAO;

	@Override
	@ModelAttribute("categoryMap")
	public Map<String, String> setCategoryMap() {
		Map<String, String> categoryMap = new HashMap<>();

		categoryMap.put("1", "기획");
		categoryMap.put("2", "분석");
		categoryMap.put("3", "디자인");
		categoryMap.put("4", "개발");
		categoryMap.put("5", "서버");
		categoryMap.put("6", "테스트");
		categoryMap.put("7", "완료");


		return categoryMap;
	}
	@ModelAttribute("statusMap")
	public Map<String, String> setStatusMap(){
		Map<String,String> statusMap = new HashMap<>();

		statusMap.put("1","대기");
		statusMap.put("2","진행중");
		statusMap.put("3","검토");

		return statusMap;
	}
	// 캘린더 배경색으로 글자색 변경
	private String getTextColor(String backgroundColor) {
		int r = Integer.parseInt(backgroundColor.substring(1, 3), 16);
		int g = Integer.parseInt(backgroundColor.substring(3, 5), 16);
		int b = Integer.parseInt(backgroundColor.substring(5, 7), 16);
		double brightness = (r * 299 + g * 587 + b * 114) / 1000;

		// 밝기가 충분히 낮으면 흰색, 그렇지 않으면 검은색으로
		return brightness < 128 ? "#ffffff" : "#000000";
	}

	@Override
	public List<Map<String, Object>> getMyAllTasks(HttpSession session) {
		UserVO userData = (UserVO) session.getAttribute("user");
		List<TaskVO> voList = new ArrayList<TaskVO>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		try {
			TaskVO task = new TaskVO();
			task.setUserId(userData.getId());
//			task.setProjectId((int) session.getAttribute("projectId"));
			voList = taskDAO.getMyTask(task);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}


		for (TaskVO vo : voList) {
			Map<String, Object> result = new HashMap<>();
			int projectId = vo.getProjectId();

			MemberVO m = new MemberVO();
			m.setProjectId(projectId);
			m.setUserId(userData.getId());

			String backgroundColor = projectService.getProjectColor(m);

			result.put("taskId", vo.getTaskId());
			result.put("title", vo.getDetail());
			result.put("progress", vo.getProgress());
			result.put("start", vo.getStartdate());
			result.put("end", vo.getEnddate());
			result.put("allday", true);
			result.put("backgroundColor", backgroundColor);
			result.put("borderColor", backgroundColor);
			result.put("textColor",getTextColor(backgroundColor));
			resultList.add(result);
		}
		return resultList;
	}
	@Override
	public List<Map<String, Object>> getMyTasks(HttpSession session) {
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		UserVO userData = (UserVO) session.getAttribute("user");
		List<TaskVO> voList = new ArrayList<TaskVO>();
    	List<Map<String, Object>> resultList = new ArrayList<>();

		MemberVO m = new MemberVO();
		m.setUserId(userData.getId());

		try {
    		TaskVO task = new TaskVO();
    		task.setUserId(userData.getId());
    		task.setProjectId(projectData.getProjectId());
			m.setProjectId(task.getProjectId());
			voList = taskDAO.getTask(task);
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e);
		}

		String backgroundColor = projectService.getProjectColor(m);

		for (TaskVO vo : voList) {
        	Map<String, Object> result = new HashMap<>();

            result.put("taskId", vo.getTaskId());
            result.put("title", vo.getDetail());
            result.put("progress", vo.getProgress());
            result.put("start", vo.getStartdate());
            result.put("end", vo.getEnddate());
            result.put("allday", true);
			result.put("backgroundColor", backgroundColor);
			result.put("borderColor", backgroundColor);
			result.put("textColor",getTextColor(backgroundColor));

			resultList.add(result);

		}
		return resultList;
	}

	@Override
	public List<TaskVO> getProjectTaskList(int projectId){
		return taskDAO.getProjectTaskList(projectId);
	}

	@Override
	public List<TaskVO> getMyProjectTaskList(TaskVO vo){
		return taskDAO.getMyProjectTaskList(vo);
	}
	// 한 일 가져오기 서비스
	@Override
	public List<TaskVO> getMyDoneTasks(TaskVO vo){
		List<TaskVO> tasklist= taskDAO.getMyProjectTaskList(vo);
		
		filterTasks(tasklist);
		return tasklist;
	}
	//한 일 업무 필터
	private static void filterTasks(List<TaskVO> taskList) {
        Iterator<TaskVO> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            TaskVO task = iterator.next();
            if (!task.getStatus().equals("완료")) {
                iterator.remove(); // 상태가 "완료"가 아닌 항목을 리스트에서 제거
            }
        }
    }
	
	public int getUserTaskList(Model model,HttpSession session){
		UserVO userData = (UserVO) session.getAttribute("user");
		if(userData.getId() != null) {
			String userId = userData.getId();
			List<TaskListVO> vo = taskDAO.getUserTaskList(userId);

			model.addAttribute("taskList", taskDAO.getUserTaskList(userId));
			return 200;
		}else{
			return 405;
		}
	}



	@Override
	public void getTaskCount(Model model, HttpSession session) {
		UserVO userData = (UserVO) session.getAttribute("user");
		int progresscount = 0, completedcount = 0, pastcount = 0;
		List<TaskVO> voList = new ArrayList<TaskVO>();
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		TaskVO task = new TaskVO();
		task.setUserId(userData.getId());
		task.setProjectId(projectData.getProjectId());
//		task.setProjectId((int) session.getAttribute("projectId"));
		voList = taskDAO.getTask(task);
		for (TaskVO vo: voList) {
			if(vo.getEnddate().compareTo(String.valueOf(new Date())) > 0){
				pastcount++;
			}
			else if(vo.getProgress() >= 0 && vo.getProgress() < 100) {
				progresscount++;
			}else if(vo.getProgress() == 100){
				completedcount++;
			}
		}
		model.addAttribute("pasttaskcount", pastcount);
		model.addAttribute("progresstaskcount", progresscount);
		model.addAttribute("completedtaskcount", completedcount);
	}

	@Override
	public void insertTask(TaskVO vo){taskDAO.insertTask(vo);}
	@Override
	public void updateTaskLeader(TaskVO vo){taskDAO.updateTaskLeader(vo);}

	@Override
	public int updateTaskMember(TaskVO vo,HttpSession session){
		ProjectVO projectData = (ProjectVO) session.getAttribute("project");
		UserVO userdata = (UserVO) session.getAttribute("user");
		ArrayList<TaskVO> taskVOList = vo.getTaskVOList();
		for(TaskVO task : taskVOList){
			System.out.println(task.getProjectId() + task.getUserId());
			taskDAO.updateTaskStatus(task);
			memberDAO.updateMemberProgress(task);
		};
		vo.setUserId(userdata.getId());
		vo.setProjectId(projectData.getProjectId());
		memberDAO.updateMemberProgress(vo);
		projectDAO.updateProgress(vo.getProjectId());
		
		ProjectVO projectVO = new ProjectVO();
		projectVO.setProjectId(vo.getProjectId());
		session.setAttribute("project", projectService.getProject(projectVO));
		return 200;
	}

	@Override
	public void deleteTask(int taskId){taskDAO.deleteTask(taskId);}

	@Override
	public int getPastTaskCnt(TaskVO task){
		Date currentDate = new Date();
		task.setNow(currentDate);

		return taskDAO.getPastTaskCnt(task);
	}
	@Override
	public int getProgressTaskCnt(TaskVO task){
		Date currentDate = new Date();
		task.setNow(currentDate);

		return taskDAO.getProgressTaskCnt(task);
	}
	@Override
	public int getCompleteTaskCnt(TaskVO task){

		return taskDAO.getCompleteTaskCnt(task);
	}
}
