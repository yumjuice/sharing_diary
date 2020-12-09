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

import service.DiaryService;
import service.MemberService;
import service.RoomService;
import vo.DiaryVO;
import vo.RoomVO;
import vo.MemberVO;

public class UpdateRoomController implements Controller {
	
	RoomService roomservice = RoomService.getInstance();
	MemberService memberservice=MemberService.getInstance();
	DiaryService diaryservice=DiaryService.getInstance();
	String user_id=null;
	String room_id=null;
	String method=null;
	PrintWriter writer=null;
	
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
	   
	    room_id= req.getParameter("room_id"); 
		method=req.getParameter("method");
		
		if(room_id==null ||room_id.isEmpty()||method==null||method.isEmpty()) {
			res.sendRedirect("main.do");
			return;
		}
		
		if(method.equals("delete")) {
			deleteRoom(req, res);
		}
		else {
			updateRoom(req,res);
		}	
		
		writer.close();
	}
	
	
	public void deleteRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//방 삭제, 다이어리 삭제
		ArrayList<MemberVO> memberList=memberservice.memberList(Integer.parseInt(room_id));
		ArrayList<DiaryVO> diaryList=diaryservice.getDiaryList(Integer.parseInt(room_id));
		roomservice.deleteRoom(user_id,Integer.parseInt(room_id),memberList);
		diaryservice.deleteDiaryList(Integer.parseInt(room_id),user_id);
		res.sendRedirect("main.do");	     
		
	}
	
	public void updateRoom(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String room_name = req.getParameter("room_name");
		String room_img = req.getParameter("room_img");
		String addFriend =req.getParameter("addFriendList");
		String removeFriend =req.getParameter("removeFriendList");
		
		if ( room_name==null || room_name.isEmpty() || room_img==null || removeFriend==null || addFriend==null) {
			res.sendRedirect("main.do");
			return;
		}
		
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

}
