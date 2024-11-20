package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberSelectListView implements View {

	@Override
	public void input() {
		MemberDAO dao = new MemberDAO();
		int num = dao.getTotalCount();
		System.out.println(num);
		int countnum= num/3;
		int ctnum = num % 3;   // 7개 총 3페이지 나와야함 
		int count=0;
		if(num<3) {
			System.out.println("1페이지까지 입력 가능합니다.");
		}
		else if(ctnum !=0) {
			count = ++countnum;
			System.out.println(count+"페이지까지 입력 가능합니다.");
		}
		else if(ctnum==0) {
			count= countnum;
			System.out.println(count+"페이지까지 입력 가능합니다.");
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("페이지 번호를 입력 하세요.");
		int pagenumber = sc.nextInt();
		ArrayList<MemberDTO> result = dao.getMembers(pagenumber, count);
		System.out.println(result);
	}

}
