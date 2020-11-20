var roomId=localStorage.getItem("roomId");
var nickname=localStorage.getItem("nickname");
var roomName=localStorage.getItem("roomName");
const HIDE_CLASS="hide"
const user_name= document.querySelector("#user_name");
const grid=document.querySelector(".grid-container");
const logoutBtn=document.querySelector('#logout_btn');
const checkBox=document.querySelector("#showMine");
const roomname_text=document.querySelector("#roomName");

//room id가져오기
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
  };

const room_id=searchParam('roomId');

let check=false; //내가 작성한 글만 보기 체크=true

//id가 room_id인 diary data를 받았다고 가정하기

//최신일기가 앞으로 
const diarydata=[
    {
        
        "context": "오늘은 설렁탕을 먹었다. 산책도 했다. 즐거웠다ㅎㅎ",
        "date": "2020.11.16",
        "diary_id": 12,
        "feeling": "😄",
        "imgaddr": "https://imagescdn.gettyimagesbank.com/500/201709/a10980090.jpg",
        'nickname': "보라돌이",
        'title': "설렁탕 먹은날",
    },
    {
        
        "context": "보라돌이랑 뽀랑 안국역에서 만났다. 밥을 먹고 삼진그룹영화 토익반 이라는 영화를 봤다. 지갑을 잃어버렸다.",
        "date": "2020.11.15",
        "diary_id": 11,
        "feeling": "🤬",
        "imgaddr": "https://file2.nocutnews.co.kr/newsroom/image/2020/09/11/20200911112424825996_0_710_1018.jpg",
        'nickname': "나나",
        'title': "내 지갑 어디갔지",
    },
    {
       
        "context": "시험을 봤는데 망했다... 애들아 어떡해ㅠ",
        "date": "2020.11.14",
        "diary_id": 10,
        "feeling": "😭",
        "imgaddr": "https://i.pinimg.com/originals/81/bc/a0/81bca025d15df5d8f8b5c5c9e0f73ab4.png",
        'nickname': "뚜비",
        'title': "망했다..",
    },
    {
       
        "context": "코로나 때매 어디 가지도 못하고 무섭다ㅠㅠ보고싶다 텔레토비 친구들아...",
        "date": "2020.11.13",
        "diary_id": 9,
        "feeling": "😨",
        "imgaddr": "https://storage.googleapis.com/jjalbot-jjals/2018/12/UyHfRAZCB/zzal.jpg",
        'nickname': "뽀",
        'title': "보고싶어애두라",
    },
    {
        
        "context": "이유는 없어",
        "date": "2020.11.12",
        "diary_id": 8,
        "feeling": "🤬",
        "imgaddr": "https://www.womensrepublic.net/wp-content/uploads/2018/09/1103-Anger-Feeling-Angry.jpg",
        'nickname': "나나",
        'title': "화가난다",
    },
    {
        
        "context": "ktx타고 강릉에 갔다. 강릉 가서 물회를 먹었다. 맛있었다. 바다너무좋아",
        "date": "2020.11.11",
        "diary_id": 7,
        "feeling": "😄",
        "imgaddr": "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/5xq2/image/0lp8RLaJ2IgctTWVl2nEa-JRCSc.jpg",
        'nickname': "보라돌이",
        'title': "여행을 떠나자",
    },
    {
        
        "context": "오늘은 무엇을 쓰지 할 말이 없넹..",
        "date": "2020.11.10",
        "diary_id": 6,
        "feeling": "🤬",
        "imgaddr": "https://pbs.twimg.com/profile_images/763291339085578240/LNzfnK67.jpg",
        'nickname': "뚜비",
        'title': "음...",
    },
    {
       
        "context": "맛있는 거 많이 먹었다. 떡뽂이랑 치킨 최고",
        "date": "2020.11.09",
        "diary_id": 5,
        "feeling": "😄",
        "imgaddr": "https://economychosun.com/query/upload/303/20190608214338_gubchoja.jpg",
        'nickname': "뽀",
        'title': "배불러",
    },
    {
        
        "context": "배고프다. 배고파!! 밥먹으러 가자 고기고기",
        "date": "2020.11.08",
        "diary_id": 4,
        "feeling": "🤬",
        "imgaddr": "https://pbs.twimg.com/profile_images/781728004611330048/RS2435tq.jpg",
        'nickname': "보라돌이",
        'title': "배고파",
    },
    {
       
        "context": "푹 쉬었다..<br>집에서 티비보고 넷플릭스 보고 행복해",
        "date": "2020.11.07",
        "diary_id": 3,
        "feeling": "😄",
        "imgaddr": "https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png",
        'nickname': "나나",
        'title': "오늘은 집에서 쉰 날",
    },
    {
        
        "context": "할 말이 없다",
        "date": "2020.11.06",
        "diary_id": 2,
        "feeling": "🤬",
        "imgaddr": "https://newsimg.hankookilbo.com/cms/articlerelease/2019/04/29/201904291390027161_3.jpg",
        'nickname': "보라돌이",
        'title': "뭐라말하지",
    },
    {
       
        "context": "강아지 귀여워",
        "date": "2020.11.05",
        "diary_id": 1,
        "feeling": "😭",
        "imgaddr": "https://images.mypetlife.co.kr/content/uploads/2019/09/04222847/dog-panting-1024x683.jpg",
        'nickname': "뽀",
        'title': "강아지 키우고 싶다",
    },
    {
        
        "context": "고양이 귀여워",
        "date": "2020.11.04",
        "diary_id": 0,
        "feeling": "😭",
        "imgaddr": "https://image-notepet.akamaized.net/resize/620x-/seimage/20190816%2Ff07bd9f247293aa0317f2c8faba7e83b.png",
        'nickname': "뚜비",
        'title': "고양이 키우고 싶다.",
    },
]

if ("diaryList" in localStorage) {
    
}else{
    localStorage.setItem('diaryList',JSON.stringify(diarydata));
}

//유저이름 로컬스토리지에서 가져오기
user_name.innerHTML=nickname;
//방이름 로컬스토리지에서 가져오기
roomname_text.innerHTML=roomName;

//이미지 추가 함수
function add_diary(id,nickname,imgaddr,feeling,comment_count=0,date,title){
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

    titletag.innerHTML=title;
    description.innerHTML=date+" "+nickname
   
    description.setAttribute('value',nickname);
    article.setAttribute('id',id)
    image.setAttribute('src',imgaddr) 
    titletag.setAttribute('href',"diary_detail.html?id="+id) //제목 클릭시 이동 주소
    imagelink.setAttribute('href',"diary_detail.html?id="+id) ; //이미지 클릭시 이동 주소   
    
    div.appendChild(imagelink);
    div.appendChild(image);
    article.appendChild(titletag);
    article.appendChild(div);
    article.appendChild(description);

    grid.append(article);
}

//일단 6개만 출력
var count=0;
let diaryList=[]
if ("diaryList" in localStorage) {
    diaryList=JSON.parse(localStorage.getItem("diaryList"));
    //console.log(diaryList[0]["title"])
    diaryList.forEach(diary => {
        if(count>=6) return;
        add_diary(diary["diary_id"],diary["nickname"],diary["imgaddr"],diary["feeling"],0,diary["date"],diary["title"]);
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
                if(count>=diaryList.length) return;
                add_diary(diaryList[x]["diary_id"],diaryList[x]["nickname"],diaryList[x]["imgaddr"],diaryList[x]["feeling"],0,diaryList[x]["date"],diaryList[x]["title"]);
               
                              
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


    logoutBtn.addEventListener('click',function(){
        localStorage.removeItem('nickname');
        localStorage.removeItem('roomId');
        location.href="login.html";

    })



    function add_hideclass(){
        let articles=document.querySelectorAll(".article-listing");
        articles.forEach(article => {
            let name=article.querySelector('.description').getAttribute('value');
            if(name!=nickname){
                article.classList.add(HIDE_CLASS);
            }
        });


    }
    function remove_hideclass(){
        let articles=document.querySelectorAll(".article-listing");
        articles.forEach(article => {
            let name=article.querySelector('.description').getAttribute('value');
            if(name!=nickname){
                article.classList.remove(HIDE_CLASS);
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
    