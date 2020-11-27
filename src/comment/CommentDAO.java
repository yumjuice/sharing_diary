package comment;

import java.util.ArrayList;

public class CommentDAO {
	//댓글 디비에 추가
	public int putComment(String comment_writer_id,int diary_id,String comment_context) {
		
		///성공시
		return 1;
	
		//실패시
		//return 0;
	}
	
	
	//해당다이어리의 댓글 리스트 가져오기
	public ArrayList<Comment> getComment(int diary_id){
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		if(diary_id==1) {
			Comment comment1=new Comment();
			comment1.setComment_context("우와");
			comment1.setComment_id(1);
			comment1.setComment_writer_id("1");
			comment1.setDiary_id(1);
			
			Comment comment2=new Comment();
			comment2.setComment_context("짱이다");
			comment2.setComment_id(2);
			comment2.setComment_writer_id("2");
			comment2.setDiary_id(1);
			
			list.add(comment2);
			list.add(comment1);
		}
		
		
		return list;
		
	}
}
