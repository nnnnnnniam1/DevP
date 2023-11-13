package com.devP.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.devP.VO.ProjectVO;
import com.devP.VO.TaskVO;
import com.devP.VO.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devP.Service.TaskService;

@Controller
@RequestMapping("task")
public class TaskController {
	

	@Autowired
	private TaskService taskService;


	//업무 가져오기
    @RequestMapping(value="/getTask.do", method= RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
	    public List<Map<String, Object>> getTask(HttpSession session){
    	
	    return taskService.getMyTasks(session);
  	}
    //한 일 가져오기
    @RequestMapping(value="/getDoneTask/view.do", method= RequestMethod.GET, produces="application/json;charset=UTF-8")
    public String getDoneTaskView(HttpSession session, Model model) {
    	ProjectVO projectData = (ProjectVO) session.getAttribute("project");
    	UserVO userData = (UserVO) session.getAttribute("user");
		
    	TaskVO vo = new TaskVO();
    	vo.setProjectId(projectData.getProjectId());
    	vo.setUserId(userData.getId());
    	model.addAttribute("donetasklist", taskService.getMyDoneTasks(vo));
    	return "doneTask";
    }

	@RequestMapping(value="/getMyTasks.do", method= RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> getMyTask(HttpSession session){ return taskService.getMyAllTasks(session); }


	// 업무 추가 뷰
	@RequestMapping(value = "/insertTask.do", method = RequestMethod.GET)
	public String addTask(){ return "addTask"; }

	@RequestMapping(value="/modify.do", method = RequestMethod.POST)
	public String modifyTask(@ModelAttribute TaskVO vo, Model model,HttpSession session){
		int result = taskService.updateTaskMember(vo, session);


		return "redirect:/project/myTask.do";
	}
}
