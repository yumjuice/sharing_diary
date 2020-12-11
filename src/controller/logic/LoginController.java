package controller.logic;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Controller;
import controller.common.HttpUtil;
import service.MemberService;

public class LoginController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
        res.setContentType("text/html; charset=utf-8");

        String id=req.getParameter("id");
        String pw=req.getParameter("pw");
        
        if (id==null || pw==null||id.isEmpty() || pw.isEmpty()) {
			req.setAttribute("error", "아이디와 비밀번호를 모두 입력해주세요!");
			HttpUtil.forward(req, res, "/index.jsp");
			return;
		}
      
     // Service ��ü�� �޼��� ȣ��
     	MemberService service = MemberService.getInstance();
     	int result= service.login(id,pw);
     	if(result==1) {//로그인 성공
     		//세션저장
     		HttpSession httpSession = req.getSession();
        	
    	    httpSession.setAttribute("id", id);
    	    
    	    res.sendRedirect("main.do");
     	}else if(result==0) {//비밀번호 틀림
     		req.setAttribute("alert", "비밀번호가 틀렸습니다.");
     		HttpUtil.forward(req, res, "/index.jsp");
     	
     	}else {//아이디 틀림
     		req.setAttribute("alert", "아이디가 틀렸습니다.");
     		HttpUtil.forward(req, res, "/index.jsp");
     	}
     	
	}

}








