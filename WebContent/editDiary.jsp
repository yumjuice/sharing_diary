<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.DiaryVO"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/writeDiary.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</head>

<body>

<%
	DiaryVO diary = (DiaryVO) request.getAttribute("diary");
%>
    <div class="container">
        <div>
            <button type="button" id="cancel_btn" onclick="location.href='diarydetail.do?diary_id=<%=diary.getDiary_id() %>&room_id=<%=diary.getRoom_id()%>&page=${page}'"><img src="images/Icon_X.png" width="30" height="30"></button>
        </div>

        <!-- EditDiaryController에 일기 수정 요청 -->
		<div class="write-container">
        	<form action="updatediary.do" method="POST" onsubmit="return checkVaildate();">
           
                <input type="hidden" name="diary_id" value="<%=diary.getDiary_id() %>" />
                <input type="hidden" name="page" value="${page}" />
                <input type="hidden" name="room_id" value="<%=diary.getRoom_id() %>" />
                <label>제목
                    <input name='title' id='title' style="margin:10px;margin-top:20px" display="inline" type="text" value="<%=diary.getTitle() %>" />
                </label>
                <br>
                <div class="check-feeling" style="margin:10px">
                    <label><input type="radio" name="feeling" value="happy">😄</label>
                    <label><input type="radio" name="feeling" value="mad">🤬</label>
                    <label><input type="radio" name="feeling" value="sad">😭</label>
                    <label><input type="radio" name="feeling" value="surprise">😨</label>
                </div>
                <hr style="border: 1px solid; border-color: rgb(129, 129, 129); width:80%; margin-left:10%">

                <textarea name="context" style="margin:10px;" id="context" cols="40" rows="10"><%=diary.getContext()%></textarea>
                <br>
                <label>이미지 주소<input name='imgaddr' id='imgaddr' style="margin-left:10px;" type="text" value="<%=diary.getImgaddr()%>"></label>
                <br>
                <button style="float: right;margin:10px;margin-right:60px" type="submit" id="write_btn">수정</button>
            
        	</form>
        </div>
    </div>
</body>

<script>




function checkVaildate(){
	var confirm_edit = confirm("수정하시겠습니까?");
	var feeling = $(":input:radio[name=feeling]:checked").val();
	var title=document.querySelector('#title').value;
	var context=document.querySelector('#context').innerHTML;
	
	if(confirm_edit == true){
		
		if(title ==null || context==null){
			alert("제목과 내용을 입력해주세요");
			return false;
		}else if(feeling==null){
			alert("오늘의 감정을 선택해주세요");
			return false;
		}
		return true;
	}else return false;
}
</script>

</html>