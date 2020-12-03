package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.RoomService;
import vo.RoomVO;

public class AddRoomController  implements Controller {
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
        String room_name = req.getParameter("room_name");
		String room_img = req.getParameter("room_img");
		String inviteList =req.getParameter("inviteList");
		
		System.out.println(inviteList+"DDd");
		
		if ( room_name==null || room_name.isEmpty() || room_img==null || inviteList==null) {
		
			res.sendRedirect("main.do");
			return;
		}
		
		//초대한 아이디들이 모두 유효한 아이디인지 확인
		List<String> friendlist=new ArrayList<String>();
		boolean userListCheck;
		if(inviteList=="") {
			userListCheck=true;
		}
		else{
			
			friendlist=Arrays.asList(inviteList.split(","));
			System.out.println(friendlist);
			MemberService memberservice = MemberService.getInstance();
			userListCheck= memberservice.checkUserList(friendlist);
			
		}
		
		//성공시 방추가 실패시 돌아가기	
		if(userListCheck) {
			RoomVO room = new RoomVO();
			room.setRoom_name(room_name);
			room.setRoom_img(room_img);
			RoomService roomservice = RoomService.getInstance();
			roomservice.addRoom(user_id, room, friendlist);
			PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>alert('방이 생성되었습니다.');location.href='main.do'</script>");
		     
			writer.close();
		}else {
			PrintWriter writer = res.getWriter(); 
			   
		    writer.println("<script>alert('유효하지 않은 ID가 있습니다.');history.back();</script>");
		    writer.close(); 
		}
	}

}
