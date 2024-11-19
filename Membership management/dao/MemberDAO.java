package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;


public class MemberDAO {
	public String insertMenber(MemberDTO dto) {
		String msg="";
		Connection con = null;
		try {

		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		//dto.getId() member 테이블 중복검사
		String selectSQL = "select id from members where id=?";
		PreparedStatement pt2 = con.prepareStatement(selectSQL);
		pt2.setString(1, dto.getId());
	 	ResultSet rs = pt2.executeQuery();
		//members id pk (1개/0개/n개(x))
	 	if(rs.next()) {
	 		//중복
	 		msg = ("중복된 아이디입니다. 다른 아이디를 입력하세요");
	 	}
	 	else {
	 		//중복x
			String insertSQL = "insert into members values(?,?,?,?,?,sysdate)";
			PreparedStatement pt = con.prepareStatement(insertSQL);
			pt.setString(1,dto.getId());
			pt.setString(2,dto.getName());
			pt.setInt(3,dto.getPw());
			pt.setString(4,dto.getPhone());
			pt.setString(5,dto.getEmail());
			int rows = pt.executeUpdate();
			msg = rows+"명의 회원가입 성공했습니다.";
	 	}
		con.close(); 
		System.out.println("db연걸 해제 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("잘못된 드라이버 이름 혹은 classpath잘못되었을 수 있습니다.");
			System.out.println( "module-info.java 미설정 확인하세요");
		}catch(SQLException e) {
			System.out.println("db 연결 실패-연결 정보를 확인하세요");
			e.printStackTrace();
		}finally {
			try {
			if(!con.isClosed()) {
				con.close();
				System.out.println("finally - db연걸 해제 성공");
				}
			}catch(Exception e) {}
		}
		return msg;
	}
}

