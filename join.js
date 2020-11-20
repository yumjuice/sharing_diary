const join_btn=document.querySelector("#join_button")


function value_check() {
    var check_count = document.getElementsByName("gender").length;

    for (var i=0; i<check_count; i++) {
        if (document.getElementsByName("gender")[i].checked == true) {
            //alert(document.getElementsByName("(NAME명)")[i].value);
            return document.getElementsByName("gender")[i].value;

        }
    }
}

function join(){
    //user생성
    //로컬스토리지에 user추가
    let id=document.querySelector('#id').value;
    let pw=document.querySelector('#pw').value;
    let nickname=document.querySelector("#nickname").value;
    let gender=value_check();
    let birth=document.querySelector('#birth').value;
    
    if(id==null || pw==null || nickname==null ||gender==null ||birth==null){
        alert("회원정보를 모두 입력해주세요!");
        return;
    }
    
    let user={
        "user_id" : id,
        "user_pw" : pw,
        "nickname" : nickname,
        "gender" : gender,
        "birth"  : birth,

    }

    localStorage.setItem("user",JSON.stringify(user));



    alert(nickname+"님, 회원가입을 축하드립니다!!😍")
    location.href="login.html";
}

join_btn.addEventListener('click',join);