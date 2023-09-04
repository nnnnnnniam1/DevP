package com.devP.Mapper.Impl;

import com.devP.Service.ProjectService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Override
	public void showCalendar(Model model, Integer year, Integer month) {
		
		System.out.println(model + " " + year + " " + month);
		
        // year와 month가 null인 경우 현재 연도와 월로 설정
        if (year == null || month == null) {
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더합니다.
        }

        // 월별 달력 데이터 생성
        List<List<Integer>> calendarData = generateCalendar(year, month);

        // JSP로 데이터 전달
        model.addAttribute("calendar", calendarData);
        model.addAttribute("year", year);
        model.addAttribute("month", month);

        return;
    }
	private List<List<Integer>> generateCalendar(int year, int month) {
        List<List<Integer>> calendarData = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1); // 해당 연도와 월의 1일로 설정

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 날짜
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 해당 월의 1일의 요일 (1: 일요일, 2: 월요일, ...)

        List<Integer> week = new ArrayList<>();
        for (int i = 1; i < firstDayOfWeek; i++) {
            week.add(null); // 빈 날짜를 추가 (월요일부터 1일까지)
        }

        for (int day = 1; day <= maxDay; day++) {
            week.add(day);
            if (week.size() == 7) {
                calendarData.add(week);
                week = new ArrayList<>();
            }
        }

        if (!week.isEmpty()) {
            calendarData.add(week);
        }

        return calendarData;
    }
}