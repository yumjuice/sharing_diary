 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
  <link rel="stylesheet" type="text/css" href="css/top.css">
  <link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>


<div class="top_container">
    <a href="main.do">
        <img style="float:left;margin-left:30px;" src="images/Pen_Logo.png">
    </a>
    <button type="button" class="hideBtn" id='profileBtn' >
        <img src="images/Icon_Self.png">
    </button>

    <span id="user_name" style="position:relative;top:-15px;left:-5px;"></span>

    <a href="#">
        <img src="images/Icon_Heart.png">
    </a>


    <button type="button" style="margin-right:30px;" onclick="location.href='logout.jsp';" id="logout_btn"><img src="images/Icon_LogOut.png" width="30" height="30"></button>

</div>
<div class="profile-menu hide">
	<ul>
		<li data-toggle="modal" data-target="#profileModal">내 정보 보기</li>
		<li>좋아요 목록 보기</li>
		<li>방 생성하기</li>
		<li>방 탈퇴하기</li>
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
	        	<div style="margin:20px;margin-bottom:10px;color:black;height:30px;">dddd</div>
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






<script>
	const profileBtn=document.querySelector('#profileBtn')
	const profileMenu=document.querySelector('.profile-menu')
	
	function toggle(e){
		profileMenu.classList.toggle('hide');
	}

	profileBtn.addEventListener('click', toggle)
	
	function user_confirm(){
		var isOk = confirm("❗️ 정말 삭제하시겠습니까? ❗️");
		return isOk;
	}
	
</script>

