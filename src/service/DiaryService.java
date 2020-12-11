package service;

import java.io.PrintWriter;
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
	
	public DiaryVO getDiary(int diary_id) {
		DiaryVO diary=dao.getDiary(diary_id);
		return diary;
	}
	
	public ArrayList<DiaryVO> getDiaryPage(int page,String user_id){
		ArrayList<DiaryVO> list = dao.getDiaryPage(page,user_id);
		return list;
	}
	
	public List<Map<String,String>> getDiaryPageJson(int page,String user_id) {
		List<Map<String,String>> dic=new ArrayList();
		ArrayList<DiaryVO> list = dao.getDiaryPage(page,user_id);
    	  for(int i=0;i<list.size();i++){
    		
    		Map<String,String> map =new HashMap();
    		map.put("diary_id",Integer.toString(list.get(i).getDiary_id()));
    		map.put("writer_name",list.get(i).getWriter_name());
    		map.put("room_name",list.get(i).getRoom_name());
    		map.put("title",list.get(i).getTitle());
    		map.put("room_id", Integer.toString(list.get(i).getRoom_id()));
    		map.put("feeling",list.get(i).getFeeling());
    		map.put("context",list.get(i).getContext());
    		map.put("imgaddr",list.get(i).getImgaddr());
    		map.put("date",list.get(i).getDate());
    		
    		dic.add(map);
    		
    	}
    	  return dic;
	}
	
	public int getPageNum(String user_id) {
		return dao.getPageNum(user_id);
	}
	
	public ArrayList<DiaryVO> getDiaryList(int room_id){
		ArrayList<DiaryVO> list = dao.getDiaryList(room_id);
		return list;
	}
	public void addDiary(DiaryVO diary) {
		dao.addDiary(diary);
	}
	
	public void deleteDiary(DiaryVO diary,String user_id) {
		dao.deleteDiary(diary,user_id);
	}
	public void updateDiary(DiaryVO diary) {
		dao.updateDiary(diary);
	}
	public void deleteDiaryList(int room_id,String user_id) {
		dao.deleteDiaryList(room_id,user_id);
	}
	
	public ArrayList<DiaryVO> getLikeDiaryList(String user_id){
		ArrayList<DiaryVO> list = dao.getLikeDiaryList(user_id);
		return list;
	}
}
