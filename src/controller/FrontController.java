package controller;

import java.io.*;


import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String charset = null;
	HashMap<String, Controller> list = null;


	@Override
	public void init(ServletConfig sc) throws ServletException {

		charset = sc.getInitParameter("charset");
		System.out.println("intint()");
		list = new HashMap<String, Controller>();
		
		list.put("/login.do", new LoginController());
		list.put("/main.do", new MainPageController());
		list.put("/diaryList.do", new DiaryListController());
		list.put("/addRoom.do", new AddRoomController());
		
		//list.put("/user.do", new JoinController());
		list.put("/writediary.do", new DiaryWriteController());
		list.put("/diarydetail.do",new DiaryDetailController());
		list.put("/deletediary.do", new DeleteDiaryController());
		list.put("/addcomment.do", new AddCommentController());
		list.put("/changelike.do", new ChangeLikeController());
		list.put("/editdiary.do", new EditPageController());
		list.put("/updatediary.do", new EditDiaryController());
		list.put("/checkuser.do",new IdCheckController());
		list.put("/updateRoom.do",new UpdateRoomController());
		list.put("/user.do",new UserController());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding(charset);

		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());

		Controller subController = list.get(path);
		subController.execute(request, response);
	}
	
}