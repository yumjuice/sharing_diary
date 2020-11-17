const write_btn= document.querySelector('#write_btn');
const cancel_btn=document.querySelector('#cancel_btn');
function value_check() {
    var check_count = document.getElementsByName("feeling").length;

    for (var i=0; i<check_count; i++) {
        if (document.getElementsByName("feeling")[i].checked == true) {
            //alert(document.getElementsByName("(NAME명)")[i].value);
            return document.getElementsByName("feeling")[i].value;

        }
    }
}

function add_diary(){
    var title=document.querySelector("#title").value;
    var feeling=value_check();
    var context=document.querySelector("#content").value;
    var imgaddr=document.querySelector("#imgaddr").value;
    //console.log(title,feeling,context,imgaddr);

    
    ///****로컬 스토리지에 작성한 일기 데이터 저장
    let diaryId=0;
    if ("diaryList" in localStorage) {
        var diaryList=JSON.parse(localStorage.getItem("diaryList"));
         diaryId=diaryList[diaryList.length-1]["diary_id"]+1;
    }
    else{var diaryList=[];}


    var d = new Date();
    var date=d.getFullYear()+"."+(d.getMonth()+1)+"."+d.getDate();
    console.log("date",date);

    var diary={
        "diary_id":diaryId,
        "date":date,
        "title":title,
        "feeling":feeling,
        "context":context,
        "imgaddr":imgaddr,
        "nickname":localStorage.getItem("nickname")
    }

    diaryList.push(diary);
    localStorage.setItem("diaryList",JSON.stringify(diaryList));
    //var x=JSON.parse(localStorage.getItem("diary"));
    alert("❤️일기가 작성되었습니다.❤️")
    location.href="diaryList.html";
}

write_btn.addEventListener('click',add_diary)
cancel_btn.addEventListener('click',function(){
    location.href="diaryList.html";
})