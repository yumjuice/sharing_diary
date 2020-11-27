package controller;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import controller.HttpUtil;
import vo.MemberVO;

import service.MemberService;
public class JoinController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        String user_birth = req.getParameter("user_birth");
		String user_email = req.getParameter("user_email");
		String user_gender = req.getParameter("user_gender");
		String user_id = req.getParameter("user_id");
		String user_name=req.getParameter("user_name");
		String user_pw=req.getParameter("user_pw");

		// ��ȿ�� üũ
		if (user_id.isEmpty() || user_pw.isEmpty() || user_name.isEmpty() || user_email.isEmpty()|| user_birth.isEmpty()|| user_gender.isEmpty()) {
			req.setAttribute("error", "값을 모두 입력해주세요");
			HttpUtil.forward(req, res, "/join.jsp");
			return;
		}

		// VO��ü�� ����Ÿ ���ε�
		MemberVO member = new MemberVO();
		member.setUser_id(user_id);
		member.setUser_pw(user_pw);
		member.setUser_name(user_name);
		member.setUser_email(user_email);
		member.setUser_gender(user_gender);
		member.setUser_birth(user_birth);

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		service.addMember(member);
		req.setAttribute("alert", "회원가입이 완료되었습니다!");
		// Output View �������� �̵�
		HttpUtil.forward(req, res, "/index.jsp");

	}
}
