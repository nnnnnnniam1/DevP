google.charts.load('current', {'packages':['gantt']});
google.charts.setOnLoadCallback(drawChart);

function drawChart(){

    var taskData = "${taskList}"
    console.log(taskData[1]);
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Task ID');
    data.addColumn('string', 'Task Name');
    data.addColumn('string', 'Resource');
    data.addColumn('date', 'Start Date');
    data.addColumn('date', 'End Date');
    data.addColumn('number', 'Duration');
    data.addColumn('number', 'Percent Complete');
    data.addColumn('string', 'Dependencies');

    data.addRows(taskData);

    var options = {
        height: 400,
        gantt: {
            trackHeight: 30
        }
    };



    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

    chart.draw(data, options);

}