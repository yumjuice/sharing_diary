<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<!--  로그인 가능한 userdata
//첫번째유저
id=1
pw=1

//두번째유저
id=2
pw=2

 -->
 <script>
 if("${alert}"!=""){

	 alert("${alert}");
 }
 </script>
 
 <form action="login.do" method="POST">
<div class="container">
            <img src="images/Logo.png">
         
            <div class="login_form">
               
                <div>
                   
                    <input style="margin-top : 25px;"class="input" id="roomId" name="id" type="text" placeholder="아이디">
                </div>
                <div >
                   
                    <input class="input" name="pw" id="roomPw" type="password" placeholder="비밀번호">
                    
                </div>
               
                <div>
                   
                    <input style="margin-top:15px"type="button" class="btn" onclick="location.href='join.jsp'" value="Sign up">
                </div>
                <div>
                    <input type="submit" class="btn" id="login_button" value="Login">
                    
                </div>
            </div>
         

        </div>
           </form>
      

</body>
</html>