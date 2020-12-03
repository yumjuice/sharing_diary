<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/join.css">
        
</head>
<body>
<form action="user.do" method="POST" onsubmit="return check()">
 <div class="container">
            <h1>교환 일기</h1>
          
            <div class="join_form">
                <p style="margin-top:10px ;text-align: center;">✨회원가입✨</p>
                <div>
                    <span>ID</span>
                    <input id="id" type="text" name="user_id" placeholder="영어 소문자,숫자 4-12자리" >
                </div>
                <div >
                    <span>PW</span>
                    <input id="pw" type="password" name="user_pw" placeholder="영어 소문자,숫자 4-11자리">
                    
                </div>
                <div >
                    <span>PW</span>
                    <input id="pw2" type="password"placeholder="비밀번호확인">
                    
                </div>
                <div>
                    <span>이름</span>
                    <input id="nickname" type="text" name="user_name" placeholder="닉네임">
                </div>
                <div>
                    <span style="margin-right:30px">성별</span>
                    <label><input type="radio" name="user_gender" value="male">🧑</label>
                    <label><input type="radio" name="user_gender" value="female">👩</label>
                   
                </div>
                <div>
                    <span>생년월일</span>
                    <input id="birth" type="text" name="user_birth" placeholder="YYYYMMDD">
                </div>
                 <div>
                    <span>이메일</span>
                    <input id="email" type="text" name="user_email" placeholder="example@example.com">
                </div>
                <div>
                    <input type="submit" id="join_button" value="Join!">
                </div>
            </div>
        </div>
</form>
<script>
function value_check() {
    var check_count = document.getElementsByName("gender").length;

    for (var i=0; i<check_count; i++) {
        if (document.getElementsByName("gender")[i].checked == true) {
            //alert(document.getElementsByName("(NAME명)")[i].value);
            return document.getElementsByName("gender")[i].value;

        }
    }
}


function check() {
	
	let id=document.querySelector('#id').value;
	let pw=document.querySelector('#pw').value;
	let pw2=document.querySelector('#pw2').value;
	let nickname=document.querySelector("#nickname").value;
	let email=document.querySelector("#email").value;
	let gender=value_check();
	let birth=document.querySelector('#birth').value;
	
	
	 var idregex = /^[a-z\d]{3,11}$/;//"영어 소문자,숫자 4-12자리
	 var pwregex = /^[a-z\d]{4,12}$/;//영어대소문자,숫자 4-11자리")
	 var emailregex = /.+@[a-z]+(\.[a-z]+){1,2}$/;
	 var birthregex= /^[0-9]{8}$/;
	  
	 
	   if(idregex.exec(id) == null){
		   alert("아이디양식을 다시 확인해주세요");
		   return false;
	   }
	   if(pwregex.exec(pw) == null){
		   alert("비밀번호 양식을 다시 확인해주세요");
		   return false;
	   }
	   if(pw!=pw2){
		   alert("비밀번호가 다릅니다.");
		   return false;
	   }
	   if(birthregex.exec(birth) == null){
		   alert("생년월일 양식을 다시 확인해주세요");
		   return false;
	   }
	   if(emailregex.exec(email) == null){
		   alert("이메일 양식을 다시 확인해주세요");
		   return false;
	   }
	   
	   return true;
}
</script>
</body>
</html>