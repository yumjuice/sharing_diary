package vo;

public class ExtendDiaryDTO {

	private DiaryVO diaryVO;
	private MemberVO memberVO;
	private RoomVO roomVO;
	public DiaryVO getDiaryVO() {
		return diaryVO;
	}
	public void setDiaryVO(DiaryVO diaryVO) {
		this.diaryVO = diaryVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public RoomVO getRoomVO() {
		return roomVO;
	}
	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}
}
