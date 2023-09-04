package com.devP.Service;

import org.springframework.ui.Model;


public interface ProjectService {
	void showCalendar(Model model, Integer year, Integer month);
}
