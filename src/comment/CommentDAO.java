package comment;

import java.util.ArrayList;

public class CommentDAO {
	//��� ��� �߰�
	public int putComment(String comment_writer_id,int diary_id,String comment_context) {
		
		///������
		return 1;
	
		//���н�
		//return 0;
	}
	
	
	//�ش���̾�� ��� ����Ʈ ��������
	public ArrayList<Comment> getComment(int diary_id){
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		if(diary_id==1) {
			Comment comment1=new Comment();
			comment1.setComment_context("���");
			comment1.setComment_id(1);
			comment1.setComment_writer_id("1");
			comment1.setDiary_id(1);
			
			Comment comment2=new Comment();
			comment2.setComment_context("¯�̴�");
			comment2.setComment_id(2);
			comment2.setComment_writer_id("2");
			comment2.setDiary_id(1);
			
			list.add(comment2);
			list.add(comment1);
		}
		
		
		return list;
		
	}
}
