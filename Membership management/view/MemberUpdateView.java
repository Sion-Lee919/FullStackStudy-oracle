package view; 

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberUpdateView implements View {

	@Override
	public void input() {
		
		MemberDTO dto = new MemberDTO();
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.next();
		System.out.println("비밀번호를 입력하세요(최대 5자리) : ");
		int pw =sc.nextInt();
		
		System.out.println("수정항목중 하나를 입력하세요 1.이름 2.이메일 3.핸드폰번호");
		int num = sc.nextInt();
		if(num==1) {
		System.out.println("변경 이름을 입력하세요 : ");
		String name =sc.next();
		}
		else if (num==2) {
			System.out.println("변경 이메일 입력하세요 : ");
			String email =sc.next();
			}
		else {
		System.out.println("변경 핸드폰번호를 입력하세요(형식 : 010-xxxx-xxxx) :");
		String phone =sc.next();
		}
	       MemberDAO dao = new MemberDAO();
	        String result = dao.UpdateMenber(dto);
	        System.out.println(result);
	}

}
//아이디 암호 입력
//수정항목(이름 이메일 폰번호 중 1개 입력가능): 이름
//수정값: xxxxx
//수정되었습니다.
//수정될 수 없습니다 : 아이디가 존재하지 않습니다
//수정될 수 없습니다 : 암호가 일치하지 않습니다.
//수정될 수 없습니다 : 해당 항목은 수정불가능항목입니다.
