<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="vo.RoomVO" %>
<%@page import ="vo.MemberVO" %>
<%@page import ="vo.DiaryVO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/diaryList.css">
          <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<script>
 if("${alert}"!=""){

	 alert("${alert}");
 }
</script>
 
<%

String user_id = (String)session.getAttribute("id");

if(user_id==null){
	response.sendRedirect("login.jsp");
}


//////*****초기화
///DAO 생성
MemberVO userVO=(MemberVO)request.getAttribute("user");
RoomVO roomVO=(RoomVO)request.getAttribute("room");


//로그인 사용자 이름
String user_name=userVO.getUser_name();
//방이름
String room_name=roomVO.getRoom_name();


//참여중인 User객체 리스트 반환

ArrayList<MemberVO> user_list=(ArrayList<MemberVO>)request.getAttribute("memberList");


%>
<!-- 헤더 삽입 -->
<%@ include file="header.jsp" %>

<div class="header_container">
    <div class="box">
        <img class="profile" src="<%=roomVO.getRoom_img()%>">
    </div>


    <div>
        <h1 id="roomName" style="display:inline"><%=room_name %></h1>
        <button type="button" id="setting_btn" data-toggle="modal" data-target="#settingRoom">
					<img src="images/plus1.png" width="25" height="25">
		</button>
    
        <hr style="border-color:rgb(218, 134, 146); background-color:rgb(218, 134, 146)">
        <span>
            <%for (int i=0;i<user_list.size();i++){
                %> <%=user_list.get(i).getUser_name() %> &nbsp;

            <% }%>

        </span>
    </div>
</div>


<div style="text-align:right; margin-top:100px;">
    <span class="checkbox">
        <input id="showMine" type="checkbox" style="margin-right:5px;">내가 쓴 글만 보기
    </span>
    <button type="button" id="write_btn" onclick="location.href='writeDiary.jsp?room_id=<%=roomVO.getRoom_id()%>'"><img src="images/Icon_Write_Line.png"></button>
    <span style="padding-right:40px;position:relative;top:-12px;">글쓰기</span>
</div>
<hr style="color:rgb(218, 134, 146); background-color:rgb(218, 134, 146);margin-left:100px;margin-right:100px;">
<div class="diary_list">
    <div class="grid-container">

		<!-- 다이어리 리스트 -->

    </div>
</div>

<!-- Modal -->
	<div class="modal fade" id="settingRoom" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">방 정보 수정</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="updateRoom.do" method="POST" onSubmit="addHiddenValue()">
					<input type="hidden" name="addFriendList" id="addFriendList" value="dddd" />
					<input type="hidden" name="removeFriendList" id="removeFriendList" value="dddd" />
					<input type="hidden" name="room_id" value="<%=roomVO.getRoom_id()%>" />
					<div class="modal-body">
						<div>
							<span>방 이름</span> <input type="text" name="room_name" value="<%=room_name %>">
						</div>
						<div>
							<span>방 이미지 주소</span> <input type="text" name="room_img" value="<%=roomVO.getRoom_img()%>">
						</div>
						<div>
							<span>친구 추가</span> <input type="text" id="friend"
								onFocus="this.value='';return true;">
							<button type="button" class="hideBtn" id="addInvite">
								<img src="images/plus1.png" style="width:25px;height:25px;">
							</button>
						</div>
						<ol id="inviteList">
							<%for (int i=0;i<user_list.size();i++){
								if(roomVO.getMaster_id().equals(user_list.get(i).getUser_id()))
									continue;
							%>
                				<div value="<%=user_list.get(i).getUser_id()%>">
                					<li style="display:inline"><%=user_list.get(i).getUser_id() %></li>
                					<button type="button" class="removeBtn"><img src="images/back.png" style="width:15px;height:15px"/></button>
                				</div>
   					        <% }%>
						</ol>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">취소</button>
						<button type="submit" class="btn"
							style="background-color: rgb(239, 137, 152);">수정하기</button>
						<button type="submit" class="btn"
							style="background-color: rgb(239, 137, 152);">삭제하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<script>
	const setting_btn=document.querySelector("#setting_btn");
	const grid = document.querySelector(".grid-container");
	const checkBox = document.querySelector("#showMine");
    
	//방장만 방수정아이콘이 보이게
	<%
	if (!roomVO.getMaster_id().equals(userVO.getUser_id())){%>
	    setting_btn.classList.add('none');
	<%}%>
	
	//출력한 다이어리 갯수
    var count = 0;

    let check = false; //내가 작성한 글만 보기 체크=true
  
    
    //방에 해당하는 다이어리 리스트 가져오기
    var diaryList = [] 
    <%
    ArrayList < DiaryVO > diaryList1 = (ArrayList < DiaryVO > ) request.getAttribute("diaryList");
    for (int i = 0; i < diaryList1.size(); i++) {
        DiaryVO diary = diaryList1.get(i); %>
        var diary = {
            "diary_id": "<%=diary.getDiary_id()%>",
            "writer_id": "<%=diary.getWriter_id()%>",
            "writer_name": "<%=diary.getWriter_name()%>",
            "imgaddr": "<%=diary.getImgaddr()%>",
            "feeling": "<%=diary.getFeeling()%>",
            "date": "<%=diary.getDate()%>",
            "title": "<%=diary.getTitle()%>",
        }
        diaryList.push(diary); 
    <%} %>



    //먼저 6개만 추가	 	
    for (var i = 0; i < 6; i++) {

        if (count >= diaryList.length) break;

        add_diary(diaryList[i]["diary_id"], diaryList[i]["writer_id"], diaryList[i]["writer_name"], diaryList[i]["imgaddr"], diaryList[i]["feeling"], 0, diaryList[i]["date"], diaryList[i]["title"]);
        count++;
    }

    //이미지 추가 함수
    function add_diary(id, writer_id, writer_name, imgaddr, feeling, comment_count = 0, date, title) {
        var article = document.createElement('article');
        var titletag = document.createElement('a');

        var div = document.createElement('div');
        var imagelink = document.createElement('a');
        var image = document.createElement('img');

        var description = document.createElement('p');

        switch (feeling) {
            case "happy":
                feeling = "😄";
                break;
            case "sad":
                feeling = "😭";
                break;

            case "mad":
                feeling = "🤬";
                break;
            case "surprise":
                feeling = "😨";
                break;
        }

        article.classList.add('article-listing');
        article.classList.add('fadein');

        titletag.classList.add('article-title');
        div.classList.add('article-image');
        description.classList.add('description');

        titletag.innerHTML = title + feeling;
        description.innerHTML = date + " " + writer_name

        description.setAttribute('value', writer_id);
        article.setAttribute('id', id)
        image.setAttribute('src', imgaddr)
       
        titletag.setAttribute('href', "diarydetail.do?diary_id=" + id +"&room_id=<%=roomVO.getRoom_id()%>&page=room") //제목 클릭시 이동 주소
        imagelink.setAttribute('href', "diaryDetail.do?diary_id="+ id +"&room_id=<%=roomVO.getRoom_id()%>&page=room"); //이미지 클릭시 이동 주소   

        div.appendChild(imagelink);
        div.appendChild(image);
        article.appendChild(titletag);
        article.appendChild(div);
        article.appendChild(description);

        grid.append(article);
    }








    // //**********무한스크롤링 **********/
    //스크롤 내릴때마다 6개 추가 출력

    var isloading = false; //중복실행여부 확인 변수
 
    function next_load() {
        //서버에 데이터 요청하기 
        //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목

        console.log(count + ' diary load');
        /* 이미지 동적 추가 */
        for (var x = count; x < count + 6; x++) {
            if (count >= diaryList.length) return;
            add_diary(diaryList[x]["diary_id"], diaryList[x]["writer_id"], diaryList[x]["writer_name"], diaryList[x]["imgaddr"], diaryList[x]["feeling"], 0, diaryList[x]["date"], diaryList[x]["title"]);
            count++; //다이어리 갯수 증가
        }

        isloading = false; //실행 가능 상태로 변경

    }

    window.addEventListener('scroll', function() {
        if (document.body.scrollTop + 100 >= document.body.scrollHeight - document.documentElement.clientHeight || window.scrollY + 100 >= document.body.scrollHeight - document.documentElement.clientHeight) {
            if (!isloading) //실행 가능 상태라면?
            {

                isloading = true; //실행 불가능 상태로 변경
                next_load();
                if (check) {

                    add_hideclass();
                } else {
                    remove_hideclass();
                }
            } else //실행 불가능 상태라면?
            {
                //alert('다음페이지를 로딩중입니다.');  
            }
        }
    });


    //////*********내가 쓴 글만 보기*************

    function add_hideclass() {
        let articles = document.querySelectorAll(".article-listing");
        articles.forEach(article => {
            let writer_id = article.querySelector('.description').getAttribute('value');
            if (writer_id != "<%=user_id%>") {
                article.classList.add('hide');
            }
        });


    }

    function remove_hideclass() {
        let articles = document.querySelectorAll(".article-listing");
        articles.forEach(article => {
            let writer_id = article.querySelector('.description').getAttribute('value');
            if (writer_id != "<%=user_id%>") {
                article.classList.remove('hide');
            }
        });


    }

    function showOnlyMine() {

        check = checkBox.checked;
        //show_mine=true;
        //다른 id에 히든 클래스 넣기
        console.log(check);
        if (check) {

            add_hideclass();
        } else {
            remove_hideclass();
        }

    }



    checkBox.addEventListener('click', showOnlyMine)
    
    
    
    //*************방 수정하기 *************
    const inviteList = document.querySelector("#inviteList");
    const addFriendBtn = document.querySelector("#addInvite");
   	///const removeFriendBtns= document.querySelectorAll(".removeBtn");
   	
   	var addInvites = new Set();
   	var removeInvites=new Set();
   	
   	//친구추가
   	function addFriend(friend){
   	//var friend = document.querySelector("#friend")
        var li = document.createElement('li');
        var div = document.createElement('div');
        //<button type="button" id="addInvite"><img src="images/plus1.png" width="25" height="25"></button>
        var button = document.createElement('button');
        var img = document.createElement('img');

        
        button.classList.add("removeBtn");
        button.setAttribute('type', 'button');
        li.innerHTML = friend;
        div.setAttribute('value', friend);
        li.setAttribute('style', 'display:inline;');
        img.setAttribute('src', "images/back.png");
        img.setAttribute('style', 'width:15px;height:15px');
        


        button.appendChild(img);
        div.appendChild(li);
        div.appendChild(button);

        inviteList.appendChild(div);
        addInvites.add(friend);
   	
   		
   	}
   	
   	//추가버튼클릭시
    function invite(){
   	 var friend = document.querySelector("#friend")
   	 
   	 //아이디가 존재하는지 확인
   	 $.ajax({
           url: 'checkuser.do?id=' + friend.value,
           type: 'get',
           success: function(result) {
        	   console.log("result",result);
               if(result==true){
               	addFriend(friend.value);
               	friend.setAttribute('value', "")
               }else{
               	alert("유효하지 않은 아이디입니다.");
               }
           }
       })
   	 
   }
    
   	//친구삭제버튼클릭시
   	function removeFriend(event){
   		
   		const removeDiv=event.target.parentNode.parentNode;
   		const removeFriend=removeDiv.getAttribute('value');
   		
   		var confirm_delete = confirm("❗"+removeFriend+"님을 정말 삭제하시겠습니까? ❗️");
	    if(confirm_delete == true){
	    	inviteList.removeChild(removeDiv);
	   		if(addInvites.has(removeFriend)){
	   			addInvites.delete(removeFriend);	
	   		}else{
	   			removeInvites.add(removeFriend);
	   		}
	   			
	   
	    }
   		
   		
   	}
  	///방 수정하기 전 hidden value에 넣기
    function addHiddenValue() {

       var addlist = Array.from(addInvites);
       $("#addFriendList").val(addlist);
       var removelist = Array.from(removeInvites);
       $("#removeFriendList").val(removelist);
    }
   	
   	addFriendBtn.addEventListener('click',invite,false);
   	//removeFriendBtn.addEventListener('click',removeFriend,false);
   	$(document).on("click",".removeBtn",removeFriend);
  	
   	
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>


</body>

</html>