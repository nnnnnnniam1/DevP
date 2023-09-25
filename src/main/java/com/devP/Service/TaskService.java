package com.devP.Service;


import java.util.List;
import java.util.Map;

import com.devP.VO.TaskVO;

public interface TaskService {
	
	List<Map<String, Object>> getTask();

	List<TaskVO> getProjectTaskList(int projectId);

	List<TaskVO> getMyProjectTaskList(TaskVO vo);

}
