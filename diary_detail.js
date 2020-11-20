const title=document.querySelector("#title");
const sub_title=document.querySelector("#sub_title");
const context=document.querySelector("#context");
const cancel_btn=document.querySelector("#cancel_btn");
const comment_list=document.querySelector(".comment-list");
const plus_btn=document.querySelector("#plus_btn");
const writer=document.querySelector("#writer");
const delete_btn=document.querySelector("#delete_btn");
const heart_img=document.querySelector(".heart_img")
const left_btn=document.querySelector("#left_btn");
const right_btn=document.querySelector("#right_btn");

//다이어리 id가져오기
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
  };

const diary_id=searchParam('id');


const check=searchParam('check');

//방 별 다이어리 게시판에서 온 경우 오른쪽왼쪽 버튼 없애기
if(check==1){
    right_btn.classList.add('hide');
    left_btn.classList.add('hide');
}

//몇번째 다이어리인지
let count=0;

//diary 객체 가져오기
function find_diary(id){
    let allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
    console.log(allDiaryList[0]["title"])
    
    for(var i=0,diary; diary=allDiaryList[i]; i++) {
	    if(diary["diary_id"]==id){
            
            return diary;
        }
        count++;
    }
}

let diary=find_diary(diary_id);

//작성 내용 초기화 하기

title.innerHTML="제목 : "+diary["title"];
sub_title.innerHTML=diary["date"]+" "+diary["feeling"];
context.innerHTML=diary["context"];

const nickname=diary["nickname"];

if(nickname==localStorage.getItem("nickname")){
    delete_btn.classList.remove('hide');
}

writer.innerHTML=nickname;

//하트 초기화 하기
//like [{ diary_id , nickname}]
if ("likes" in localStorage) {
    var likes=JSON.parse(localStorage.getItem("likes"));
    likes.forEach(like =>{
        if(like["diary_id"]===diary_id && like["nickname"]===localStorage.getItem("nickname")){
            heart_img.setAttribute('value','full_heart')
            heart_img.setAttribute('src','images/fullheart.png')
            return;
        }
    } );
    
}



//******댓글 가져오기******

//comment : comment_id, diary_id, nickname, context
function get_comment(comment){
    var div = document.createElement('div');
    var img = document.createElement( 'img' );
    var name = document.createElement( 'strong' );
    var context = document.createElement( 'p' );

    div.classList.add('comment');
    img.setAttribute('src','images/user.png')
    img.classList.add('user_img')
    name.innerHTML=comment["nickname"];
    name.classList.add('user_text');
    context.classList.add('comment_text');
    context.innerHTML=comment["context"];

    div.appendChild(img);
    div.appendChild(name);
    div.appendChild(context);
    
    comment_list.append(div);

    
}

if ("commentList" in localStorage) {
    var commentList=JSON.parse(localStorage.getItem("commentList"));
    commentList.forEach(comment =>{
        if(comment["diary_id"]===diary_id){
            get_comment(comment);
        }
    } );
    
}

////*************** */


///*****댓글 작성하기 */
//commentList 객체 있으면 가져오고 없으면 생성하기
//comment : comment_id, diary_id, nickname, context
function add_comment(){
    var context=document.querySelector("#comment").value;
    let commentId=0;
    let commentList=[];
    //로컬스토리지에 이미 댓글 리스트가 있을시
    if ("commentList" in localStorage) {
        console.log("잇");
        commentList=JSON.parse(localStorage.getItem("commentList"));
        commentId=commentList[0]["comment_id"]+1;
    }
    //없을시
    console.log(commentList);
   

    var comment={
        "comment_id":commentId,
        "diary_id":diary_id,
        "nickname":localStorage.getItem("nickname"),
        "context":context

    }
    commentList.unshift(comment);
    localStorage.setItem('commentList',JSON.stringify(commentList));
    location.reload()
}


plus_btn.addEventListener("click",add_comment);
cancel_btn.addEventListener('click',function(){
    if(check==1){
        var diarylist=JSON.parse(localStorage.getItem('diaryList'));
        var roomId=diarylist[0]["room_id"]
        location.href="diaryList.html?roomId="+roomId;
    }
    else{
        location.href="mainpage.html"
    }
})

function deleteDiary(){
    let allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
    newdiaryList=allDiaryList.filter(diary=>{
        return diary["diary_id"]!=diary_id
        //console.log(diary_id,diary["diary_id"]);

    })
    localStorage.setItem("allDiaryList",JSON.stringify(newdiaryList))

    let diaryList=JSON.parse(localStorage.getItem("diaryList"));
    newdiaryList=diaryList.filter(diary=>{
        return diary["diary_id"]!=diary_id
        //console.log(diary_id,diary["diary_id"]);

    })
    localStorage.setItem("diaryList",JSON.stringify(newdiaryList))
}

delete_btn.addEventListener("click",function(){
    var confirm_delete = confirm("❗️ 정말 삭제하시겠습니까? ❗️");
    if(confirm_delete == true){
      deleteDiary();
      if(check==1){
        history.back();
    }
    else{
        location.href="mainpage.html"
    }
    }
    else if(confirm_delete == false){
      
    }
})



///****************좋아요 기능 */
//좋아요 추가
//like [{ diary_id , nickname}]
function add_like(){
    let likes=[]
    if ("likes" in localStorage) {
        likes=JSON.parse(localStorage.getItem("likes"));
    }
    let like={
        "diary_id":diary_id,
        "nickname":localStorage.getItem("nickname")
    }
    likes.push(like);
    localStorage.setItem("likes",JSON.stringify(likes));

}

function remove_like(){
    let likes=JSON.parse(localStorage.getItem("likes"));
    let new_likes=likes.filter(like=>{
        return !(like["diary_id"]==diary_id && like["nickname"]==localStorage.getItem('nickname'))
    })
    localStorage.setItem("likes",JSON.stringify(new_likes));
}



function change_heart(){
    let heart=heart_img.getAttribute('value');
    if(heart==='empty_heart'){
        heart_img.setAttribute('value','full_heart')
        heart_img.setAttribute('src','images/fullheart.png')
        alert('좋아요를 추가합니다!')
        add_like();
    }else{
        heart_img.setAttribute('value','empty_heart')
        heart_img.setAttribute('src','images/heart.png')
        alert('좋아요를 삭제합니다!')
        remove_like();
    }
}

heart_img.addEventListener('click',change_heart);



///////////////////////////////////

console.log('count',count)

left_btn.addEventListener('click',function(){
    if(count==0) return;
    let allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
    let next_id=allDiaryList[--count]["diary_id"];
    location.href="diary_detail.html?id="+next_id;
})

right_btn.addEventListener('click',function(){
    
    let allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
    if(count>=allDiaryList.length-1) return;
    let next_id=allDiaryList[++count]["diary_id"];
    location.href="diary_detail.html?id="+next_id;
})

