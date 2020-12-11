package controller.logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import service.CommentService;
import vo.CommentVO;

public class AddCommentController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //유효성 검사
        String diary_id = req.getParameter("diary_id");
		String context = req.getParameter("comment_context");
		String room_id = req.getParameter("room_id");
	    String page = req.getParameter("page");
		
		if ( diary_id==null || diary_id.isEmpty() || context==null || context.isEmpty()) {
		
			res.sendRedirect("main.do");
			return;
		}
		CommentVO comment =new CommentVO();
		comment.setDiary_id(Integer.parseInt(diary_id));
		comment.setComment_context(context);
		comment.setWriter_id(user_id);
		
		CommentService service = CommentService.getInstance();
		service.addComment(comment);
		
		res.sendRedirect("diarydetail.do?diary_id="+diary_id+"&room_id="+room_id+"&page="+page);
		
	}
}
