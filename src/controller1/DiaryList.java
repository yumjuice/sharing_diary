package controller1;

import java.io.IOException;
import java.io.PrintWriter;

import diary.DiaryDAO;
import room.RoomDAO;
import  diary.Diary;
import user.UserDAO;
import  user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/diarylist")
public class DiaryList extends HttpServlet {
	@Override
	
	////방 id별로 다이어리 목록 json으로 반환
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
        res.setContentType("application/json; charset=utf-8");

        int room_id=Integer.parseInt(req.getParameter("room_id"));
        
        System.out.println("room_id"+room_id);
        Gson gson=new Gson();
    	List<Map<String,String>> dic=new ArrayList();
    	
    	DiaryDAO diaryDAO=new DiaryDAO();
    	UserDAO userDAO=new UserDAO();
    	
    	ArrayList<Diary> list=diaryDAO.getList(room_id);
    	
    	PrintWriter out = res.getWriter();
    	
    	for(int i=0;i<list.size();i++){
    		System.out.println("writer_id"+list.get(i).getWriter_id());
    		User user=userDAO.getUser(list.get(i).getWriter_id());
    		Map<String,String> map =new HashMap();
    		map.put("diary_id",Integer.toString(list.get(i).getDiary_id()));
    		map.put("writer_name",user.getUser_name());
    		map.put("writer_id",list.get(i).getWriter_id());
    		map.put("title",list.get(i).getTitle());
    	
    		map.put("feeling",list.get(i).getFeeling());
    		map.put("context",list.get(i).getContext());
    		map.put("imgaddr",list.get(i).getImgaddr());
    		map.put("date",list.get(i).getDate());
    		
    		dic.add(map);
    		
    	}
    	out.print(gson.toJson(dic));
	}
}