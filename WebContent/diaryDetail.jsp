<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@page import ="diary.DiaryDAO" %>
    <%@page import ="diary.Diary" %>
<%@page import="user.UserDAO" %>
<%@page import="room.RoomDAO" %>
<%@page import="like.LikeDAO" %>

<%@page import="room.Room" %>
<%@page import ="user.User" %>
<%@page import="comment.CommentDAO" %>
<%@page import="comment.Comment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/diary_detail.css">
        
</head>
<body>
<%

//로그인한 userid가져오기
String user_id = (String)session.getAttribute("id");

///세션 없을시 로그인 페이지로 이동
if(user_id==null){
	response.sendRedirect("login.jsp");
}

//diary_id가져오기

int diary_id=Integer.parseInt(request.getParameter("diary_id"));

//room_id 가져오기
int room_id;
if(request.getParameter("room_id")!=null){
room_id=Integer.parseInt(request.getParameter("room_id"));
}else{
	room_id=0; //메인게시판
}
//////*****초기화
///DAO 생성
UserDAO userDAO=new UserDAO();
DiaryDAO diaryDAO=new DiaryDAO();
CommentDAO commentDAO=new CommentDAO();


//로그인 사용자 이름
String user_name=userDAO.getUser(user_id).getUser_name();
//다이어리 객체
Diary diary = diaryDAO.getDiary(diary_id);
//작성자 이름
String writer_name=userDAO.getUser(diary.getWriter_id()).getUser_name();

//댓글 리스트
ArrayList<Comment> commentlist=commentDAO.getComment(diary_id);
%>
 <div class="container">
            <div style="margin:10px;margin-left:550px;">
                
                <button type="button" id="delete_btn" class="btn hide"><img src="images/garbage.png" width="35" height="35"></button>
                <button type="button" id="cancel_btn" class="btn"><img src="images/Icon_X.png" width="35" height="35"></button>
            </div>
            <div class="slide_container">
                <div>
                    <button type="button" id="left_btn" class="btn slide_btn"><img src="images/Icon_Left.png" width="35" height="35"></button>   
                </div>
                <div class="diary-container">
                    <div class="diary">
                       
                            <h3 id="title" style="margin:20px; ">제목 : <%=diary.getTitle()%></h3>
                            <p id="sub_title" style="text-align:right;margin:10px;margin-right:20px;"><%=diary.getDate() %> <%=diary.getFeeling() %></p>
                            <p id="context" style="margin:10px ; margin-left:20px;margin-right:20px;"><%=diary.getContext() %></p>
                        
                       
                    </div>
                    <div class="comment-container">
                        <div>
                            <img src="images/user.png" width=30 height=30 style="margin-top:15px;" class="user_img">
                            <p id="writer" style="font-weight:bold; display: inline; margin-right:10px;margin-left:4px;"><%=writer_name %></p>
                            <img src="images/heart.png" class="heart_img" width=30 height=30 style="margin-top:15px;" value="empty_heart">
                           
                        </div>
                        
                        <hr style="border:1px #b1b0b0 solid; margin:10px">
                        <div class="comment-list">
                        
                        <!------- 댓글 리스트 가져오기 --------->
                        <%
                        for(int i=0;i<commentlist.size();i++){
        					//유저 이름 가져오기
        					String comment_writer_name =userDAO.getUser(commentlist.get(i).getComment_writer_id()).getUser_name();
                        
                        %>
                            
                            <div class="comment" id="<%=commentlist.get(i).getComment_id()%>">
                                
                                <img src="images/user.png" width=30 height=30 style="margin-left:10px;position:relative;top:7px; "> 
                                <strong style="margin-right:10px;display: inline;margin-left:10px;"><%=comment_writer_name %></strong>
                                <p style="display:inline; font-weight: lighter;"><%=commentlist.get(i).getComment_context() %></p>
                               
                            </div>
                             
                             <%} %>
                         <!-- ------------------------- -->
                         <!-- -------댓글 작성---------- -->
                        </div>
                        <form action="comment?diary_id=<%=diary_id %>" method="POST">
                        <div class="comment-write">
                            <input name="comment_context" id="comment" style="display: inline;position:relative;top:-10px; " type="text" placeholder="댓글 달기..">
                            <button type="submit" id="plus_btn" class="btn"><img src="images/plus1.png" width="25" height="25"></button>
                        </div>
                        </form>
                          <!-- ------------------------- -->
                    </div>
                </div>
                <div>
                    <button type="button" id="right_btn" class="btn slide_btn"><img src="images/Icon_Right.png" width="35" height="35"></button>     
                </div>
            </div>
            
        </div>
        <script>
        
        //************취소버튼*****************
        const cancel_btn=document.querySelector("#cancel_btn");
        var room_id=<%=room_id%>
        cancel_btn.addEventListener('click',function(){
            if(room_id==0){//모든 다이어리 리스트
               
                location.href="main.jsp";
            }
            else{//방 게시판
                location.href="diaryList.jsp?room_id="+room_id;
            }
        })
        
        
        	///****************좋아요 기능 */
//좋아요 추가
//like [{ diary_id , nickname}]
        
        const heart_img=document.querySelector(".heart_img")
        <%
        
        LikeDAO likeDAO=new LikeDAO();
        
        boolean result;
        %>



function change_heart(){
    let heart=heart_img.getAttribute('value');
    if(heart==='empty_heart'){
        heart_img.setAttribute('value','full_heart')
        heart_img.setAttribute('src','images/fullheart.png')
        alert('좋아요를 추가합니다!')
        <%result= likeDAO.addLike(user_id,diary_id);
        if( result==false){
        	%>alert('오류가 발생하였습니다!')
        <%}
        %>
    }else{
        heart_img.setAttribute('value','empty_heart')
        heart_img.setAttribute('src','images/heart.png')
        alert('좋아요를 삭제합니다!')
        <%result= likeDAO.removeLike(user_id,diary_id);
        if( result==false){%>
        	alert('오류가 발생하였습니다!')
       <% }
        %>
    }
}

heart_img.addEventListener('click',change_heart);


        </script>
</body>
</html>