package controller.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import controller.common.HttpUtil;
import service.RoomService;
import service.DiaryService;
import vo.DiaryVO;
import service.MemberService;
import vo.MemberVO;
import service.CommentService;
import vo.CommentVO;
import service.LikeService;

public class DiaryDetailController  implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //get diary_id & room_id &이전페이지
	    String diaryIdstr = req.getParameter("diary_id");
	    String roomIdstr = req.getParameter("room_id");
	    String page = req.getParameter("page");
	    if(diaryIdstr==null || diaryIdstr.isEmpty()||roomIdstr==null || roomIdstr.isEmpty()) {
	    	PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>location.href='main.do';</script>");
		     
			writer.close();
	    }
	    int diary_id=Integer.parseInt(diaryIdstr);
	    int room_id=Integer.parseInt(roomIdstr);
	    System.out.println("ddd"+diary_id);
	    System.out.println("rrr"+room_id);
	    //볼 수 있는 권한이 있는지 확인
	    //->작성된 다이어리의 room_id에 user_id가 참여하는지 확인
	    RoomService roomservice =RoomService.getInstance();
     	boolean isUserInRoom= roomservice.checkUserInRoom(room_id, user_id);
     	System.out.println(isUserInRoom);
     	
     	//권한 있음
     	if(isUserInRoom) {
     		DiaryService diaryservice =DiaryService.getInstance();
     		DiaryVO diaryVO=diaryservice.getDiary(diary_id);
     		MemberService memberservice =MemberService.getInstance();
     		MemberVO memberVO=memberservice.getMember(user_id);
     		
     		CommentService commentservice =CommentService.getInstance();
     		ArrayList<CommentVO> commentList=commentservice.getCommentList(diary_id);
     		
     		LikeService likeservice =LikeService.getInstance();
     		boolean isLike=likeservice.isLike(user_id, diary_id);
     		
     		String master_id=roomservice.getRoom(room_id).getMaster_id();
     		
     		req.setAttribute("diary", diaryVO);
     		req.setAttribute("user", memberVO);
     		req.setAttribute("page", page);
     		req.setAttribute("commentList", commentList);
     		req.setAttribute("like", isLike);
     		req.setAttribute("master_id", master_id);
    		HttpUtil.forward(req, res, "/diaryDetail.jsp");
     	}else { //권한없음
     		PrintWriter writer =res.getWriter(); 
			   
		    writer.println("<script>alert('읽을 권한이 없습니다.');location.href='main.do';</script>");
		     
			writer.close();
     	}
	}

}
