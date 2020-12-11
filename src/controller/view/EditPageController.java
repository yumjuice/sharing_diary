package controller.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import controller.common.HttpUtil;
import service.DiaryService;
import vo.DiaryVO;

//다이어리 내용을 수정 jsp에 뿌려주기
public class EditPageController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    String page = req.getParameter("page");
	    String diary_id = req.getParameter("diary_id");
	    DiaryService diaryservice=DiaryService.getInstance();
	    DiaryVO diary=diaryservice.getDiary(Integer.parseInt(diary_id));
	    
	    req.setAttribute("diary", diary);
	    req.setAttribute("page", page);
		HttpUtil.forward(req, res, "/editDiary.jsp");
	}

}
