var roomId=localStorage.getItem("roomId");
var nickname=localStorage.getItem("nickname");

const user_name= document.querySelector("#user_name");
const grid=document.querySelector(".grid-container");


//유저이름 로컬스토리지에서 가져오기
user_name.innerHTML=nickname;

//이미지 추가 함수
function add_diary(id,nickname,imgaddr,feeling,comment_count=0,date,title){
    var article = document.createElement( 'article' );
    var titletag=document.createElement('a');
    
    var div=document.createElement('div');
    var imagelink=document.createElement('a');
    var image=document.createElement('img');

    var description=document.createElement('p');

    article.classList.add('location-listing');
    article.classList.add('fadein');
    titletag.classList.add('location-title');
    div.classList.add('location-image');

    titletag.innerHTML=title;
    description.innerHTML=date+" "+nickname
    article.setAttribute('id',id)
    image.setAttribute('src',imgaddr) 
    titletag.setAttribute('href',"diary_detail.html?id="+id) //제목 클릭시 이동 주소
    imagelink.setAttribute('href',"diary_detail.html?id="+id) ; //이미지 클릭시 이동 주소   
    
    div.appendChild(imagelink);
    div.appendChild(image);
    article.appendChild(titletag);
    article.appendChild(div);
    article.appendChild(description);

    grid.prepend(article);
}


if ("diaryList" in localStorage) {
    let diaryList=JSON.parse(localStorage.getItem("diaryList"));
    console.log(diaryList[0]["title"])
    diaryList.forEach(diary => {
        add_diary(diary["diary_id"],diary["nickname"],diary["imgaddr"],diary["feeling"],0,diary["date"],diary["title"]);
    });
       
    
}


//***무한스크롤링 */
var loading = false;    //중복실행여부 확인 변수
var page = 1;   //불러올 페이지

    /*nextpageload function*/
    function next_load()
    {
          //서버에 데이터 요청하기 
          //필요한 데이터 : 닉네임, 이미지 주소, 일기id, 감정, 댓글 수, 작성날짜,제목
                
        console.log(page+' page load');
        /* 이미지 동적 추가 */
        for (var x=1;x<6;x++)   
            {
                var article = document.createElement( 'article' );
                var title=document.createElement('a');
                
                var div=document.createElement('div');
                var imagelink=document.createElement('a');
                var image=document.createElement('img');

                var description=document.createElement('p');

                article.classList.add('location-listing');
                article.classList.add('fadein');
                title.classList.add('location-title');
                div.classList.add('location-image');

                title.innerHTML="title";
                description.innerHTML="date username"
                article.setAttribute('id',page*5+x)
                image.setAttribute('src','images/background.jpg') 
                title.setAttribute('href',"#"); //제목 클릭시 이동 주소
                imagelink.setAttribute('href',"#") ; //이미지 클릭시 이동 주소   
                
                div.appendChild(imagelink);
                div.appendChild(image);
                article.appendChild(title);
                article.appendChild(div);
                article.appendChild(description);

                grid.appendChild(article);
                               
            }
            page++; //페이지 증가
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
                alert('다음페이지를 로딩중입니다.');  
            }
        }
    });  