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
    	
		//id 세션가져오기
	    String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //방id가져오기
	    int room_id=Integer.parseInt(req.getParameter("roomId"));
	    
	    //id가 해당방에 참여하지않는다면 접근권한이 없습니다.알럿 띄우기
	    RoomService roomservice =RoomService.getInstance();
     	boolean isUserInRoom= roomservice.checkUserInRoom(room_id, user_id);
     	System.out.println(isUserInRoom);
	    
     	if(isUserInRoom) {
		  //멤버 객체 가져오기
		    MemberService memberservice = MemberService.getInstance();
		    MemberVO memberVO=memberservice.getMember(user_id);
		    
		    //방정보반환
		    RoomVO roomVO=roomservice.getRoom(room_id);
		    
		    
		    //다이어리 리스트ㅡ 반환
		    ArrayList<DiaryVO> diaryList = diaryservice.getDiaryList(room_id);
		    
		    //참여중인 친구들리스트반환
		    ArrayList<MemberVO> memberList=memberservice.memberList(room_id);
		    
		    req.setAttribute("memberList", memberList);
		    req.setAttribute("user", memberVO);
		    req.setAttribute("diaryList", diaryList);
		    req.setAttribute("room", roomVO);
		    HttpUtil.forward(req, res, "/diaryList.jsp");
		   
     	}else {//권한없음
     		PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>alert('읽을 권한이 없습니다.');location.href='main.do';</script>");
		     
			writer.close();
     		
     	}
     	
	}

}
