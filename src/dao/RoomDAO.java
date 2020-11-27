package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.RoomVO;

public class RoomDAO {
	private static RoomDAO dao = new RoomDAO();
	private RoomDAO(){}

	public static RoomDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1452", "it1452", "it1452");
			
		} catch (Exception ex) {
			System.out.println("오류발생: " + ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("���� �߻� : " + ex);				
			}
		}
	} // close
	
	public ArrayList<RoomVO> getRoomList(String user_id){
		ArrayList<RoomVO> list=new ArrayList<RoomVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomVO room = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from ROOMINFO where room_id in(select room_id from ROOMUSER where user_id=?)");
			pstmt.setString(1,user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				room=new RoomVO();
				room.setRoom_id(rs.getInt("room_id"));
				room.setRoom_img(rs.getString("room_img"));
				room.setRoom_name(rs.getString("room_name"));
				
				list.add(room);
				
			}

		} catch (Exception ex) {
			System.out.println("RoomDAO->getRoomList()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
}
