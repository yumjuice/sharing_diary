package service;

import java.util.ArrayList;

import dao.RoomDAO;
import vo.RoomVO;

public class RoomService {
	private static RoomService service = new RoomService();
	public RoomDAO dao = RoomDAO.getInstance();
	
	private RoomService(){}

	public static RoomService getInstance() {
		return service;
	}
	
	public ArrayList<RoomVO> getRoomList(String user_id){
		ArrayList<RoomVO> list = dao.getRoomList(user_id);
		return list;
	}
}
