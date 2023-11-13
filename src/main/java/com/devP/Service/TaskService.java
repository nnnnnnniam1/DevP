package com.devP.Service;


import java.util.List;
import java.util.Map;

import com.devP.VO.TaskListVO;
import com.devP.VO.TaskVO;
import org.springframework.ui.Model;

public interface TaskService {
	List<Map<String, Object>> getMyTasks();

	List<Map<String, Object>> getMyAllTasks();

	List<TaskVO> getProjectTaskList(int projectId);

	List<TaskVO> getMyProjectTaskList(TaskVO vo);

	int getUserTaskList(Model model);

	void getTaskCount(Model model);

	void insertTask(TaskVO vo);

	void updateTaskLeader(TaskVO vo);

	int updateTaskMember(TaskVO vo);

	void deleteTask(int taskId);

	int getPastTaskCnt(TaskVO task);

	int getProgressTaskCnt(TaskVO task);

	int getCompleteTaskCnt(TaskVO task);

	Map<String,String> setCategoryMap();

	Map<String,String> setStatusMap();

	List<TaskVO> getMyDoneTasks(TaskVO vo);
}
