package view;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberSelectView implements View {

	@Override
	public void input() {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("정보 조회할 아이디를 입력하세요 : ");
		String id = sc.next();
		System.out.println("암호를 입력하세요 : ");
		int pw = sc.nextInt();
		
		MemberDAO dao = new MemberDAO();
        MemberDTO dto = dao.getMember(id,pw);
        if(dto ==null) {
        	System.out.println("해당 아이디는 존재하지 않습니다.");
        }
        else {
        	if(dto.getName() == null) {
        		System.out.println("암호가 일치하지 않습니다.");
        	}
        	else {
        		System.out.println(dto.toString());
        	}
        }
	}

}
