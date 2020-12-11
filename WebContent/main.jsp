<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="vo.RoomVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="vo.DiaryVO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous"> -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
</head>
<body>



<%
//main.do에서 유저, 다이어리 정보,  방정보 받기

	//무한스크롤시 스크롤할때마다 getPage.do 에서 json데이터 받기
	//////*****초기화
	MemberVO userVO = (MemberVO) request.getAttribute("user");

	//로그인 사용자 이름
	String user_name = userVO.getUser_name();

	//참여중인 Room객체 리스트 반환
	//ArrayList<RoomVO> room_list = (ArrayList<RoomVO>) request.getAttribute("roomList");

	//첫페이지다이어리리스트
	ArrayList<DiaryVO> diary_list = (ArrayList<DiaryVO>) request.getAttribute("diaryList");
//	ArrayList<DiaryVO> likeDiaryList=(ArrayList<DiaryVO>) request.getAttribute("likeDiaryList");
	//총 페이지 수
	int total_page = (int) request.getAttribute("pageNum");
	%>
<!-- 헤더 삽입 -->
<%@ include file="header.jsp"%>


<div class="body-container">
    <!--- 다이어리 리스트--->
    <div class="diary_list">
      <%
		if (total_page >= 1) {
			for (int i = 0; i < diary_list.size(); i++) {
				DiaryVO diary = diary_list.get(i);
				
				String feeling = diary.getFeeling();
				switch (feeling) {
					case "happy" :
						feeling = "😄";
						break;
					case "sad" :
						feeling = "😭";
						break;
					case "mad" :
						feeling = "🤬";
						break;
					case "surprise" :
						feeling = "😨";
						break;
				}%>

	       <div class="content_container">
	            <div class="title">
	                <p class="title_text">
	                    🏠<%=diary.getRoom_name()%>🏠 &nbsp;<%=diary.getWriter_name()%></p>
	            </div>
	
	            <div class="diary-container">
	                <div class="img">
	                    <img src="<%=diary.getImgaddr()%>">
	                </div>
	                <div class="diary" onclick="submitDiaryDetail('<%=diary.getDiary_id()%>')">
	                    <form action="diarydetail.do" method="POST" id="<%=diary.getDiary_id()%>">
	                        <input type="hidden" name="diary_id" value="<%=diary.getDiary_id()%>" />
	                        <input type="hidden" name="room_id" value="<%=diary.getRoom_id()%>" />
	                        <input type="hidden" name="page" value="main" />
	                    </form>
	                    <h3 class="title_text">
	                        <%=diary.getTitle()%></h3>
	                    <p class="sub_title_text"><%=diary.getDate()%>
	                        &nbsp;
	                        <%=feeling%></p>
	                    <p class="context"><%=diary.getContext()%></p>
	
	
	                </div>
	            </div>
	        </div>

        	<%}
		}%>




    </div>
    <!--- 방 리스트--->
    <div class="room-container">
        <div style="margin-top:10px;">
            <p id="room_name" style="display:inline;">
                ❤️<%=user_name%>님의 방❤️
            </p>

        </div>
        <hr style="margin-bottom: 10px;">

        <%
				for (int i = 0; i < room_list.size(); i++) {
			%>

        <div style="cursor: pointer;" onclick="location.href='diaryList.do?roomId=<%=room_list.get(i).getRoom_id()%>'">
            <%if (room_list.get(i).getMaster_id().equals(userVO.getUser_id())){ %>
            
            <%}%>
         
            
            
            <div class="roomImg">
            
            	<img src="<%=room_list.get(i).getRoom_img()%>">
            </div>
            
            <div class="roomTitle">
            <p><%=room_list.get(i).getRoom_name()%> 
            	<%if (room_list.get(i).getMaster_id().equals(userVO.getUser_id())){ %> 👑 <%}%>
            </p>
            </div>

        </div>

        <%}%>

		</div>
	</div>
<script>
    const diary_list_container = document.querySelector(".diary_list")
    
    
    //다이어리 추가
    function add_diary(room_id, roomname, diary_id, nickname, imgaddr, feeling, date, title1, context) {
        
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
        var container = document.createElement('div');

        var title = document.createElement('div');
        var p = document.createElement('p');

        var diary_container = document.createElement('div');

        var img_container = document.createElement('div');
        var img = document.createElement('img');

        var diary = document.createElement('div');
        var title_text = document.createElement('h3');
        var sub_title = document.createElement('p');
        var context_text = document.createElement('p');

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

        diary.setAttribute('onclick', 'location.href="diarydetail.do?room_id=' + room_id + '&diary_id=' + diary_id + '&page=main"');
        img.setAttribute('src', imgaddr);
        p.innerHTML = "🏠" + roomname + "🏠 " + nickname;
        title_text.innerHTML = title1;
        sub_title.innerHTML = date + " " + feeling;
        context_text.innerHTML = context;


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





    //*************무한스크롤*********
  
    var loading = false;

    var current_page = 2; //불러올 페이지

    /*스크롤 내릴때마다 다음 페이지 목록 호출*/
    function next_load() {
        //서버에 데이터 요청하기 
        //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목
        if (current_page > <%= total_page %> ) return;
        console.log(current_page + ' diary load');
        /* 이미지 동적 추가 */

        $.ajax({
            url: 'main.do?page=' + current_page,
            type: 'get',
            success: function(diary_list) {
                diary_list.forEach(diary => {
      	          add_diary(diary["room_id"], diary["room_name"], diary["diary_id"], diary["writer_name"], diary["imgaddr"], diary["feeling"], diary["date"], diary["title"], diary["context"]);
                })
            	loading = false; //실행 가능 상태로 변경
                current_page++;
            }
        })
    }

    window.addEventListener('scroll', function() {
        if (document.body.scrollTop + 100 >= document.body.scrollHeight - document.documentElement.clientHeight || window.scrollY + 100 >= document.body.scrollHeight - document.documentElement.clientHeight) {
            if (!loading) //실행 가능 상태라면?
            {
                loading = true; //실행 불가능 상태로 변경
                next_load();
            } else //실행 불가능 상태라면?
            {
                //alert('다음페이지를 로딩중입니다.');  
            }
        }
    });

    
    
    //***********************
  
   ///상세다이어리로 이동
    function submitDiaryDetail(id) {
        $("#" + id).submit();
    }
</script>


</body>
</html>