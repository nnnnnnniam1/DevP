package com.devP.Service;


import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.devP.VO.TaskVO;

public interface TaskService {
	List<Map<String, Object>> getTask();
	
	void getTaskCount(Model model);
}
