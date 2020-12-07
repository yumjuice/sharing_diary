package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.DiaryService;
import service.MemberService;

public class IdCheckController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json; charset=utf-8");
		String id=req.getParameter("id");
		MemberService memberservice = MemberService.getInstance();
		boolean isUserExist= memberservice.checkUserExist(id);
	    Gson gson=new Gson();
	    	
	    PrintWriter out = res.getWriter();
	    	
	    out.print(gson.toJson(isUserExist));
	    return;
	    
	}
	
	
}
