package controller.logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import controller.common.HttpUtil;
import service.MemberService;
import vo.DiaryVO;
import service.DiaryService;

public class DiaryWriteController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        HttpSession httpSession = req.getSession();
		
      //id 세션가져오기
        
		String user_id=(String)httpSession.getAttribute("id");
	    
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
        String room_id=req.getParameter("room_id");
        String title = req.getParameter("title");
		String feeling = req.getParameter("feeling");
		String context = req.getParameter("context");
		String imgaddr = req.getParameter("imgaddr");
		String date = req.getParameter("date");
		
		if(room_id.isEmpty() || room_id==null) {
			req.setAttribute("error", "잘못된 접근입니다.");
			HttpUtil.forward(req, res, "/main.do");
			return;
		}
		
		// ��ȿ�� üũ
		if (title.isEmpty() || feeling.isEmpty() || context.isEmpty()|| title==null || feeling==null || context==null|| imgaddr==null) {
			req.setAttribute("error", "값을 모두 입력해주세요");
			HttpUtil.forward(req, res, "/writeDiary.jsp?room_id="+room_id);
			return;
		}
		
		if(imgaddr.isEmpty() || imgaddr==null) {
			imgaddr="images/Logo.png";
		}
		int roomId=Integer.parseInt(room_id);
		
		DiaryVO diary =new DiaryVO();
		diary.setContext(context);
		diary.setDate(date);
		diary.setFeeling(feeling);
		diary.setImgaddr(imgaddr);
		diary.setTitle(title);
		diary.setWriter_id(user_id);
		diary.setRoom_id(roomId);
		
		
		DiaryService service = DiaryService.getInstance();
		service.addDiary(diary);
		PrintWriter writer = res.getWriter(); 
	   
	    writer.println("<script>alert('❤️일기가 작성되었습니다.❤️');location.href='diaryList.do?roomId="+room_id+"';</script>");
	    writer.close();   
	
	}

}
