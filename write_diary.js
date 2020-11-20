const write_btn= document.querySelector('#write_btn');
const cancel_btn=document.querySelector('#cancel_btn');

//room id가져오기
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
  };


const roomId=searchParam('roomId');

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
   
    let roomname="";
    
    if ("allDiaryList" in localStorage) {
        var allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
         diaryId=allDiaryList[0]["diary_id"]+1;
         var diaryList=JSON.parse(localStorage.getItem("diaryList"));
         
         roomname=diaryList[0]["roomName"]
         
    }
    else{var diaryList=[];}

    console.log(diaryId);

    var d = new Date();
    var date=d.getFullYear()+"."+(d.getMonth()+1)+"."+d.getDate();
    console.log("date",date);

    var diary={
        "room_id":roomId,
        "room_name":roomname,
        "diary_id":diaryId,
        "date":date,
        "title":title,
        "feeling":feeling,
        "context":context,
        "imgaddr":imgaddr,
        "nickname":localStorage.getItem("nickname")
    }

    diaryList.unshift(diary);
    console.log(JSON.stringify(diaryList));
    allDiaryList.unshift(diary);
    
    localStorage.setItem("diaryList",JSON.stringify(diaryList));
    localStorage.setItem("allDiaryList",JSON.stringify(allDiaryList));
    //var x=JSON.parse(localStorage.getItem("diary"));
    alert("❤️일기가 작성되었습니다.❤️")
    history.back();    
}

write_btn.addEventListener('click',add_diary)
cancel_btn.addEventListener('click',function(){
    location.href="diaryList.html?roomId="+roomId;
})