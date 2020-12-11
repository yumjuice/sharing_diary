 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 <%@page import="vo.RoomVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="vo.DiaryVO"%>
  <link rel="stylesheet" type="text/css" href="css/top.css">
  <link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<%
ArrayList<DiaryVO> likeDiaryList=(ArrayList<DiaryVO>) request.getAttribute("likeDiaryList");
ArrayList<RoomVO> room_list = (ArrayList<RoomVO>) request.getAttribute("roomList");
%>
<div class="top_container">
    <a href="main.do">
        <img style="float:left;margin-left:30px;" src="images/Pen_Logo.png">
    </a>
    <button type="button" class="hideBtn" id='profileBtn' >
        <img src="images/Icon_Self.png">
    </button>

    <span id="user_name" style="position:relative;top:-15px;left:-5px;"></span>

    <button type="button" class="hideBtn" data-toggle="modal" data-target="#likeListModal">
        <img src="images/Icon_Heart.png">
    </button>


    <button type="button" style="margin-right:30px;" onclick="location.href='logout.jsp';" id="logout_btn"><img src="images/Icon_LogOut.png" width="30" height="30"></button>

</div>
<div class="profile-menu hide">
	<ul>
		<li data-toggle="modal" data-target="#profileModal">내 정보 보기</li>
		
		<li data-toggle="modal" data-target="#makeRoom">방 생성하기</li>
		<li data-toggle="modal" data-target="#leaveRoomModal">방 탈퇴하기</li>
	</ul>
</div>



<!--내 정보 Modal -->
<div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">내 정보</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body profile_container">
        <div class="profile_name">
        	<div>아이디</div>
        	<div>이름</div>
        	<div>성별</div>
        	<div>생년월일</div>
        	<div>이메일</div>
        </div>
       
	        <div class="profile_text">
	         <form action="user.do" method="POST" id="editUserForm" onsubmit="return user_confirm();">
	        	<div style="margin:20px;margin-bottom:10px;color:black;height:30px;"><%=userVO.getUser_id()%></div>
	        	<input type="text" name="user_name" value="<%=userVO.getUser_name()%>">
	        	<input type="text" name="user_gender" value="<%=userVO.getUser_gender()%>">
	        	<input type="text" name="user_birth" value="<%=userVO.getUser_birth()%>">
	        	<input type="text" name="user_email" value="<%=userVO.getUser_email()%>">
	        </form>
	        </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" form="editUserForm"  class="btn btn-primary" style="background-color:rgb(239,137,152);border-color:rgb(239,137,152);">수정하기</button>
      </div>
    </div>
  </div>
</div>


<!--좋아요 목록 Modal -->
<div class="modal fade" id="likeListModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">좋아요 목록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body likeList_container">
        <ul>
        	<%for(int i=0;i<likeDiaryList.size();i++){ 
        	
        		DiaryVO likeDiary=likeDiaryList.get(i);%>
	        	<li onclick="location.href='diarydetail.do?room_id=<%=likeDiary.getRoom_id() %>&diary_id=<%=likeDiary.getDiary_id() %>&page=main'">
	        		<div class="likeList">
	        			<div class="diaryImg">
	        				<img src="<%=likeDiary.getImgaddr()%>"> 
	        			</div>
	        			<div class="diaryContext">
	        				<h5>🏠<%=likeDiary.getRoom_name()%> 🏠 <%=likeDiary.getWriter_name() %></h5>
	        				<p><%=likeDiary.getTitle() %> <%=likeDiary.getDate() %></p>
	        				<div><%=likeDiary.getContext() %></div>
	        			</div>
	        		</div>
	        	</li>
       		<%}%>
        
        </ul>
        
      </div>
    <!--   <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" form="editUserForm"  class="btn btn-primary" style="background-color:rgb(239,137,152);border-color:rgb(239,137,152);">수정하기</button>
      </div> -->
    </div>
  </div>
</div>
<!-- 방생성 Modal -->
	<div class="modal fade" id="makeRoom" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">방 추가하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="room.do" method="POST" onSubmit="addHidden()">
					<input type="hidden" name="inviteList" id="hiddenList" value="dddd" />
					<div class="modal-body">
						<div>
							<span>방 이름</span> <input type="text" name="room_name">
						</div>
						<div>
							<span>방 이미지 주소</span> <input type="text" name="room_img" id="room_img">
						</div>
						<div>
							<span>친구 추가</span> <input type="text" id="friend"
								onFocus="this.value='';return true;">
							<button type="button" class="hideBtn" id="addInvite">
								<img src="images/plus1.png" width="25" height="25">
							</button>
						</div>
						<ol id="inviteList">
						</ol>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn"
							style="background-color: rgb(239, 137, 152);">방 만들기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<!--방탈퇴 Modal -->
<div class="modal fade" id="leaveRoomModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">방 목록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body RoomList_container">
        <ul>
        	<%for(int i=0;i<room_list.size();i++){ 
        	
        		RoomVO room=room_list.get(i);%>
	        	<form action="room.do" method="POST" id="room<%=room.getRoom_id() %>">
	        	<div>
	        		<div class="RoomList">
	        			
		        			<input type="hidden" name="method" value="leave" />
		        			<input type="hidden" name="room_id" value="<%=room.getRoom_id() %>" />
		        			<div class="roomImg">
		        				<img src="<%=room.getRoom_img()%>"> 
		        			</div>
		        			<div class="roomContext">
		        				<h5><%=room.getRoom_name()%></h5>
		        			
		        			</div>
		        			<button type="button" class="leaveBtn" onclick="checkDeleteConfirm(<%=room.getRoom_id() %>)">
		        				<img style="width:30px;height:30px" src="images/back.png"> 
		        			</button>
		        		
	        		</div>
	        	</div>
	        	</form>
       		<%}%>
        
        </ul>
        
      </div>
    <!--   <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" form="editUserForm"  class="btn btn-primary" style="background-color:rgb(239,137,152);border-color:rgb(239,137,152);">수정하기</button>
      </div> -->
    </div>
  </div>
</div>


<script>
	const profileBtn=document.querySelector('#profileBtn')
	const profileMenu=document.querySelector('.profile-menu')
	
	
	const openAndCloseMenu = (e) => {
		
		  if (e.target.parentNode === profileBtn) {
			
			  e.stopPropagation();
			  profileMenu.classList.remove('hide')
		  }
		
		  else{
			  profileMenu.classList.add('hide')
		  }
		}

	
	window.addEventListener('click', openAndCloseMenu)
	
	
	
	
	
	//수정확인
	function user_confirm(){
		var isOk = confirm("❗️ 정말 수정하시겠습니까? ❗️");
		return isOk;
	}
	
	
	
	
	
	//*****방 생성 *****
	const inviteList = document.querySelector("#inviteList");
    const addBtn = document.querySelector("#addInvite");
    var invites = new Set();

    function addFriend(friend) {
        //var friend = document.querySelector("#friend")
        var li = document.createElement('li');
        var div = document.createElement('div');
        //<button type="button" id="addInvite"><img src="images/plus1.png" width="25" height="25"></button>
        var button = document.createElement('button');
        var img = document.createElement('img');

        
        button.classList.add("removeBtn");
        button.setAttribute('type', 'button');
        li.innerHTML = friend;
        li.setAttribute('value', friend);
        li.setAttribute('style', 'display:inline;');
        img.setAttribute('src', "images/back.png");
        img.setAttribute('width', '15');
        img.setAttribute('height', '15');


        button.appendChild(img);
        div.appendChild(li);
        div.appendChild(button);

        inviteList.appendChild(div);
        invites.add(friend);


    }
    function invite(){
    	 var friend = document.querySelector("#friend")
    	 //아이디가 존재하는지 확인
    	 $.ajax({
            url: 'checkuser.do?id=' + friend.value,
            type: 'get',
            success: function(result) {
                if(result==true){
                	addFriend(friend.value);
                	friend.setAttribute('value', "")
                }else{
                	alert("유효하지 않은 아이디입니다.");
                }
            }
        })
    	 
    }
	
    addBtn.addEventListener('click', invite);

	
	 ///방 추가하기 전 hidden value에 넣기
    function addHidden() {
		alert($("#room_img").val())
		 if($("#room_img").val()==null || $("#room_img").val()==""){
			 $("#room_img").val("images/Pen_Logo.png");
		 }
        var list = Array.from(invites);
        ///alert(list+typeof(list));
        //document.getElementById("inviteList").setAttribute('value',list);

        $("#hiddenList").val(list);
    }
	 
    function checkDeleteConfirm(id) {

    	var isOk = confirm("❗️ 정말 탈퇴하시겠습니까? ❗️");
		if(isOk){
			$("#room" + id).submit();
		}
    	return isOk;
    }
   

</script>

