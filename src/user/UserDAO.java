package user;

public class UserDAO {
	//id pw �̸� ������� ���� �̸���
	//�α��� Ȯ��
	public int login(String id, String pw) {
	
		if (id.equals("1")) {
			
			if(pw.equals("1")) {
				
				return 1; //�α��� ����
			}return 0;//��й�ȣ�� Ʋ�Ƚ��ϴ�.
		}else if(id.equals("2")) {
			if(pw.equals("2")) {
				return 1;
			}return 0;
		}
	
		return -1;//���̵� Ʋ�Ƚ��ϴ�.
	}
	//�ش� id�� ������ü ��ȯ
	public User getUser(String id) {
		User user=new User();
		if (id.equals("1")) {
			user.setUser_id("1");
			user.setUser_pw("1");
			user.setUser_birth("1996.11.07");
			user.setUser_email("aa@naver.com");
			user.setUser_gender("��");
			user.setUser_name("������");
		}
		else if (id.equals("2")) {
			user.setUser_id("2");
			user.setUser_pw("2");
			user.setUser_birth("1996.10.11");
			user.setUser_email("bb@daum.com");
			user.setUser_gender("��");
			user.setUser_name("�Ѻ�");
		}
		return user;
		 
		 
	}
	///���̵� �ߺ�Ȯ��
	public int join(User user) {
		//���̵� �ߺ�Ȯ��
		if(user.getUser_id().equals("1")){//�ߺ� �� 
			return -1;
		}
		else return 1;
	}
	
//////��id������ �������� �������̵� ���
}
