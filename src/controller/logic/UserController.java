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
import vo.MemberVO;

public class UserController implements Controller {
	
	 String user_birth=null;
	 String user_email=null;
	 String user_gender=null;
	 String user_id=null;
	 String user_name=null;
	 String user_pw=null;
	 PrintWriter writer=null;
	 MemberVO member = new MemberVO();
	 MemberService service = MemberService.getInstance();
	 
	 
	 
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        writer =res.getWriter(); 
        
        
        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        user_id=(String)httpSession.getAttribute("id");
	   
	   
        user_birth = req.getParameter("user_birth");
		user_email = req.getParameter("user_email");
		user_gender = req.getParameter("user_gender");		
		user_name=req.getParameter("user_name");
		

		if (user_name==null || user_email==null|| user_birth==null|| user_gender==null|| user_name.isEmpty() || user_email.isEmpty()|| user_birth.isEmpty()|| user_gender.isEmpty()) {
			if(user_id==null) {
				req.setAttribute("error", "값을 모두 입력해주세요");
				HttpUtil.forward(req, res, "/join.jsp");
			}
			else {
				
			}
			return;
		}
		
	
		member.setUser_id(user_id);
		member.setUser_pw(user_pw);
		member.setUser_name(user_name);
		member.setUser_email(user_email);
		member.setUser_gender(user_gender);
		member.setUser_birth(user_birth);
		
		//회원가입
		 if(user_id==null) {
			user_id = req.getParameter("user_id");
			user_pw=req.getParameter("user_pw");
			if (user_id==null||user_pw==null|user_id.isEmpty() || user_pw.isEmpty()) {
				req.setAttribute("error", "값을 모두 입력해주세요");
				HttpUtil.forward(req, res, "/join.jsp");
				return;
			}
			member.setUser_id(user_id);
			member.setUser_pw(user_pw);
		    addUser();
		 }
	        
		 else {
			updateUser();
		 }
	
		
		   
		 //writer.println("<script>alert('읽을 권한이 없습니다.');location.href='main.do';</script>");
		     
		 writer.close();
        
	}
	
	
	public void updateUser() {
		service.updateMember(member);
		writer.println("<script>location.href='main.do';</script>");
	}
	
	public void addUser() {
		boolean isIdDuplicate= service.addMember(member);
		if(isIdDuplicate) {
			writer.println("<script>alert('회원가입이 완료되었습니다!');location.href='/SharedDiary';</script>");
		}else {
			writer.println("<script>alert('아이디가 중복되었습니다!');history.back();</script>");
		}
	}
}
