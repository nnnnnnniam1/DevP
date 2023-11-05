document.addEventListener('DOMContentLoaded', function() {
   	  	 var request = $.ajax({
             url: "/task/getMyTasks.do",
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