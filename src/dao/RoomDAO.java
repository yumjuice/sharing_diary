package dao;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			//Class.forName("com.mysql.cj.jdbc.Driver");
	        //conn = DriverManager.getConnection("jdbc:mysql://192.168.1.159:3306/sharingdb?serverTimezone=UTC", "1234", "1234");
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
	
	//사용자가 참여하는 방 리스트 반환
	public ArrayList<RoomVO> getRoomList(String user_id){
		ArrayList<RoomVO> list=new ArrayList<RoomVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RoomVO room = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from ROOMINFO where use_yn='y' AND room_id in(select room_id from ROOMUSER where user_id=?)");
			pstmt.setString(1,user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				room=new RoomVO();
				room.setRoom_id(rs.getInt("room_id"));
				room.setRoom_img(rs.getString("room_img"));
				room.setRoom_name(rs.getString("room_name"));
				room.setMaster_id(rs.getString("master_id"));
				list.add(room);
				
			}

		} catch (Exception ex) {
			System.out.println("RoomDAO->getRoomList()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
	
	//방 정보반환
	public RoomVO getRoom(int room_id) {
		
			RoomVO room=new RoomVO();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		

			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from ROOMINFO where room_id=? and use_yn='y'");
				pstmt.setInt(1, room_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					
					room.setRoom_id(rs.getInt("room_id"));
					room.setRoom_img(rs.getString("room_img"));
					room.setRoom_name(rs.getString("room_name"));
					room.setMaster_id(rs.getString("master_id"));
					
				}

			} catch (Exception ex) {
				System.out.println("roomDAO->getRoom()오류 : " + ex);
			
			} finally {
				close(conn, pstmt, rs);
			}
			
			return room;
	
		
	}
	
	//마지막 room_id가져옴
	public int getLastId() {
		int lastId=-1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select max(room_id) from ROOMINFO");
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastId=rs.getInt(1);
				
			}

		} catch (Exception ex) {
			System.out.println("roomDAO->getLastId()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		
		return lastId;
	}
	
	//방정보추가
	public void addRoom(RoomVO room,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("INSERT INTO ROOMINFO (room_id, room_name ,room_img, use_yn,creator,create_time,modifier,modify_time,master_id) values (?,?,?, 'y',?, NOW(), ?, NOW(),?);");
			pstmt.setInt(1, room.getRoom_id());
			pstmt.setString(2, room.getRoom_name());
			pstmt.setString(3, room.getRoom_img());
			pstmt.setString(4, user_id);
			pstmt.setString(5, user_id);
			pstmt.setString(6, user_id);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("RoomDAO-> addRoom오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	
	}
	//방수정
	public void updateRoom(RoomVO room,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE ROOMINFO SET room_name=? ,room_img=?, modifier=?,modify_time=NOW();");
			
			pstmt.setString(1, room.getRoom_name());
			pstmt.setString(2, room.getRoom_img());
			pstmt.setString(3, user_id);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("RoomDAO-> addRoom오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
	////방과 참여자들 추가
	public void addRoomUser(RoomVO room,List<String> userList,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(userList);
		System.out.println(user_id);
	
		try {
			conn = connect();
			if(user_id!=null) {
				pstmt = conn.prepareStatement("INSERT INTO ROOMUSER values (?,?, 'y',?, NOW(), ?, NOW());");
				pstmt.setInt(1, room.getRoom_id());
				pstmt.setString(2, user_id);
				pstmt.setString(3, user_id);
				pstmt.setString(4, user_id);
				pstmt.executeUpdate();
			}
			for(int i=0;i<userList.size();i++) {
				pstmt = conn.prepareStatement("INSERT INTO ROOMUSER values (?,?, 'y',?, NOW(), ?, NOW());");
				pstmt.setInt(1, room.getRoom_id());
				pstmt.setString(2, userList.get(i));
				pstmt.setString(3, user_id);
				pstmt.setString(4, user_id);
				pstmt.executeUpdate();
			}
			
		} catch (Exception ex) {
			System.out.println("RoomDAO-> addRoomUser오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
	
	//이미 만들어진 방에 참여자들만 추가
	public void addUser(RoomVO room,List<String> userList,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println(userList);
		System.out.println(user_id);
		
		try {
			conn = connect();
				
			for(int i=0;i<userList.size();i++) {
				pstmt = conn.prepareStatement("INSERT INTO ROOMUSER values (?,?, 'y',?, NOW(), ?, NOW());");
				pstmt.setInt(1, room.getRoom_id());
				pstmt.setString(2, userList.get(i));
				pstmt.setString(3, user_id);
				pstmt.setString(4, user_id);
				pstmt.executeUpdate();
			}
				
		} catch (Exception ex) {
			System.out.println("RoomDAO-> addRoomUser오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	 
	//현재 방에 유저가 있는지 확인
	public boolean checkUserInRoom(int room_id,String user_id) {
		boolean isUserInRoom=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from ROOMUSER where room_id=? and user_id=? and use_yn='y'");
			pstmt.setInt(1, room_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isUserInRoom=true;
			}

		} catch (Exception ex) {
			System.out.println("roomDAO->getRoom()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		
		return isUserInRoom;
	}
	public boolean checkUserExistsRoom(int room_id,String user_id) {
		boolean isUserInRoom=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from ROOMUSER where room_id=? and user_id=?");
			pstmt.setInt(1, room_id);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isUserInRoom=true;
			}

		} catch (Exception ex) {
			System.out.println("roomDAO->getRoom()오류 : " + ex);
		
		} finally {
			close(conn, pstmt, rs);
		}
		
		return isUserInRoom;
	}

	//방에서 삭제된 유저 다시 초대 
	public void reAddRoomUser(RoomVO room,String friend,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
	
			pstmt = conn.prepareStatement("UPDATE ROOMUSER SET use_yn='y',modifier=?,modify_time=NOW() where room_id=? and user_id=?;");
				
			pstmt.setString(1, user_id);
			pstmt.setInt(2, room.getRoom_id());
			pstmt.setString(3, friend);
			pstmt.executeUpdate();
		}catch (Exception ex) {
			System.out.println("RoomDAO-> reAddRoomUser오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
	//방에서 유저 삭제
	public void removeRoomUser(int room_id,List<String> userList,String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
		
			for(int i=0;i<userList.size();i++) {
				pstmt = conn.prepareStatement("UPDATE ROOMUSER SET use_yn='n',modifier=?,modify_time=NOW() where room_id=? and user_id=?;");
				
				pstmt.setString(1, user_id);
				pstmt.setInt(2, room_id);
				pstmt.setString(3, userList.get(i));
				pstmt.executeUpdate();
			}
			
		} catch (Exception ex) {
			System.out.println("RoomDAO-> removeRoomUser오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	//방 삭제
	public void deleteRoom(String user_id,int room_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE ROOMINFO SET use_yn='n',modifier=?,modify_time=NOW() where room_id=?;");
					
			pstmt.setString(1, user_id);
			pstmt.setInt(2, room_id);
			pstmt.executeUpdate();
		
				
		} catch (Exception ex) {
			System.out.println("RoomDAO-> removeRoomUser오류 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}
	
}
