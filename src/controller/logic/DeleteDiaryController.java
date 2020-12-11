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

        //id ���ǰ�������
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    //����������
	    String page = req.getParameter("page");
	    //�� id
	    String room_id = req.getParameter("room_id");
	    //������ diary_id
	    String diaryIdstr = req.getParameter("diary_id");
	    if(diaryIdstr==null || diaryIdstr.isEmpty()) {
	    	PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>location.href='main.do';</script>");
		     
			writer.close();
			return;
	    }
	    int diary_id=Integer.parseInt(diaryIdstr);
	    
	    //������ ������ �ִ��� Ȯ��
	 
	    
	    //diary����
	    DiaryVO diary = new DiaryVO();
		diary.setDiary_id(diary_id);
		
		DiaryService diaryservice = DiaryService.getInstance();
		diaryservice.deleteDiary(diary,user_id);
		
		PrintWriter writer =res.getWriter(); 
		if(page.equals("main")) {
			 writer.println("<script>alert('������ �Ϸ�Ǿ����ϴ�');location.href='main.do';</script>");
		}
		else {
			 writer.println("<script>alert('������ �Ϸ�Ǿ����ϴ�');location.href='diaryList.do?roomId="+room_id+"';</script>");
		}
	     
		writer.close();
	    
	}
}
