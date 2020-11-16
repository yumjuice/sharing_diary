const loginButton=document.querySelector("#login_button");
const id=document.getElementById("roomId").getAttribute( 'value' );
const pw=document.getElementById("roomPw").value;
const nickname=document.getElementById("nickname").value;



function login(){
    //console.log(document.login_form.querySelector("div").roomId.value);
    if(id!="1234"){
        console.log(id);
        alert("아이디가 틀렸습니다.");
        return;
    }
    else if(pw!="1234"){
       alert("비밀번호가 틀렸습니다.");
       return;
    }
    else if(nickname!="연주"){
        alert("존재하지 않는 닉네임 입니다!")
        return;
    }
    else{
        localStorage.setItem("roomId", id);
        localStorage.setItem("nickname", nickname);
        location.href="diaryList.html";
    }

}

loginButton.addEventListener('click',login,false);