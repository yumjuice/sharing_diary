package controller1;

import javax.servlet.http.HttpServlet;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import diary.Diary;
import diary.DiaryDAO;
import user.UserDAO;
import  user.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	//λ‘κ·Έ?Έ ??Έ
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        String id=req.getParameter("id");
        String pw=req.getParameter("pw");
      
    	UserDAO userDAO=new UserDAO();
    	RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
    
    	int result=userDAO.login(id,pw);
    	PrintWriter writer = res.getWriter(); 
    	
    	
    	///1?Όκ²½μ° ?±κ³?
    	if(result==1) {
    		HttpSession httpSession = req.getSession();
    	
    	    httpSession.setAttribute("id", id);
    	    res.sendRedirect("main.jsp");
    	    
    	}else if(result==0) {
    		writer.println("<script>alert('λΉλ?λ²νΈκ°? ??? Έ?΅??€.?₯?'); history.back(-1);</script>"); 

    	}else {
    		writer.println("<script>alert('??΄??? λΉλ?λ²νΈκ°? ??? Έ?΅??€.?₯?'); history.back(-1);</script>"); 
    		
    	}
    	writer.close();
    	//0?Όκ²½μ° ??΄?λ§? ?±κ³?
    	//-1?Όκ²½μ° λΉλ?λ²νΈλ§? ?±κ³?
    	
    	
	}

	
    
}
