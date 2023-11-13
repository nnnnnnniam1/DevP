package com.devP.Service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.devP.VO.TaskListVO;
import com.devP.VO.TaskVO;
import org.springframework.ui.Model;

public interface TaskService {
	List<Map<String, Object>> getMyTasks(HttpSession session);

	List<Map<String, Object>> getMyAllTasks(HttpSession session);

	List<TaskVO> getProjectTaskList(int projectId);

	List<TaskVO> getMyProjectTaskList(TaskVO vo);

	int getUserTaskList(Model model, HttpSession session);

	void insertTask(TaskVO vo);

	void updateTaskLeader(TaskVO vo);

	void deleteTask(int taskId);

	int getPastTaskCnt(TaskVO task);

	int getProgressTaskCnt(TaskVO task);

	int getCompleteTaskCnt(TaskVO task);

	Map<String,String> setCategoryMap();

	Map<String,String> setStatusMap();

	List<TaskVO> getMyDoneTasks(TaskVO vo);

	void getTaskCount(Model model, HttpSession session);

	int updateTaskMember(TaskVO vo, HttpSession session);
}
