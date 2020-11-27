package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.HttpUtil;
import service.MemberService;
import vo.MemberVO;
import service.DiaryService;
import vo.DiaryVO;
import service.RoomService;
import vo.RoomVO;

public class MainPageController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		
		HttpSession httpSession = req.getSession();
    	
	    String user_id=(String)httpSession.getAttribute("id");
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    //**유저이름, 다이어리 정보,  방정보 전달
	    //멤버 객체 가져오기
	    MemberService memberservice = MemberService.getInstance();
	    MemberVO memberVO=memberservice.getMember(user_id);
	    
	    
	    //다이어리 정보 가져오기
	    DiaryService diaryservice=DiaryService.getInstance();
	    int pageNum=diaryservice.getPageNum(user_id);
	    
	    ArrayList<DiaryVO> diaryList = diaryservice.getDiaryPage(1,user_id);
	    
	    
	    
	    //방 정보 가져오기
	    RoomService roomservice=RoomService.getInstance();
	    ArrayList<RoomVO> roomList=roomservice.getRoomList(user_id);
	    
	    req.setAttribute("roomList", roomList);
	    req.setAttribute("user", memberVO);
	    req.setAttribute("diaryList", diaryList);
	    req.setAttribute("pageNum", pageNum);
		HttpUtil.forward(req, res, "/main.jsp");
		
	}
}
	
