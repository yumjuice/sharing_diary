package service;

import java.util.*;
import dao.DiaryDAO;
import vo.DiaryVO;

public class DiaryService {

	private static DiaryService service = new DiaryService();
	public DiaryDAO dao = DiaryDAO.getInstance();
	
	private DiaryService(){}

	public static DiaryService getInstance() {
		return service;
	}
	
	public ArrayList<DiaryVO> getDiaryPage(int page,String user_id){
		ArrayList<DiaryVO> list = dao.getDiaryPage(page,user_id);
		return list;
	}
	
	public int getPageNum(String user_id) {
		return dao.getPageNum(user_id);
	}
	
	public ArrayList<DiaryVO> getDiaryList(int room_id){
		ArrayList<DiaryVO> list = dao.getDiaryList(room_id);
		return list;
	}
	
}
