package view;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberInsertView implements View {

	@Override
	public void input() {
		//가입정보 키보드 입력 -> MemberDTO -> MemberDAO:insertMenber(MemberDTO)->저장
		//이름,암호,폰,이메일
		MemberDTO dto = new MemberDTO();
		System.out.println("회원가입 정보를 입력하세요");
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디를 입력하세요 : ");
		String id = sc.next();
		System.out.print("이름을 입력하세요 : ");
		String name = sc.next();
		System.out.print("비밀번호 1~5자리를 입력하세요 : ");
		int pw =sc.nextInt();
		System.out.print("핸드폰번호를 입력하세요(형식 : 010-xxxx-xxxx) : ");
		String phone = sc.next();
		System.out.print("이메일을 입력하세요 : ");
		String email = sc.next();
		dto.setId(id);
		dto.setName(name);
		dto.setPw(pw);
		dto.setPhone(phone);
		dto.setEmail(email);
		System.out.println("회원 정보가 다음과 같이 입력되었습니다:");
        System.out.println(dto);
        
        MemberDAO dao = new MemberDAO();
        String result = dao.insertMenber(dto);
        System.out.println(result);
	}

}
