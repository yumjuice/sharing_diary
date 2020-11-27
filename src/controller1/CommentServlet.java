package controller1;


import javax.servlet.http.HttpServlet;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import comment.Comment;
import comment.CommentDAO;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	///댓글 작성 기능
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");
        HttpSession httpSession = req.getSession();
        
	    String user_id=(String) httpSession.getAttribute("id");
        int diary_id=Integer.parseInt(req.getParameter("diary_id"));
        String comment_context=req.getParameter("comment_context");
        System.out.println("댓글:"+user_id+diary_id+comment_context);
        CommentDAO commentDAO= new CommentDAO();
        
        ///디비에 댓글 추가
        int result= commentDAO.putComment(user_id, diary_id, comment_context);
        PrintWriter writer = res.getWriter(); 
        //댓글 작성
        if(result ==1) {
        	writer.println("<script>location.href='diaryDetail.jsp?diary_id="+diary_id+"';</script>");
        }else {
        	writer.println("<script>alert('댓글 작성이 실패하였습니다.');history.back();</script>");
        }
        
        writer.close();
	}
	


}
