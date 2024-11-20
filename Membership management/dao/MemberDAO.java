package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDTO;
import view.MemberSelectView;
import view.View;


public class MemberDAO {
	//회원가입
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
	
	//회원 정보 수정
	public String UpdateMenber(MemberDTO dto) {
		String msg="";
		Connection con = null;
		try {

		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		
		String selectSQL = "select id from members where id=?";
		PreparedStatement pt1 = con.prepareStatement(selectSQL);
		pt1.setString(1, dto.getId());
	 	ResultSet rs = pt1.executeQuery();
	 	if(!rs.next()) {
	 		msg = ("아이디가 일치하지 않습니다. 다시입력하세요");
	 		return msg;
	 	}
	 	else {
	 		msg = (" 아이디가 일치합니다");
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
	
	//회원 탈퇴
	public String DeleteMenber(String id,int pw) {
		String msg="";
		Connection con = null;
		try {
	
		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		//회원 존재여부+ 암호여부
		String selectSQL = "select id, pw from members where id=?";
		PreparedStatement pt2 = con.prepareStatement(selectSQL);
		pt2.setString(1, id);
	 	ResultSet rs = pt2.executeQuery();

	 	if(rs.next()) {
	 		if(pw == rs.getInt("pw")) {
	 			String deleteSQL = "delete from members where id=?";
	 			PreparedStatement pt = con.prepareStatement(deleteSQL);
	 			pt.setString(1, id);
	 			pt.executeUpdate();
	 			msg = "성공적으로 탈퇴처리되었습니다.";
	 		}
	 		else {
	 			msg="입력하신 암호는 회원님의 암호와 다릅니다.";
	 		}
	 	}	
	 	else {
			msg = "가입된 회원이 아닙니다.";
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
	
	//내 정보 조회
	public MemberDTO getMember(String id,int pw) {
		MemberDTO dto = null;
		Connection con = null;
		try {
	
		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		//회원 존재여부+ 암호여부
		String selectSQL = "select id, name,pw,phone,email,to_char(regdate,'yyyy\"년도\"mm\"월\"dd\"일\"') regdate from members where id=?";
		PreparedStatement pt1 = con.prepareStatement(selectSQL);
		pt1.setString(1, id);
	 	ResultSet rs = pt1.executeQuery();

	 	if(rs.next()) {
	 		if(pw == rs.getInt("pw")) {
	 			dto = new MemberDTO();
	 			dto.setId(rs.getString("id"));
	 			dto.setName( rs.getString("name"));
	 			dto.setPw( rs.getInt("pw"));
	 			dto.setPhone (rs.getString("phone"));
	 			dto.setEmail (rs.getString("email"));
	 			dto.setRegdate(rs.getString("regdate"));	 			
	 		}
	 		else {
	 			dto = new MemberDTO();
	 			dto.setId(rs.getString("id"));
	 		}
	 	}	
	 	//else { dto는 이미 null이여서 쓸 필요가 없음
	 	//}
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
		return dto;
	}

	//회원리스트 조회 페이지
	public int getTotalCount() {
		int cm=0;
		Connection con = null;
		try {
	
		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		//회원 존재여부+ 암호여부
		String selectSQL = "select count(*) cm from members";
		PreparedStatement pt1 = con.prepareStatement(selectSQL);
	 	ResultSet rs = pt1.executeQuery();
	 	
	 	if(rs.next()) {
	 		cm= rs.getInt("cm");
	 		
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
		return cm ;
	}
	
	//회원리스트 조회
	public ArrayList<MemberDTO> getMembers(int pagenumber, int count) {
		 ArrayList<MemberDTO> dtolist=new ArrayList<MemberDTO>();
		Connection con = null;
		try {
	
		Class.forName(DBInfo.driver);
		con = DriverManager.getConnection
				(DBInfo.url,DBInfo.account,DBInfo.password);
		System.out.println("db 연결 성공");
		//회원 존재여부+ 암호여부
		int begin = (pagenumber-1)*3 +1;
		int end =begin+2;
		String selectSQL = "select id,name,phone,email,to_char(regdate,'yyyy\"년도\"mm\"월\"dd\"일\"') regdate from (select rownum r,mem.* from(select * from members order by regdate desc ) mem) where r>=? and r<=? ";
		PreparedStatement pt1 = con.prepareStatement(selectSQL);
		pt1.setInt(1, begin);
		pt1.setInt(2, end);
	 	ResultSet rs = pt1.executeQuery();
	 	
	 	while(rs.next()) {
	 		MemberDTO dto1 = new MemberDTO();
 			dto1.setId(rs.getString("id"));
 			dto1.setName( rs.getString("name"));
 			dto1.setPhone (rs.getString("phone"));
 			dto1.setEmail (rs.getString("email"));
 			dto1.setRegdate(rs.getString("regdate"));
 			dtolist.add(dto1);
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
		return dtolist;
	}	
	
	
	//검색조회
	public ArrayList<MemberDTO> getMembers(String item, String searchword){
		
	}
}

