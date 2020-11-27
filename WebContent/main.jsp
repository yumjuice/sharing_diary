<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="diary.DiaryDAO" %>
<%@page import ="diary.Diary" %>
<%@page import="user.UserDAO" %>
<%@page import="room.RoomDAO" %>
<%@page import="room.Room" %>
<%@page import ="user.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script
  src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
</head>
<body>


<%

String user_id = (String)session.getAttribute("id");
if(user_id==null){

	response.sendRedirect("index.jsp");
}
System.out.println(user_id+"id"); 

%>

<%
//main.do에서 유저이름, 다이어리 정보,  방정보 받기

//무한스크롤시 스크롤할때마다 getPage.do 에서 json데이터 받기
//////*****초기화
///DAO 생성
UserDAO userDAO=new UserDAO();
DiaryDAO diaryDAO=new DiaryDAO();
RoomDAO roomDAO=new RoomDAO();

//로그인 사용자 이름
String user_name=userDAO.getUser(user_id).getUser_name();
System.out.println(user_name+"user_name"); 
//참여중인 Room객체 리스트 반환
ArrayList<Room> room_list=roomDAO.getRoomList(user_id);

//총 페이지 수
int total_page=diaryDAO.getPageNum();
      
 %>
<!-- 헤더 삽입 -->
<%@ include file="header.jsp" %>


        <div class="body-container">
            <div class="diary_list">
         <%   
         if(total_page>=1){
         	ArrayList<Diary> list=diaryDAO.getAllList(1);
	
				for(int i=0;i<list.size();i++){
					//유저 객체 가져오기
					User user=userDAO.getUser(list.get(i).getWriter_id());
					//방 객체 가져오기
					
		%>
              	
     
              <div class="content_container">
                    <div class="title">
                        <p class="title_text">🏠<%= roomDAO.getRoomName(list.get(i).getRoom_id())%>🏠의 &nbsp;<%=user.getUser_name() %></p>
                    </div>
                    
                    <div class="diary-container">
                        <div class="img">
                            <img src="<%=list.get(i).getImgaddr()%>">
                        </div>
                        <div class="diary" onclick="location.href='diaryDetail.jsp?diary_id=<%=list.get(i).getDiary_id() %>'">
                        
                            <h3 class="title_text" >제목 : &nbsp;<%=list.get(i).getTitle() %></h3>
                            <p class="sub_title_text" ><%=list.get(i).getDate() %> &nbsp; <%=list.get(i).getFeeling() %></p>
                            <p class="context" ><%=list.get(i).getContext() %></p>
                        
                    
                        </div>
                    </div>
                </div>
              <%} }%>
  
      
              
            </div>

            <div class="room-container">
                <p id="room_name">❤️<%=user_name%>님의 방❤️</p>
                <hr style="margin-bottom: 10px;">
                <button type="button" id="plus_btn"><img src="images/plus1.png" width="25" height="25"></button>
                <%
                for(int i=0;i<room_list.size();i++){
                	
                
                %>
                    <div style="cursor:pointer;"onclick="location.href='diaryList.jsp?room_id=<%=room_list.get(i).getRoom_id() %>'">
                       <img class="room_img" src="images/Icon_Self_Line.png">
                       <span><%=room_list.get(i).getRoom_name() %></span>
                    </div>
                 <%}%>
                
            </div>
        </div>
        <script>
    
        const diary_list_container=document.querySelector(".diary_list")
      //이미지 추가 함수
        function add_diary(roomname, diary_id,nickname,imgaddr,feeling,date,title1,context){
            var container = document.createElement( 'div' );

            var title=document.createElement('div');
            var p=document.createElement('p');
            
            var diary_container=document.createElement('div');
            
            var img_container=document.createElement('div');
            var img=document.createElement('img');

            var diary=document.createElement('div');
            var title_text=document.createElement('h3');
            var sub_title=document.createElement('p');
            var context_text=document.createElement('p');

            container.classList.add('content_container')
            container.classList.add('fadein')
            title.classList.add('title')
            p.classList.add('title_text')
            diary_container.classList.add('diary-container')
            img_container.classList.add('img');
            diary.classList.add('diary');
            title_text.classList.add('title_text');
            sub_title.classList.add('sub_title_text')
            context_text.classList.add('context');

            diary.setAttribute('onclick','location.href="diaryDetail.jsp?diary_id='+diary_id+'"');
            img.setAttribute('src',imgaddr);
            p.innerHTML="🏠"+roomname+"🏠의 "+nickname;
            title_text.innerHTML="제목: "+title1;
            sub_title.innerHTML=date+" "+feeling;
            context_text.innerHTML=context;

           
           diary.appendChild(title_text);
           diary.appendChild(sub_title)
           diary.appendChild(context_text);

           img_container.appendChild(img);

           diary_container.appendChild(img_container);
           diary_container.appendChild(diary);

           title.appendChild(p);

           container.appendChild(title);
           container.appendChild(diary_container);



            diary_list_container.append(container);
        }
     

    
      

      //스크롤 내릴때마다 6개 추가 출력
      //***무한스크롤링 */
      var loading = false;
    
      var current_page = 2;   //불러올 페이지
   
          /*nextpageload function*/
          function next_load()
          {
    	  
                //서버에 데이터 요청하기 
                //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목
            	if(current_page><%=total_page%>) return;
              console.log(current_page+' diary load');
              /* 이미지 동적 추가 */
        	
             $.ajax({
     			url:'diarypage?page='+current_page,
    			type : 'get',
    
    			 success:function(diary_list){
      			 	    diary_list.forEach(diary=>{
      			 	    	//roomname, diary_id,nickname,imgaddr,feeling,date,title1,context
      			 	    	
      			 	    	
      			 		add_diary(diary["room_name"],diary["diary_id"],diary["writer_name"],diary["imgaddr"],diary["feeling"],diary["date"],diary["title"],diary["context"]);
      			 	})
      			  loading = false;   //실행 가능 상태로 변경
      			 	current_page++;
    			}
             })
            	 	
            
                  
                            
          }

          window.addEventListener('scroll',function(){
              //console.log(window.scrollY, document.documentElement.scrollTop);
              //console.log (document.body.scrollTop,document.body.scrollHeight,document.documentElement.clientHeight);
              if(document.body.scrollTop+100>=document.body.scrollHeight-document.documentElement.clientHeight ||window.scrollY+100>=document.body.scrollHeight-document.documentElement.clientHeight){
                  if(!loading)    //실행 가능 상태라면?
                  {
                      loading = true; //실행 불가능 상태로 변경
                      next_load(); 
                    
                      
                  }
                  else            //실행 불가능 상태라면?
                  {
                      //alert('다음페이지를 로딩중입니다.');  
                  }
              }
          });  

       


        </script>

</body>
</html>