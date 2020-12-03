package service;

import dao.LikeDAO;


public class LikeService {

	private static LikeService service = new LikeService();
	public LikeDAO dao = LikeDAO.getInstance();
	
	private LikeService(){}

	public static LikeService getInstance() {
		return service;
	}
	
	public void changeLike(String user_id,int diary_id) {
		int checkLike= dao.getLike(user_id, diary_id);
		//존재하지않음
		if(checkLike==0) {
			dao.addLike(user_id, diary_id);
		}else if(checkLike==-1) { //취소함
			dao.reLike(user_id, diary_id);
		}else {
			dao.cancelLike(user_id, diary_id);
		}
	}
	
	
	//좋아요 여부
	public boolean isLike(String user_id,int diary_id) {
		int checkLike= dao.getLike(user_id, diary_id);
		if(checkLike==0 || checkLike==-1) {
			return false;
		}
		return true;
	}
}
