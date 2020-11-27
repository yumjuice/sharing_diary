package room;

import java.util.ArrayList;


public class RoomDAO {
	
	//roomId를 넣으면 방 이름 출력
	public String getRoomName(int room_id) {
		if(room_id==1) {
			return "텔레토비";
		}else if(room_id==2) {
			return "뽀로로 친구들";
		}
		return "";
	}
	
	
	//아이디 넣으면 참여중인 방 리스트 출력
	public ArrayList<Room> getRoomList(String user_id){
		ArrayList<Room> roomlist=new ArrayList<Room>();
		
		if(user_id.equals("1")) {
			Room room1=new Room();
			room1.setRoom_id(1);
			room1.setRoom_name("텔레토비");
		
			Room room2=new Room();
			room2.setRoom_id(2);
			room2.setRoom_name("뽀로로 친구들");
			
			roomlist.add(room1);
			roomlist.add(room2);
			
		}else if(user_id.equals("2")) {
			Room room1=new Room();
			room1.setRoom_id(1);
			room1.setRoom_name("텔레토비");
			
			roomlist.add(room1);
		}
		
		return roomlist;
	}
}
