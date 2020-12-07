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
		
		
		
		List<String> friendlist=new ArrayList<String>();
		friendlist=Arrays.asList(inviteList.split(","));
		
		RoomVO room = new RoomVO();
		room.setRoom_name(room_name);
		room.setRoom_img(room_img);
		RoomService roomservice = RoomService.getInstance();
		roomservice.addRoom(user_id, room, friendlist);
		PrintWriter writer =res.getWriter(); 
			   
		writer.println("<script>alert('방이 생성되었습니다.');location.href='main.do'</script>");
		     
		writer.close();
		
		
	}

}
