document.getElementById('checkBtn').addEventListener('click', function(e){
    var authKey = [[%{authKey}]];
    var inputKey = document.getElementById("checkingCode");
    if(authKey == null){
        alert("")
    }
});