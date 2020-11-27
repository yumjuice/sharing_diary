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
<link rel="stylesheet" type="text/css" href="css/diaryList.css">
          <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<%

String user_id = (String)session.getAttribute("id");

if(user_id==null){
	response.sendRedirect("login.jsp");
}


//////*****초기화
///DAO 생성
UserDAO userDAO=new UserDAO();
DiaryDAO diaryDAO=new DiaryDAO();
RoomDAO roomDAO=new RoomDAO();

int room_id=Integer.parseInt(request.getParameter("room_id"));
//로그인 사용자 이름
String user_name=userDAO.getUser(user_id).getUser_name();
//참여중인 방 이름
String room_name=roomDAO.getRoomName(room_id);


//참여중인 User객체 리스트 반환

//ArrayList<User> user_list=userDAO.getUserList(room_id);


%>
<!-- 헤더 삽입 -->
<%@ include file="header.jsp" %>

 <div class="header_container">
            <div id="group_img">
                <img src="images/Pen_Logo.png">
            </div>
            <div>
                <h1 id="roomName"><%=room_name %></h1>
                <hr style="border-color:rgb(218, 134, 146); background-color:rgb(218, 134, 146)">
                <span>보라돌이, 뚜비, 나나, 뽀</span>
            </div>
        </div>
        <div style="text-align:right; margin-top:100px;">
        <span class="checkbox">
           <input id="showMine" type="checkbox" style="margin-right:5px;">내가 쓴 글만 보기
         </span>
        <button type="button" id="write_btn" onclick="location.href='writeDiary.jsp?room_id=<%=room_id%>'"><img src="images/Icon_Write_Line.png"></button>
            <span style="padding-right:40px;position:relative;top:-12px;">글쓰기</span>
        </div>
        <hr style="border-color: rgb(218, 134, 146);margin-left:100px;margin-right:100px;">
        <div class="diary_list">
            <div class="grid-container">

                
            
              </div>
        </div>
        <script>
        //출력한 다이어리 갯수/ 일단 6개만 출력
        var count=0;
      
      
        const grid=document.querySelector(".grid-container");
      
        const checkBox=document.querySelector("#showMine");
        
        let check=false; //내가 작성한 글만 보기 체크=true
        var diaryList=[];
        //방에 해당하는 다이어리 리스트 가져오기
        $.ajax({
 			url:'diarylist?room_id=<%=room_id%>',
			type : 'get',

			 success:function(diary_list){
			 	diaryList=diary_list;
			 	
			 	  for(var i=0;i<6;i++){
			        	
			        	 if(count>=diaryList.length) break;
			        
			        	 add_diary(diaryList[i]["diary_id"],diaryList[i]["writer_id"],diaryList[i]["writer_name"],diaryList[i]["imgaddr"],diaryList[i]["feeling"],0,diaryList[i]["date"],diaryList[i]["title"]);
			             count++;
			         }
			 }
        })
      //이미지 추가 함수
      function add_diary(id,writer_id,writer_name,imgaddr,feeling,comment_count=0,date,title){
          var article = document.createElement( 'article' );
          var titletag=document.createElement('a');
          
          var div=document.createElement('div');
          var imagelink=document.createElement('a');
          var image=document.createElement('img');

          var description=document.createElement('p');

         
          article.classList.add('article-listing');
          article.classList.add('fadein');
        
          titletag.classList.add('article-title');
          div.classList.add('article-image');
          description.classList.add('description');

          titletag.innerHTML=title+feeling;
          description.innerHTML=date+" "+writer_name
         
          description.setAttribute('value',writer_id);
          article.setAttribute('id',id)
          image.setAttribute('src',imgaddr) 
          titletag.setAttribute('href',"diaryDetail.jsp?diary_id="+id+"&room_id=<%=room_id%>") //제목 클릭시 이동 주소
          imagelink.setAttribute('href',"diaryDetail.jsp?diary_id="+id+"&room_id=<%=room_id%>") ; //이미지 클릭시 이동 주소   
          
          div.appendChild(imagelink);
          div.appendChild(image);
          article.appendChild(titletag);
          article.appendChild(div);
          article.appendChild(description);

          grid.append(article);
      }

   
       
          
          
             
          
      
       // //**********무한스크롤링 **********/
      //스크롤 내릴때마다 6개 추가 출력
     
      var loading = false;    //중복실행여부 확인 변수
      ///var page = 1;   //불러올 페이지

         
          function next_load()
          {
                //서버에 데이터 요청하기 
                //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목
                      
              console.log(count+' diary load');
              /* 이미지 동적 추가 */
              for (var x=count;x<count+6;x++)   
                  {
                      if(count>=diaryList.length) return;
                      add_diary(diaryList[x]["diary_id"],diaryList[x]["writer_id"],diaryList[x]["writer_name"],diaryList[x]["imgaddr"],diaryList[x]["feeling"],0,diaryList[x]["date"],diaryList[x]["title"]);
                     
                                    
                      count++; //다이어리 갯수 증가
                  }
                 
                  loading = false;    //실행 가능 상태로 변경
                            
          }
         
          window.addEventListener('scroll',function(){
              //console.log(window.scrollY, document.documentElement.scrollTop);
              //console.log (document.body.scrollTop,document.body.scrollHeight,document.documentElement.clientHeight);
              if(document.body.scrollTop+100>=document.body.scrollHeight-document.documentElement.clientHeight ||window.scrollY+100>=document.body.scrollHeight-document.documentElement.clientHeight){
                  if(!loading)    //실행 가능 상태라면?
                  {
                      loading = true; //실행 불가능 상태로 변경
                      next_load(); 
                      if(check){
                  
                          add_hideclass();
                      }else{
                          remove_hideclass();
                      }
                  }
                  else            //실행 불가능 상태라면?
                  {
                      //alert('다음페이지를 로딩중입니다.');  
                  }
              }
          });  
          
          
          //////*********내가 쓴 글만 보기*************

          function add_hideclass(){
              let articles=document.querySelectorAll(".article-listing");
              articles.forEach(article => {
                  let writer_id=article.querySelector('.description').getAttribute('value');
                  if(writer_id!="<%=user_id%>"){
                      article.classList.add('hide');
                  }
              });


          }
          function remove_hideclass(){
              let articles=document.querySelectorAll(".article-listing");
              articles.forEach(article => {
                  let writer_id=article.querySelector('.description').getAttribute('value');
                  if(writer_id!="<%=user_id%>"){
                      article.classList.remove('hide');
                  }
              });


          }
          
          function showOnlyMine(){
            
              check=checkBox.checked;
                  //show_mine=true;
                  //다른 id에 히든 클래스 넣기
              console.log(check);
              if(check){
                  
                  add_hideclass();
              }else{
                  remove_hideclass();
              }
              
          }



          checkBox.addEventListener('click',showOnlyMine)
          
        </script>

</body>
</html>