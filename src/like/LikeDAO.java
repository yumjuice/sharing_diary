package like;

public class LikeDAO {
	//해당 user가 해당 diary를 좋아요 했는지 확인
	public boolean checkLike(String user_id,int diary_id) {
		if(user_id.equals("1") && diary_id==1) {
			return true;
		}
		return false;
	}
	//좋아요 추가
	public boolean addLike(String user_id, int diary_id) {
		///디비에 좋아요 추가
		System.out.println("좋아요 추가");
		return true;
	}
	
	public boolean removeLike(String user_id, int diary_id) {
		//디비에 좋아요 삭제
		System.out.println("좋아요 삭제");
		return true;
	}
}
