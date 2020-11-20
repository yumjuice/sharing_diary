const loginButton=document.querySelector("#login_button");

let user_names=["보라돌이","뚜비","나나","뽀",'햇님'];



function login(){
    let id=document.getElementById("roomId").value;
    let pw=document.getElementById("roomPw").value;
    let nickname=document.getElementById("nickname").value;
    if(id!="1234"){
        console.log(id);
        alert("아이디가 틀렸습니다.");
        return;
    }
    else if(pw!="1234"){
       alert("비밀번호가 틀렸습니다.");
       return;
    }
    else if(!user_names.includes(nickname)){
        alert("존재하지 않는 닉네임 입니다!")
        return;
    }
    else{
     //  localStorage.setItem("roomId", id);
        localStorage.setItem("nickname", nickname);
        location.href="mainpage.html";
    }

}

loginButton.addEventListener('click',login,false);