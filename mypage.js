const nickname_text=document.querySelector("#nickname")
const create_room_btn=document.querySelector('#create_room_btn');
const enter_room_btn=document.querySelector('#enter_room_btn')
const user_info_btn=document.querySelector('#user_info_btn');
const room_selectBox=document.querySelector('#room');
const logoutBtn=document.querySelector('#logout_btn');
let nickname=localStorage.getItem("nickname");

//**************내용 초기화 *********/
//닉네임입력
nickname_text.innerHTML=nickname+" 님 반갑습니다!! 😄";

//로컬스토리지에 room_users 데이터 저장
const room_users_data=[
    {
        "roomId":"1234",
        "roomName": "텔레토비 친구들",
        "userId" : "1",
        "nickname" : "보라돌이"
    },
    {
        "roomId":"12345",
        "roomName": "인턴 친구들",
        "userId" : "1",
        "nickname" : "보라돌이"
    }
]

localStorage.setItem("room_users",JSON.stringify(room_users_data));

//콤보박스 입력
//room_users[{ roomId,roomName, userId, nickname },,,]

function add_selectBox(roomname,roomId){
    var option=document.createElement('option');
    option.innerHTML=roomname;
    option.setAttribute('value',roomId);

    room_selectBox.append(option);
}

let room_users=JSON.parse(localStorage.getItem("room_users"));
let room_me=room_users.filter(room_user=>{
    return room_user["nickname"]==nickname;
})
room_me.forEach(element => {
    add_selectBox(element["roomName"],element["roomId"])
});


///************************** */

//방들어가기 -> 게시판 페이지로 이동
function enterRoom(){
    
    //콤보박스 선택한 방 게시판으로 이동
    let roomId= room_selectBox.options[room_selectBox.selectedIndex].value;
    localStorage.setItem("roomName",room_selectBox.options[room_selectBox.selectedIndex].innerHTML);
    localStorage.setItem("roomId",roomId);
    location.href="diaryList.html?roomId="+roomId;
}




create_room_btn.addEventListener('click',{
    //방생성 페이지???어떻게 할깧ㅎ
})

enter_room_btn.addEventListener('click',enterRoom);

user_info_btn.addEventListener('click',function(){
    //회원정보페이지로 이동
    location.href("#");

})

logoutBtn.addEventListener('click',function(){
    localStorage.removeItem('nickname');
    localStorage.removeItem('roomId');
    location.href="login.html";

})