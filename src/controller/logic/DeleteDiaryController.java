package controller.logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import service.DiaryService;
import vo.DiaryVO;

public class DeleteDiaryController  implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    //이전페이지
	    String page = req.getParameter("page");
	    //방 id
	    String room_id = req.getParameter("room_id");
	    //삭제할 diary_id
	    String diaryIdstr = req.getParameter("diary_id");
	    if(diaryIdstr==null || diaryIdstr.isEmpty()) {
	    	PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>location.href='main.do';</script>");
		     
			writer.close();
			return;
	    }
	    int diary_id=Integer.parseInt(diaryIdstr);
	    
	    //삭제할 권한이 있는지 확인
	 
	    
	    //diary삭제
	    DiaryVO diary = new DiaryVO();
		diary.setDiary_id(diary_id);
		
		DiaryService diaryservice = DiaryService.getInstance();
		diaryservice.deleteDiary(diary,user_id);
		
		PrintWriter writer =res.getWriter(); 
		if(page.equals("main")) {
			 writer.println("<script>alert('삭제가 완료되었습니다');location.href='main.do';</script>");
		}
		else {
			 writer.println("<script>alert('삭제가 완료되었습니다');location.href='diaryList.do?roomId="+room_id+"';</script>");
		}
	     
		writer.close();
	    
	}
}
