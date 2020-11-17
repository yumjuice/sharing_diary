const title=document.querySelector("#title");
const sub_title=document.querySelector("#sub_title");
const context=document.querySelector("#context");
const cancel_btn=document.querySelector("#cancel_btn");
const comment_list=document.querySelector(".comment_list");
const plus_btn=document.querySelector("#plus_btn");

//다이어리 id가져오기
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
  };

const diary_id=searchParam('id');


//diary 객체 가져오기
function find_diary(id){
    let diaryList=JSON.parse(localStorage.getItem("diaryList"));
    console.log(diaryList[0]["title"])
    
    for(var i=0,diary; diary=diaryList[i]; i++) {
	    if(diary["diary_id"]==id){
            return diary;
        }
    }
}

let diary=find_diary(diary_id);

title.innerHTML="제목 : "+diary["title"];
sub_title.innerHTML=diary["date"]+" "+diary["feeling"];
context.innerHTML=diary["context"];

cancel_btn.addEventListener('click',function(){
    location.href="diaryList.html";
})




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
    div.appendChild(strong);
    div.appendChild(name);
    
    comment_list.prepend(div);

    
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
        let commentList=JSON.parse(localStorage.getItem("commentList"));
        commentId=commentList[commentList.length-1]["comment_id"]+1;
    }
    //없을시
    
   

    var comment={
        "comment_id":commentId,
        "diary_id":diary_id,
        "nickname":localStorage.getItem("nickname"),
        "context":context

    }
    commentList.push(comment);
    localStorage.setItem('commentList',commentList);
    location.reload()
}


plus_btn.addEventListener("click",add_comment);
cancel_btn.addEventListener('click',function(){
    location.href="diaryList.html";
})