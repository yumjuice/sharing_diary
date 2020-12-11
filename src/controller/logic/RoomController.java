package controller.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import service.DiaryService;
import service.MemberService;
import service.RoomService;
import vo.DiaryVO;
import vo.MemberVO;
import vo.RoomVO;

public class RoomController  implements Controller {
	
	PrintWriter writer=null;
	String user_id=null;
	RoomService roomservice = RoomService.getInstance();
	MemberService memberservice=MemberService.getInstance();
	DiaryService diaryservice=DiaryService.getInstance();
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        writer =res.getWriter(); 
        
      //id 세션가져오기
        HttpSession httpSession = req.getSession();
        user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //method확인
	    String method = req.getParameter("method");
	    
	    if(method==null) {
	    	addRoom(req,res);
	    }
	    else if(method!=null && method.equals("leave")) {
	    	leaveRoom(req,res);
	    }
	    else if(method.equals("delete")) {
			deleteRoom(req, res);
		}
		else if(method.equals("update")){
			updateRoom(req,res);
			System.out.println("update");
		}
		
		
		writer.close();
	
		
	}
	
	//방생성
	public void addRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
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
		
		roomservice.addRoom(user_id, room, friendlist);
		
		writer.println("<script>alert('방이 생성되었습니다.');location.href='main.do'</script>");
		
	}
	
	//탈퇴하기
	public void leaveRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("leaveroom");
		String room_id = req.getParameter("room_id");
		if ( room_id==null || room_id.isEmpty()) {
			res.sendRedirect("main.do");
		}
		roomservice.deleteRoomUser(user_id,Integer.parseInt(room_id));
		res.sendRedirect("main.do");
	}
	
	//방 정보 수정
	public void updateRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String room_id = req.getParameter("room_id");
		
		String room_name = req.getParameter("room_name");
		String room_img = req.getParameter("room_img");
		String addFriend =req.getParameter("addFriendList");
		String removeFriend =req.getParameter("removeFriendList");
		
		if (  room_id==null || room_id.isEmpty() ||room_name==null || room_name.isEmpty() || room_img==null || removeFriend==null || addFriend==null) {
			res.sendRedirect("main.do");
			return;
		}
		System.out.println("addFriendList"+addFriend);
		System.out.println("removeFriendList"+removeFriend);
		List<String> addFriendList=new ArrayList<String>();
		addFriendList=Arrays.asList(addFriend.split(","));
		List<String> removeFriendList=new ArrayList<String>();
		removeFriendList=Arrays.asList(removeFriend.split(","));
		
		RoomVO room = new RoomVO();
		room.setRoom_name(room_name);
		room.setRoom_img(room_img);
		room.setRoom_id(Integer.parseInt(room_id));
		
		roomservice.updateRoom(user_id, room, addFriendList,removeFriendList);
			   
		writer.println("<script>alert('방이 수정되었습니다.');location.href='diaryList.do?roomId="+room_id+"';</script>");

		
		
	}
	
	
	//방삭제
	public void deleteRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String room_id = req.getParameter("room_id");
		if ( room_id==null || room_id.isEmpty()) {
			res.sendRedirect("main.do");
		}//방 삭제, 다이어리 삭제
		ArrayList<MemberVO> memberList=memberservice.memberList(Integer.parseInt(room_id));
		
		roomservice.deleteRoom(user_id,Integer.parseInt(room_id),memberList);
		diaryservice.deleteDiaryList(Integer.parseInt(room_id),user_id);
		res.sendRedirect("main.do");	     
		
	}

}
