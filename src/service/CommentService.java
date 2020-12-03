package service;

import java.util.ArrayList;

import dao.CommentDAO;
import vo.CommentVO;

public class CommentService {
	private static CommentService service = new CommentService();
	public CommentDAO dao = CommentDAO.getInstance();
	
	private CommentService(){}

	public static CommentService getInstance() {
		return service;
	}
	
	public void addComment(CommentVO comment) {
		dao.addComment(comment);
	}
	
	public ArrayList<CommentVO> getCommentList(int diary_id){
		ArrayList<CommentVO> commentList=dao.getCommentList(diary_id);
		return commentList;
	}
}
