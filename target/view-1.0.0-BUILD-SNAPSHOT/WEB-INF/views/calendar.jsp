<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>


	
      document.addEventListener('DOMContentLoaded', function() {
   	  	 var request = $.ajax({
             url: "/task/getTask.do", // 변경하기
             method: "GET",
             contentType: "application/json; charset=utf-8", // 요청의 문자 인코딩 설정
           	 success: function(data) {
               	 console.log(data);
          	 	var calendarEl = document.getElementById('calendar');
                var calendar = new FullCalendar.Calendar(calendarEl, {
             	  headerToolbar: {
              	      start: 'custom1,custom2,custom3',
              	      center: 'title',
              	      end: 'prevYear,prev,next,nextYear'	
              	  },
              	  customButtons: {
                   custom1: {
                     text: '일',
                     click: function() {
                       calendar.changeView('timeGridDay');
                     }
                   },
                   custom2: {
                     text: '주',
                     click: function() {
                         calendar.changeView('timeGridWeek');
                     }
                   },
                   custom3: {
                       text: '월',
                       click: function() {
                           calendar.changeView('dayGridMonth');
                       }
                   }
                 },
                 initialView: 'dayGridMonth',
                 locale: 'ko',
                 themeSystem: 'bootstrap5',
                     events: data
               });
               calendar.render();
     	     },
       	     error: function(jqXHR, textStatus, errorThrown) {
       	         console.error("Error: " + textStatus, errorThrown);
       	     }
         });
      });
    </script>
</head>
<body>
<div id='calendar'></div>
</body>
</html>