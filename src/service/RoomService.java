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
	//해당 유저가 참여하는 방 리스트
	public ArrayList<RoomVO> getRoomList(String user_id){
		ArrayList<RoomVO> list = dao.getRoomList(user_id);
		return list;
	}
	
	//방정보
	public RoomVO getRoom(int room_id) {
		RoomVO room=dao.getRoom(room_id);
		return room;
	}
	//방추가
	public void addRoom(String user_id, RoomVO room,List<String> userList) {
		int room_id=dao.getLastId()+1;
		System.out.println("roomID"+room_id);
		room.setRoom_id(room_id);
		dao.addRoom(room, user_id);
		dao.addRoomUser(room, userList, user_id);

	}
	
	//방수정
	public void updateRoom(String user_id, RoomVO room,List<String> addFriendList,List<String> removeFriendList) {
		dao.updateRoom(room, user_id);
		List<String> newFriendList=new ArrayList<String>();
		for (int i=0;i<addFriendList.size();i++) {
			//이미 존재했던 사람이면
			if(dao.checkUserExistsRoom(room.getRoom_id(), addFriendList.get(i))) {
				dao.reAddRoomUser(room, addFriendList.get(i),user_id);
			}else {
				newFriendList.add(addFriendList.get(i));
			}
		}
		if(newFriendList.size()>=1) {
			System.out.println("size"+newFriendList.size());
			System.out.println("newFriendList");
			System.out.println(newFriendList);
			dao.addUser(room,newFriendList, user_id);
			
		}
		if(removeFriendList.size()>=1) {
			System.out.println("rmoveFriendlist"+removeFriendList);
			dao.removeRoomUser(room.getRoom_id(),removeFriendList,user_id);	
		}
	}
		
	//방에 해당 유저가 참여하는지 확인
	public boolean checkUserInRoom(int room_id,String user_id) {
		
		return dao.checkUserInRoom(room_id, user_id);
	}
	
	//방삭제
	public void deleteRoom(String user_id,int room_id,ArrayList<MemberVO> memberList) {
		dao.deleteRoom(user_id,room_id);
		List<String> userList=new ArrayList<String>();
		for(int i=0;i<memberList.size();i++) {
			userList.add(memberList.get(i).getUser_id());
		}
		dao.removeRoomUser(room_id, userList, user_id);

	}
	
	//방 탈퇴
	public void deleteRoomUser(String user_id,int room_id) {
		String master_id=dao.getRoom(room_id).getMaster_id();
		//방에 참여자가 본인 한명이라면
		int count=dao.getCountOfParti(room_id);
		if(count==1) {
			dao.deleteRoomUser(user_id,room_id);
		}
		//탈퇴한 사람이 방장이라면 가장 먼저 추가된 사람에게 방장 이관
		else if(master_id.equals(user_id)) {
			
			dao.updateMaster(room_id, user_id);
			dao.deleteRoomUser(user_id,room_id);
		}
		else dao.deleteRoomUser(user_id,room_id);
		
	}
}
