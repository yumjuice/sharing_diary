package controller1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;
import  user.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/user")
public class Join extends HttpServlet {
	///?��?��추�?
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        
        User user=new User();
        
        user.setUser_birth(req.getParameter("user_birth"));
        user.setUser_email(req.getParameter("user_email"));
        user.setUser_gender(req.getParameter("user_gender"));
        user.setUser_id(req.getParameter("user_id"));
        user.setUser_name(req.getParameter("user_name"));
        user.setUser_pw(req.getParameter("user_pw"));
        
       // System.out.println(req.getParameter("user_gender"));
        		
        
        UserDAO userDAO=new UserDAO();
        
        
        int result=userDAO.join(user);
        
        if(result==-1) {//?��?��
        	PrintWriter writer = res.getWriter(); 
        	writer.println("<script>alert('?��?��?���? 중복?��?��?��?��?��.?��?'); history.back(-1); </script>"); 
        	writer.close();
        }else {//?���?
        	PrintWriter writer = res.getWriter(); 
        	writer.println("<script>alert('?��️회?���??��?�� 축하?��립니?��?���?'); location.href='index.jsp';</script>"); 
        	writer.close();

        }
	}
}
