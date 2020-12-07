package service;

import java.util.ArrayList;
import java.util.List;

import dao.RoomDAO;
import vo.RoomVO;

public class RoomService {
	private static RoomService service = new RoomService();
	public RoomDAO dao = RoomDAO.getInstance();
	
	private RoomService(){}

	public static RoomService getInstance() {
		return service;
	}
	//�ش� ������ �����ϴ� �� ����Ʈ
	public ArrayList<RoomVO> getRoomList(String user_id){
		ArrayList<RoomVO> list = dao.getRoomList(user_id);
		return list;
	}
	
	//������
	public RoomVO getRoom(int room_id) {
		RoomVO room=dao.getRoom(room_id);
		return room;
	}
	//���߰�
	public void addRoom(String user_id, RoomVO room,List<String> userList) {
		int room_id=dao.getLastId()+1;
		System.out.println("roomID"+room_id);
		room.setRoom_id(room_id);
		dao.addRoom(room, user_id);
		dao.addRoomUser(room, userList, user_id);

	}
	
	//�����
	public void updateRoom(String user_id, RoomVO room,List<String> addFriendList,List<String> removeFriendList) {
		dao.updateRoom(room, user_id);
		List<String> newFriendList=new ArrayList<String>();
		for (int i=0;i<addFriendList.size();i++) {
			//�̹� �����ߴ� ����̸�
			if(dao.checkUserInRoom(room.getRoom_id(), addFriendList.get(i))) {
				dao.reAddRoomUser(room, addFriendList.get(i),user_id);
			}else {
				newFriendList.add(addFriendList.get(i));
			}
		}
		if(!newFriendList.isEmpty()) {
		dao.addUser(room,newFriendList, user_id);
		System.out.println("newFriendlist");}
		if(!removeFriendList.isEmpty()) {
			System.out.println("rmoveFriendlist");
			dao.removeRoomUser(room,removeFriendList,user_id);	}
	}
		
	//�濡 �ش� ������ �����ϴ��� Ȯ��
	public boolean checkUserInRoom(int room_id,String user_id) {
		
		return dao.checkUserInRoom(room_id, user_id);
	}
}
