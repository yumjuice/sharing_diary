const diary_list_container=document.querySelector(".diary_list")
const logoutBtn=document.querySelector("#logout_btn");
const diary_element=document.querySelector('.diary');
const room_container=document.querySelector('.room-container')


const allDiarydata=[
    {
        "roomId":2,
        "roomName":"뽀로로 친구들",
        "context": "노는게 제일 좋앟ㅎㅎㅎㅎ",
        "date": "2020.11.18",
        "diary_id": 17,
        "feeling": "😄",
        "imgaddr": "https://pbs.twimg.com/profile_images/931571066534690816/YjOsFwcJ_400x400.jpg",
        'nickname': "뽀로로",
        'title': "노는 날",
    },
    {
        "roomId":2,
        "roomName":"뽀로로 친구들",
        "context": "낚시터에 가서 물고기를 잡았다.대왕 잉어",
        "date": "2020.11.18",
        "diary_id": 16,
        "feeling": "😄",
        "imgaddr": "https://i.pinimg.com/originals/5f/f1/86/5ff1862e1361bc910184200ff01bff6d.jpg",
        'nickname': "루피",
        'title': "낚시장 간 날",
    },
    {
        "roomId":2,
        "roomName":"뽀로로 친구들",
        "context": "크로로롤ㅇ클ㄹ롤오롤올오로롱ㄹㅇ",
        "date": "2020.11.18",
        "diary_id": 15,
        "feeling": "🤬",
        "imgaddr": "https://imagescdn.gettyimagesbank.com/500/201709/a10980090.jpg",
        'nickname': "크롱",
        'title': "크롱크롱",
    },
    {
        "roomId":2,
        "roomName":"뽀로로 친구들",
        "context": "귀신 무서워서 잠을 잘 못 잤다.",
        "date": "2020.11.17",
        "diary_id": 14,
        "feeling": "😨",
        "imgaddr": "https://imagescdn.gettyimagesbank.com/500/201709/a10980090.jpg",
        'nickname': "에디",
        'title': "귀신무서워",
    },
    {
        "roomId":2,
        "roomName":"뽀로로 친구들",
        "context": "그냥 슬프다..",
        "date": "2020.11.17",
        "diary_id": 13,
        "feeling": "😭",
        "imgaddr": "https://imagescdn.gettyimagesbank.com/500/201709/a10980090.jpg",
        'nickname': "에디",
        'title': "슬프다아",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "오늘은 설렁탕을 먹었다. 산책도 했다. 즐거웠다ㅎㅎ",
        "date": "2020.11.16",
        "diary_id": 12,
        "feeling": "😄",
        "imgaddr": "https://imagescdn.gettyimagesbank.com/500/201709/a10980090.jpg",
        'nickname': "보라돌이",
        'title': "설렁탕 먹은날",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "보라돌이랑 뽀랑 안국역에서 만났다. 밥을 먹고 삼진그룹영화 토익반 이라는 영화를 봤다. 지갑을 잃어버렸다.",
        "date": "2020.11.15",
        "diary_id": 11,
        "feeling": "🤬",
        "imgaddr": "https://file2.nocutnews.co.kr/newsroom/image/2020/09/11/20200911112424825996_0_710_1018.jpg",
        'nickname': "나나",
        'title': "내 지갑 어디갔지",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "시험을 봤는데 망했다... 애들아 어떡해ㅠ",
        "date": "2020.11.14",
        "diary_id": 10,
        "feeling": "😭",
        "imgaddr": "https://i.pinimg.com/originals/81/bc/a0/81bca025d15df5d8f8b5c5c9e0f73ab4.png",
        'nickname': "뚜비",
        'title': "망했다..",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "코로나 때매 어디 가지도 못하고 무섭다ㅠㅠ보고싶다 텔레토비 친구들아...",
        "date": "2020.11.13",
        "diary_id": 9,
        "feeling": "😨",
        "imgaddr": "https://storage.googleapis.com/jjalbot-jjals/2018/12/UyHfRAZCB/zzal.jpg",
        'nickname': "뽀",
        'title': "보고싶어애두라",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "이유는 없어",
        "date": "2020.11.12",
        "diary_id": 8,
        "feeling": "🤬",
        "imgaddr": "https://www.womensrepublic.net/wp-content/uploads/2018/09/1103-Anger-Feeling-Angry.jpg",
        'nickname': "나나",
        'title': "화가난다",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "ktx타고 강릉에 갔다. 강릉 가서 물회를 먹었다. 맛있었다. 바다너무좋아",
        "date": "2020.11.11",
        "diary_id": 7,
        "feeling": "😄",
        "imgaddr": "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/5xq2/image/0lp8RLaJ2IgctTWVl2nEa-JRCSc.jpg",
        'nickname': "보라돌이",
        'title': "여행을 떠나자",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "오늘은 무엇을 쓰지 할 말이 없넹..",
        "date": "2020.11.10",
        "diary_id": 6,
        "feeling": "🤬",
        "imgaddr": "https://pbs.twimg.com/profile_images/763291339085578240/LNzfnK67.jpg",
        'nickname': "뚜비",
        'title': "음...",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "맛있는 거 많이 먹었다. 떡뽂이랑 치킨 최고",
        "date": "2020.11.09",
        "diary_id": 5,
        "feeling": "😄",
        "imgaddr": "https://economychosun.com/query/upload/303/20190608214338_gubchoja.jpg",
        'nickname': "뽀",
        'title': "배불러",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "배고프다. 배고파!! 밥먹으러 가자 고기고기",
        "date": "2020.11.08",
        "diary_id": 4,
        "feeling": "🤬",
        "imgaddr": "https://pbs.twimg.com/profile_images/781728004611330048/RS2435tq.jpg",
        'nickname': "보라돌이",
        'title': "배고파",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "푹 쉬었다..<br>집에서 티비보고 넷플릭스 보고 행복해",
        "date": "2020.11.07",
        "diary_id": 3,
        "feeling": "😄",
        "imgaddr": "https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png",
        'nickname': "나나",
        'title': "오늘은 집에서 쉰 날",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "할 말이 없다",
        "date": "2020.11.06",
        "diary_id": 2,
        "feeling": "🤬",
        "imgaddr": "https://newsimg.hankookilbo.com/cms/articlerelease/2019/04/29/201904291390027161_3.jpg",
        'nickname': "보라돌이",
        'title': "뭐라말하지",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "강아지 귀여워",
        "date": "2020.11.05",
        "diary_id": 1,
        "feeling": "😭",
        "imgaddr": "https://images.mypetlife.co.kr/content/uploads/2019/09/04222847/dog-panting-1024x683.jpg",
        'nickname': "뽀",
        'title': "강아지 키우고 싶다",
    },
    {
        "roomId":1,
        "roomName":"텔레토비 친구들",
        "context": "고양이 귀여워",
        "date": "2020.11.04",
        "diary_id": 0,
        "feeling": "😭",
        "imgaddr": "https://image-notepet.akamaized.net/resize/620x-/seimage/20190816%2Ff07bd9f247293aa0317f2c8faba7e83b.png",
        'nickname': "뚜비",
        'title': "고양이 키우고 싶다.",
    },
]


if ("allDiaryList" in localStorage) {
    
}else{
    localStorage.setItem('allDiaryList',JSON.stringify(allDiarydata));
}


//
//로컬스토리지에 room_users 데이터 저장
const room_users_data=[
    {
        "roomId":1,
        "roomName": "텔레토비 친구들",
        "userId" : "1",
        "nickname" : "보라돌이"
    },
    {
        "roomId":2,
        "roomName": "뽀로로 친구들",
        "userId" : "1",
        "nickname" : "보라돌이"
    },
    {
        
        "roomId":1,
        "roomName": "텔레토비 친구들",
        "userId" : "2",
        "nickname" : "뽀"
    },
    {
        
        "roomId":1,
        "roomName": "텔레토비 친구들",
        "userId" : "3",
        "nickname" : "나나"
    },
    {
        
        "roomId":1,
        "roomName": "텔레토비 친구들",
        "userId" : "4",
        "nickname" : "뚜비"
    }
]

localStorage.setItem("room_users",JSON.stringify(room_users_data));

///**********데이터 가져오기 */

//유저이름 로컬스토리지에서 가져오기
let user_name=localStorage.getItem('nickname');
room_name.innerHTML="❤️"+user_name+"❤️님의 방"

//방 리스트 가져오기
function add_room(roomname,roomId){
    var div=document.createElement('div');
    div.setAttribute('onclick','location.href="diaryList.html?roomId='+roomId+'";localStorage.setItem("roomName","'+roomname+'");')
    div.setAttribute('style','cursor:pointer;')
    var img=document.createElement('img');
    var span=document.createElement('span')

    img.classList.add('room_img');
    img.setAttribute('src','images/group.png');
    span.innerHTML=roomname
    
    div.appendChild(img);
    div.appendChild(span)



    room_container.append(div);
}

let room_users=JSON.parse(localStorage.getItem("room_users"));
let room_me=room_users.filter(room_user=>{
    return room_user["nickname"]==user_name;
})
room_me.forEach(element => {
    add_room(element["roomName"],element["roomId"])
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

//*************************************** */

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
    title.classList.add('title')
    p.classList.add('title_text')
    diary_container.classList.add('diary-container')
    img_container.classList.add('img');
    diary.classList.add('diary');
    title_text.classList.add('title_text');
    sub_title.classList.add('sub_title_text')
    context_text.classList.add('context');

    diary.setAttribute('onclick','location.href="diary_detail.html?id='+diary_id+'"');
    img.setAttribute('src',imgaddr);
    p.innerHTML="🏠"+roomname+"🏠 "+nickname;
    title_text.innerHTML=title1+feeling;
    sub_title.innerHTML=date+" "+nickname;
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


///(roomname, diary_id,nickname,imgaddr,feeling,date,title)



//일단 6개만 출력
var count=0;
let allDiaryList=[]
if ("allDiaryList" in localStorage) {
    allDiaryList=JSON.parse(localStorage.getItem("allDiaryList"));
    //console.log(diaryList[0]["title"])
    allDiaryList.forEach(diary => {
        if(count>=6) return;
        add_diary(diary["roomName"],diary["diary_id"],diary["nickname"],diary["imgaddr"],diary["feeling"],diary["date"],diary["title"],diary["context"]);
        count++;
    });
       
    
}

//스크롤 내릴때마다 6개 추가 출력
//***무한스크롤링 */
var loading = false;    //중복실행여부 확인 변수
///var page = 1;   //불러올 페이지

    /*nextpageload function*/
    function next_load()
    {
          //서버에 데이터 요청하기 
          //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목
                
        console.log(count+' diary load');
        /* 이미지 동적 추가 */
        for (var x=count;x<count+6;x++)   
            {
                if(count>=allDiaryList.length) return;
                add_diary(allDiaryList[x]["roomName"],allDiaryList[x]["diary_id"],allDiaryList[x]["nickname"],allDiaryList[x]["imgaddr"],allDiaryList[x]["feeling"],allDiaryList[x]["date"],allDiaryList[x]["title"],allDiaryList[x]["context"]);
               
                              
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
                
            }
            else            //실행 불가능 상태라면?
            {
                //alert('다음페이지를 로딩중입니다.');  
            }
        }
    });  

    logoutBtn.addEventListener('click',function(){
        localStorage.removeItem('nickname');
        localStorage.removeItem('roomId');
        localStorage.removeItem('roomName');
        location.href="login.html";

    })


