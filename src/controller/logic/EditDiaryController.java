package controller.logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import service.DiaryService;
import vo.DiaryVO;

public class EditDiaryController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    String room_id = req.getParameter("room_id");
	    String page = req.getParameter("page");
	    String title = req.getParameter("title");
	    String feeling = req.getParameter("feeling");
	    String context = req.getParameter("context");
	    String imgaddr = req.getParameter("imgaddr");
	    String diary_id = req.getParameter("diary_id");
	    
	    DiaryVO diary=new DiaryVO();
	    diary.setContext(context);
	    diary.setDiary_id(Integer.parseInt(diary_id));
	    diary.setFeeling(feeling);
	    diary.setImgaddr(imgaddr);
	    diary.setWriter_id(user_id);
	    diary.setTitle(title);
	    
	    DiaryService diaryservice=DiaryService.getInstance();
	    diaryservice.updateDiary(diary);
	    
		res.sendRedirect("diarydetail.do?diary_id="+diary_id+"&room_id="+room_id+"&page="+page);
	}


}
