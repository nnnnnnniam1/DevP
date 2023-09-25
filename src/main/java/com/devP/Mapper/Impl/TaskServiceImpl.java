package com.devP.Mapper.Impl;


import com.devP.Mapper.Repository.TaskDAOMybatis;
import com.devP.Service.TaskService;
import com.devP.VO.TaskVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAOMybatis taskDAO;

    @Autowired
    private HttpSession session;


	@Override
	public List<Map<String, Object>> getTask() {
		List<TaskVO> voList = new ArrayList<TaskVO>();
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	try {
    		voList = taskDAO.getTask(session.getAttribute("id").toString());
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e);
		}
		System.out.println(session.getAttribute("id").toString());
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	for (TaskVO vo : voList) {
        	Map<String, Object> result = new HashMap<>();
    		String formattedstartDate = dateFormat.format(vo.getStartdate());
    		String formattedendDate = dateFormat.format(vo.getEnddate());
            result.put("title", vo.getDetail());
            result.put("start", formattedstartDate);
            result.put("end", formattedendDate);
            result.put("allday", true);
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


}
