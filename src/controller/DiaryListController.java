package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.DiaryService;

import vo.DiaryVO;
import service.MemberService;
import vo.RoomVO;
import service.RoomService;

import vo.MemberVO;


public class DiaryListController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		System.out.println("main");
		HttpSession httpSession = req.getSession();
		
		DiaryService diaryservice=DiaryService.getInstance();
    	
		//id ���ǰ�������
	    String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //��id��������
	    int room_id=Integer.parseInt(req.getParameter("roomId"));
	    
	    //id�� �ش�濡 ���������ʴ´ٸ� ���ٱ����� �����ϴ�.�˷� ����
	    RoomService roomservice =RoomService.getInstance();
     	boolean isUserInRoom= roomservice.checkUserInRoom(room_id, user_id);
     	System.out.println(isUserInRoom);
	    
     	if(isUserInRoom) {
		  //��� ��ü ��������
		    MemberService memberservice = MemberService.getInstance();
		    MemberVO memberVO=memberservice.getMember(user_id);
		    
		    //��������ȯ
		    RoomVO roomVO=roomservice.getRoom(room_id);
		    
		    
		    //���̾ ����Ʈ�� ��ȯ
		    ArrayList<DiaryVO> diaryList = diaryservice.getDiaryList(room_id);
		    
		    //�������� ģ���鸮��Ʈ��ȯ
		    ArrayList<MemberVO> memberList=memberservice.memberList(room_id);
		    
		    req.setAttribute("memberList", memberList);
		    req.setAttribute("user", memberVO);
		    req.setAttribute("diaryList", diaryList);
		    req.setAttribute("room", roomVO);
		    HttpUtil.forward(req, res, "/diaryList.jsp");
		   
     	}else {//���Ѿ���
     		PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>alert('���� ������ �����ϴ�.');location.href='main.do';</script>");
		     
			writer.close();
     		
     	}
     	
	}

}
