package like;

public class LikeDAO {
	//�ش� user�� �ش� diary�� ���ƿ� �ߴ��� Ȯ��
	public boolean checkLike(String user_id,int diary_id) {
		if(user_id.equals("1") && diary_id==1) {
			return true;
		}
		return false;
	}
	//���ƿ� �߰�
	public boolean addLike(String user_id, int diary_id) {
		///��� ���ƿ� �߰�
		System.out.println("���ƿ� �߰�");
		return true;
	}
	
	public boolean removeLike(String user_id, int diary_id) {
		//��� ���ƿ� ����
		System.out.println("���ƿ� ����");
		return true;
	}
}
