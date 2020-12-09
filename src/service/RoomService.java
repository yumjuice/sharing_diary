package service;

import java.util.ArrayList;

import java.util.List;

import dao.RoomDAO;
import vo.RoomVO;
import vo.DiaryVO;
import vo.MemberVO;

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
			if(dao.checkUserExistsRoom(room.getRoom_id(), addFriendList.get(i))) {
				dao.reAddRoomUser(room, addFriendList.get(i),user_id);
			}else {
				newFriendList.add(addFriendList.get(i));
			}
		}
		if(!newFriendList.isEmpty()) {
		dao.addUser(room,newFriendList, user_id);
		System.out.println("newFriendList");
		System.out.println(newFriendList);}
		if(!removeFriendList.isEmpty()) {
			System.out.println("rmoveFriendlist"+removeFriendList);
			dao.removeRoomUser(room.getRoom_id(),removeFriendList,user_id);	}
	}
		
	//�濡 �ش� ������ �����ϴ��� Ȯ��
	public boolean checkUserInRoom(int room_id,String user_id) {
		
		return dao.checkUserInRoom(room_id, user_id);
	}
	
	//�����
	public void deleteRoom(String user_id,int room_id,ArrayList<MemberVO> memberList) {
		dao.deleteRoom(user_id,room_id);
		List<String> userList=new ArrayList<String>();
		for(int i=0;i<memberList.size();i++) {
			userList.add(memberList.get(i).getUser_id());
		}
		dao.removeRoomUser(room_id, userList, user_id);

	}
}
