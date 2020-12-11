package controller.logic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.common.Controller;
import service.LikeService;

public class ChangeLikeController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("application/json; charset=utf-8");

        //id 세션가져오기
        HttpSession httpSession = req.getSession();
        String user_id=(String)httpSession.getAttribute("id");
	   
	    if(user_id==null) {
	    	res.sendRedirect("index.jsp");
	    }
	    
	    //유효성 검사
        String diary_id = req.getParameter("diary_id");
        if(diary_id==null || diary_id.isEmpty()) {
	    	res.sendRedirect("main.do");
	    }
        
        LikeService likeservice=LikeService.getInstance();
        
        likeservice.changeLike(user_id, Integer.parseInt(diary_id));
       
    
    	PrintWriter out = res.getWriter();
    	
    	out.print("success");
    	
    	out.close();
		
	}

}
