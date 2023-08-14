function tabMenu(){
    const tabList = document.querySelectorAll('.searchLogin-wrapper .list li');

    for(var i=0;i<tabList.length;i++){
        tabList[i].querySelector('.btn').addEventListener('click',function(e){
            e.preventDefault();
            for(var j=0;j<tabList.length;j++){
                tabList[j].classList.remove('is_on');
            }
            this.parseNode.classList.add('is_on');
        });
    }
}