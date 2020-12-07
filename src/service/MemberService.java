package service;

import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;

import vo.MemberVO;

public class MemberService {
	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService(){}

	public static MemberService getInstance() {
		return service;
	}

	//ȸ������
	public void addMember(MemberVO member) {
		dao.addMember(member);
	}
	
	
	//�α���
	public int login(String id,String pw) {
		int result = dao.login(id,pw);
		return result;
	}
	
	//�濡 �����ϴ� ������ü
	public ArrayList<MemberVO> memberList(int room_id){
		ArrayList<MemberVO> list = dao.getMemberList(room_id);
		return list;
	}
	
	public MemberVO getMember(String user_id) {
		MemberVO member=dao.getUser(user_id);
		return member;
	}
	
	//��� ������� ȸ������ Ȯ��
	public boolean checkUserExist(String id) {
		
		if(dao.getUser(id)==null) {
				return false;
		}
		
		return true;
	}
/*
	public void memberDelete(String id) {
		dao.memberDelete(id);
	}

*/
}
