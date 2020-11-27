package comment;

public class Comment {
	private int comment_id;
	private int diary_id;
	private String comment_writer_id;
	private String comment_context;
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getComment_writer_id() {
		return comment_writer_id;
	}
	public void setComment_writer_id(String comment_writer_id) {
		this.comment_writer_id = comment_writer_id;
	}
	public String getComment_context() {
		return comment_context;
	}
	public void setComment_context(String comment_context) {
		this.comment_context = comment_context;
	}
	
}
