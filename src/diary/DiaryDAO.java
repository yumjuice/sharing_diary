package diary;

import java.util.ArrayList;

public class DiaryDAO {

	public Diary getDiary(int diary_id) {
		Diary diary=new Diary();
		
		///디비에 연결해서 diary_id에 맞는 diary가져오기
		
		if(diary_id==1) {
			 
			 diary.setContext("할 말이 없다aaaaa");
			 diary.setDate("2020.11.04");
			 diary.setDiary_id(1);
			 diary.setFeeling("🤬");
			 diary.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary.setRoom_id(1);
			 diary.setTitle("뭐라ddddd말하지");
			 diary.setWriter_id("2");
		}
		else if(diary_id==2) {
			 diary.setContext("alfdjkfv");
			 diary.setDate("2020.11.05");
			 diary.setDiary_id(2);
			 diary.setFeeling("🤬");
			 diary.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary.setRoom_id(1);
			 diary.setTitle("뭐라말하지");
			 diary.setWriter_id("2");
		}else if(diary_id==3) {
			
			 diary.setContext("할 말이 없다aaaaa");
			 diary.setDate("2020.11.09");
			 diary.setDiary_id(3);
			 diary.setFeeling("🤬");
			 diary.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary.setRoom_id(1);
			 diary.setTitle("뭐라ddddd말하지");
			 diary.setWriter_id("1");
		}else if(diary_id==4) {
			 diary.setContext("할 말이 없다");
			 diary.setDate("2020.11.20");
			 diary.setDiary_id(4);
			 diary.setFeeling("🤬");
			 diary.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary.setRoom_id(1);
			 diary.setTitle("뭐라말하지");
			 diary.setWriter_id("1");
		}
		
		return diary;
	}
	
	///참여하고 있는 모든 방의 글 리스트 페이지별로 가져오기
	public ArrayList<Diary> getAllList(int page){
		ArrayList<Diary> list=new ArrayList<Diary>();
		if(page==1) {
			 Diary diary1=new Diary();
			 diary1.setContext("할 말이 없다");
			 diary1.setDate("2020.11.20");
			 diary1.setDiary_id(4);
			 diary1.setFeeling("🤬");
			 diary1.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary1.setRoom_id(1);
			 diary1.setTitle("뭐라말하지");
			 diary1.setWriter_id("1");
		
			 
			 Diary diary2=new Diary();
			 diary2.setContext("할 말이 없다aaaaa");
			 diary2.setDate("2020.11.09");
			 diary2.setDiary_id(3);
			 diary2.setFeeling("🤬");
			 diary2.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary2.setRoom_id(1);
			 diary2.setTitle("뭐라ddddd말하지");
			 diary2.setWriter_id("1");
		
			 
			 list.add(diary1);
			 list.add(diary2);
		}else if(page==2) {
			 Diary diary1=new Diary();
			 diary1.setContext("alfdjkfv");
			 diary1.setDate("2020.11.05");
			 diary1.setDiary_id(2);
			 diary1.setFeeling("🤬");
			 diary1.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary1.setRoom_id(1);
			 diary1.setTitle("뭐라말하지");
			 diary1.setWriter_id("2");
	
			 
			 Diary diary2=new Diary();
			 diary2.setContext("할 말이 없다aaaaa");
			 diary2.setDate("2020.11.04");
			 diary2.setDiary_id(1);
			 diary2.setFeeling("🤬");
			 diary2.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
			 diary2.setRoom_id(1);
			 diary2.setTitle("뭐라ddddd말하지");
			 diary2.setWriter_id("2");
		
			 
			 list.add(diary1);
			 list.add(diary2);
		}
		return list;
	}
	//참여하고 있는 모든 방의 글의 페이지 수
	public int getPageNum() {
		return 2;
	}
	
	
	//방 id별로 글 리스트 가져오기
	public ArrayList<Diary> getList(int room_id){
		ArrayList<Diary> list=new ArrayList<Diary>();
		if(room_id==1) {
		
		Diary diary1=new Diary();
		 diary1.setContext("할 말이 없다");
		 diary1.setDate("2020.11.20");
		 diary1.setDiary_id(4);
		 diary1.setFeeling("🤬");
		 diary1.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
		 diary1.setRoom_id(1);
		 diary1.setTitle("뭐라말하지");
		 diary1.setWriter_id("1");
	
		 
		 Diary diary2=new Diary();
		 diary2.setContext("할 말이 없다aaaaa");
		 diary2.setDate("2020.11.09");
		 diary2.setDiary_id(3);
		 diary2.setFeeling("🤬");
		 diary2.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
		 diary2.setRoom_id(1);
		 diary2.setTitle("뭐라ddddd말하지");
		 diary2.setWriter_id("1");
		 
		 Diary diary3=new Diary();
		 diary3.setContext("alfdjkfv");
		 diary3.setDate("2020.11.05");
		 diary3.setDiary_id(2);
		 diary3.setFeeling("🤬");
		 diary3.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
		 diary3.setRoom_id(1);
		 diary3.setTitle("뭐라말하지");
		 diary3.setWriter_id("2");

		 
		 Diary diary4=new Diary();
		 diary4.setContext("할 말이 없다aaaaa");
		 diary4.setDate("2020.11.04");
		 diary4.setDiary_id(1);
		 diary4.setFeeling("🤬");
		 diary4.setImgaddr("https://tistory2.daumcdn.net/tistory/1727115/skin/images/ryon.png");
		 diary4.setRoom_id(1);
		 diary4.setTitle("뭐라ddddd말하지");
		 diary4.setWriter_id("2");
	
		 list.add(diary1);
		 list.add(diary2);
		 list.add(diary3);
		 list.add(diary4);
		}
		return list;
	}
	
	///다이어리 작성 기능
	public int putDiary(int room_id,String user_id,String title,String feeling,String context,String imgaddr) {
		////디비에 다이이리정보 집어 넣기
		//SQL에서 날짜 삽입하기 
		
		///성공시 
		return 1;
		
		//실패시 
		//return 0;
	}
	
}