package room;

import java.util.ArrayList;


public class RoomDAO {
	
	//roomId�� ������ �� �̸� ���
	public String getRoomName(int room_id) {
		if(room_id==1) {
			return "�ڷ����";
		}else if(room_id==2) {
			return "�Ƿη� ģ����";
		}
		return "";
	}
	
	
	//���̵� ������ �������� �� ����Ʈ ���
	public ArrayList<Room> getRoomList(String user_id){
		ArrayList<Room> roomlist=new ArrayList<Room>();
		
		if(user_id.equals("1")) {
			Room room1=new Room();
			room1.setRoom_id(1);
			room1.setRoom_name("�ڷ����");
		
			Room room2=new Room();
			room2.setRoom_id(2);
			room2.setRoom_name("�Ƿη� ģ����");
			
			roomlist.add(room1);
			roomlist.add(room2);
			
		}else if(user_id.equals("2")) {
			Room room1=new Room();
			room1.setRoom_id(1);
			room1.setRoom_name("�ڷ����");
			
			roomlist.add(room1);
		}
		
		return roomlist;
	}
}
