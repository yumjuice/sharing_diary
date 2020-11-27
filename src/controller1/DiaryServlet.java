package controller1;

import javax.servlet.http.HttpServlet;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import diary.Diary;
import diary.DiaryDAO;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/diary")
public class DiaryServlet extends HttpServlet {
	///?ã§?ù¥?ñ¥Î¶? ?ûë?Ñ±
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        HttpSession httpSession = req.getSession();
        
	    String user_id=(String) httpSession.getAttribute("id");
        int room_id=Integer.parseInt(req.getParameter("room_id"));
        String title=req.getParameter("title");
        String feeling=req.getParameter("feeling");
        String context=req.getParameter("context");
        String imgaddr=req.getParameter("imgaddr");
        
        DiaryDAO diaryDAO=new DiaryDAO();
        int result=diaryDAO.putDiary(room_id,user_id,title,feeling,context,imgaddr);
        PrintWriter writer = res.getWriter(); 
    	
        if(result==1) {
        	writer.println("<script>alert('?ùºÍ∏? ?ûë?Ñ±?ù¥ ?ôÑÎ£åÎêò?óà?äµ?ãà?ã§.?üò?'); location.href='diaryList.jsp?room_id="+room_id+"';</script>");
        }else {
        	writer.println("<script>alert('?ùºÍ∏? ?ûë?Ñ±?ù¥ ?ã§?å®?ïò???äµ?ãà?ã§.')");
        }
        writer.close();
	}
        
}
