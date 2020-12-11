<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="vo.DiaryVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="vo.CommentVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/diary_detail.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<%
	//로그인한 user객체가져오기
	MemberVO user = (MemberVO) request.getAttribute("user");

	//diary객체
	DiaryVO diary = (DiaryVO)request.getAttribute("diary");

	//이전 페이지가 뭔지
	String beforepage =(String)request.getAttribute("page");
	
	//로그인 사용자 이름
	String user_name = user.getUser_name();
	
	//작성자 이름
	String writer_name = diary.getWriter_name();
	
	//댓글 리스트
	ArrayList<CommentVO> commentlist = (ArrayList<CommentVO>)request.getAttribute("commentList");
	
	String feeling= diary.getFeeling();
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
	%>
	<div class="container">
		<div style="margin: 10px; margin-left: 550px;">
			<button type="button" id="edit_btn" class="btn hide">
				<img src="images/edit.png" width="35" height="35">
			</button>
			<button type="button" id="delete_btn" class="btn hide">
				<img src="images/delete2.png" width="35" height="35">
			</button>
			<button type="button" id="cancel_btn" class="btn">
				<img src="images/Icon_X.png" width="35" height="35">
			</button>
		</div>
		<div class="slide_container">
			<!-- <div>
				<button type="button" id="left_btn" class="btn slide_btn">
					<img src="images/Icon_Left.png" width="35" height="35">
				</button>
			</div> -->
			<div class="diary-container">
				<div class="diary">

					<h3 id="title" style="margin: 20px;">
						제목 :
						<%=diary.getTitle()%></h3>
					<p id="sub_title"
						style="text-align: right; margin: 10px; margin-right: 20px;"><%=diary.getDate()%>
						<%=feeling%></p>
					<p id="context"
						style="margin: 10px; margin-left: 20px; margin-right: 20px;"><%=diary.getContext()%></p>


				</div>
				<div class="comment-container">
					<div>
						<img src="images/user.png" width=30 height=30
							style="margin-top: 15px;" class="user_img">
						<p id="writer"
							style="font-weight: bold; display: inline; margin-right: 10px; margin-left: 4px;"><%=writer_name%></p>
						<img src="images/redbinheart.png" class="heart_img" width=30 height=30
							style="margin-top: 15px;" value="empty_heart">

					</div>

					<hr style="border: 1px #b1b0b0 solid; margin: 10px">
					<div class="comment-list">

						<!------- 댓글 리스트 가져오기 --------->
						<%
							for (int i = 0; i < commentlist.size(); i++) {
							//유저 이름 가져오기
							
						%>

						<div class="comment" id="<%=commentlist.get(i).getComment_id()%>">

							<img src="images/user.png" width=30 height=30
								style="margin-left: 10px; position: relative; top: 7px;">
							<strong
								style="margin-right: 10px; display: inline; margin-left: 10px;"><%=commentlist.get(i).getWriter_name()%></strong>
							<p class="comment-text" style="display: inline; font-weight: lighter;cursor: default;"><%=commentlist.get(i).getComment_context()%></p>

						</div>

						<%
							}
						%>
						<!-- ------------------------- -->
						<!-- -------댓글 작성---------- -->
					</div>
					<form action="addcomment.do" method="POST" onsubmit="return checkValue();">
						<div class="comment-write">
							<input type="hidden" name="diary_id" value="<%=diary.getDiary_id()%>"/>
							<input type="hidden" name="room_id" value="<%=diary.getRoom_id()%>"/>
							<input type="hidden" name="page" value="<%=beforepage%>"/>
							<input name="comment_context" id="comment"
								style="display: inline; position: relative; top: -10px;"
								type="text" placeholder="댓글 달기..">
							<button type="submit" id="plus_btn" class="btn" >
								<img src="images/plus1.png" width="25" height="25">
							</button>
						</div>
					</form>
					<!-- ------------------------- -->
				</div>
			</div>
			<!-- <div>
				<button type="button" id="right_btn" class="btn slide_btn">
					<img src="images/Icon_Right.png" width="35" height="35">
				</button>
			</div> -->
		</div>

	</div>
	<script>
		//************취소버튼*****************
		const cancel_btn = document.querySelector("#cancel_btn");
		
		cancel_btn.addEventListener('click', function() {
			<%if (beforepage.equals("main")) {//모든 다이어리 리스트%>

				location.href = "main.do";
			<%} else {//방 게시판%>
				location.href = "diaryList.do?roomId=<%=diary.getRoom_id()%>";
			<%}%>
		})
		
		
		///****************삭제 기능 */
		//삭제 수정 버튼
		const delete_btn=document.querySelector("#delete_btn");
		const edit_btn=document.querySelector("#edit_btn");
		
		if("<%=user.getUser_id()%>"=="<%=diary.getWriter_id()%>" ){
    		delete_btn.classList.remove('hide');
    		edit_btn.classList.remove('hide');
		}else if("<%=user.getUser_id()%>"=="${master_id}"){
			delete_btn.classList.remove('hide');
		}
		
		delete_btn.addEventListener("click",function(){
		    var confirm_delete = confirm("❗️ 정말 삭제하시겠습니까? ❗️");
		    if(confirm_delete == true){
		    	location.href="deletediary.do?diary_id=<%=diary.getDiary_id()%>&page=<%=beforepage%>&room_id=<%=diary.getRoom_id()%>";
		      
		    }
		    else if(confirm_delete == false){
		    
		    }     
		 })
		 ///****************수정 기능 */
		 edit_btn.addEventListener("click",function(){
		  
		    location.href="editdiary.do?diary_id=<%=diary.getDiary_id()%>&page=<%=beforepage%>";
		         
		 })
		 
		///****************좋아요 기능 */
		//좋아요 추가
	

		const heart_img = document.querySelector(".heart_img");
		if(${like}==true){
			heart_img.setAttribute('value', 'full_heart')
			heart_img.setAttribute('src', 'images/fullheart.png')
		}
		
		function change_heart() {
			let heart = heart_img.getAttribute('value');
			if (heart === 'empty_heart') {
				heart_img.setAttribute('value', 'full_heart')
				heart_img.setAttribute('src', 'images/fullheart.png')
				alert('좋아요를 추가합니다!')
				
				$.ajax({
     				url:'changelike.do?diary_id=<%=diary.getDiary_id()%>',
    				type : 'get',
    				success:function(result){
    					console.log(result);
    				}   
				})
      			
			} else {
				heart_img.setAttribute('value', 'empty_heart')
				heart_img.setAttribute('src', 'images/redbinheart.png')
				alert('좋아요를 삭제합니다!')
				$.ajax({
     				url:'changelike.do?diary_id=<%=diary.getDiary_id()%>',
    				type : 'get',
    				success:function(result){
    					console.log(result);
    				}   
      			})
			}
		}

		heart_img.addEventListener('click', change_heart);
	
		
		/////***댓글 길게보기
		const pTags=document.querySelectorAll('.comment-text');
		
		[].forEach.call(pTags,function(pTag){ 
			pTag.addEventListener("click",function(e){
				console.log(e.target);
				e.target.parentNode.classList.toggle('open');
			}); 
		});

		
		//댓글 작성 값유무확인
		function checkValue(){			
			if(!$('#comment').val()){
			
				return false;
			}
			return true;
		}
		
	</script>
</body>
</html>