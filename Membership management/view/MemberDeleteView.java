package view;

import java.util.Scanner;

import dao.MemberDAO;

public class MemberDeleteView implements View {

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("탈퇴할 아이디를 입력하세요 : ");
		String id = sc.next();
		System.out.println("탈퇴할 암호를 입력하세요 : ");
		int pw = sc.nextInt();
		
		MemberDAO dao = new MemberDAO();
        String result = dao.DeleteMenber(id,pw);
        System.out.println(result);
	}

}
