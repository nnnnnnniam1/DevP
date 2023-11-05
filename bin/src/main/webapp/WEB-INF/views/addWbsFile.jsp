<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.9/xlsx.full.min.js"></script>

  <script type="text/javascript">
    google.charts.load('current', {'packages':['gantt']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart(event){
        var input = event.target;
        var reader = new FileReader();

        var data = new google.visualization.DataTable();


        reader.onload = function(){
            var fdata = reader.result;
            var read_buffer = XLSX.read(fdata, {type : 'binary'});



            read_buffer.SheetNames.forEach(function(sheetName){
                rowdata = XLSX.utils.sheet_to_json(read_buffer.Sheets[sheetName], {
                    defval: null,
                    raw: false // raw 속성을 false로 설정하면 숫자로 읽힌 날짜를 Date 객체로 변환합니다.
                  });
                var allDatas = [];
                var wbsDatas = rowdata.map(function (row) {
                    // 빈 문자열("")이면 null로 대체
                    return [
                        row['Task ID'] || null,            // Task ID
                        row['Task Name'] || null,          // Task Name
                        row['Resource'] || null,           // Resource
                        row['Start Date'] ? new Date(row['Start Date']) : null, // Start Date
                        row['End Date'] ? new Date(row['End Date']) : null,     // End Date
                        row['Duration'] || null,           // Duration
                        parseFloat(row['Percent Complete']) || null,   // Percent Complete
                        row['Dependencies'] || null        // Dependencies
                    ];
                });
                for(var i=0;i<wbsDatas.length;i++){
                    allDatas.push(wbsDatas[i]);
                }
                console.log(allDatas);

               data.addColumn('string', 'Task ID');
               data.addColumn('string', 'Task Name');
               data.addColumn('string', 'Resource');
               data.addColumn('date', 'Start Date');
               data.addColumn('date', 'End Date');
               data.addColumn('number', 'Duration');
               data.addColumn('number', 'Percent Complete');
               data.addColumn('string', 'Dependencies');

               data.addRows(allDatas);

               console.log(allDatas);
            });
        }
        reader.readAsBinaryString(input.files[0]);

        // DataTable을 생성하고 데이터를 추가합니다.

        var options = {
            height: 400,
            gantt: {
                trackHeight: 30
            }
        };

        var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

        chart.draw(data, options);
    };
  </script>
</head>
<body>
    <div class="container">
        <div>
            파일 선택 : <input type="file" id="id_file_upload" onchange="drawChart(event)"/>
            <div id="chart_div"></div>
        </div>
    </div>
</body>
</html>