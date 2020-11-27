<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/writeDiary.css">
        
</head>
<body>

<%
int room_id=Integer.parseInt(request.getParameter("room_id"));
%>
<div class="container">
            <div>
            <button type="button" id="cancel_btn" onclick="location.href='diaryList.jsp?room_id=<%=room_id%>'"><img src="images/Icon_X.png" width="30" height="30"></button>
            </div>
            
            <!-- diary servlet에 일기 작성 요청 -->
            
            <form action="diary?room_id=<%=room_id %>" method="POST">
            <div class="write-container">
                <label>제목
                    <input name='title' id='title' style="margin:10px;margin-top:20px" display="inline" type="text" placeholder="제목을 입력해주세요"/>
                </label>
                <br>
                <div class="check-feeling" style="margin:10px">
                    <label><input type="radio" name="feeling" value="😄">😄</label>
                    <label><input type="radio" name="feeling" value="🤬">🤬</label>
                    <label><input type="radio" name="feeling" value="😭">😭</label>
                    <label><input type="radio" name="feeling" value="😨">😨</label>
                </div>
                <hr style="border: 1px solid; border-color: rgb(129, 129, 129); width:80%; margin-left:10%">
                
                <textarea name="context" style="margin:10px;" id="content" cols="40" rows="10" placeholder="내용을 입력해주세요" ></textarea>
                <br>
                <label>이미지 주소<input name='imgaddr' id='imgaddr' style="margin-left:10px;" type="text"></label>
                <br>
                <button style="float: right;margin:10px;margin-right:60px" type="submit" id="write_btn">작성</button>
            </div>
            </form>
        </div>
     
       
</body>
</html>