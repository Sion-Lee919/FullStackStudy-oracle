package main;

import java.util.Scanner;

import view.MemberDeleteView;
import view.MemberInsertView;
import view.MemberSelectListView;
import view.MemberSelectSearchListView;
import view.MemberSelectView;
import view.MemberUpdateView;
import view.View;

public class MemberMain {

	public static void main(String[] args) {
		View view = null;
		while(true) {
		System.out.println("회원관리 프로그램 메뉴");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 내 정보 조회");
		System.out.println("5. 회원 리스트 조회");
		System.out.println("6. 조건 조회");
		System.out.println("7. 종료");
		System.out.println("번호 입력 : ");
		Scanner sc = new Scanner(System.in);
		int menunumber = sc.nextInt();
		if(menunumber == 7) {
			System.out.println("회원관리 프로그램을 종료합니다.");
			break;
		}
		else if(menunumber == 1) {
			System.out.println("회원가입을 선택하셨습니다");
			//가입정보 키보드 입력 -> MemberDTO -> MemberDAO:insertMenber(MemberDTO)->저장
			view = new MemberInsertView();
		}
		else if(menunumber == 2) {
			System.out.println("회원 정보 수정을 선택하셨습니다");
			view = new MemberUpdateView();
		}
		else if(menunumber == 3) {
			System.out.println("회원 탈퇴를 선택하셨습니다");
			view = new MemberDeleteView();
		}
		else if(menunumber == 4) {
			System.out.println("내 정보 조회를 선택하셨습니다.");
			view = new MemberSelectView();
		}
		else if(menunumber == 5) {
			System.out.println("회원 리스트 조회를 선택하셨습니다.");
			view = new MemberSelectListView();
		}
		else if(menunumber == 6) {
			System.out.println("조건 조회를 선택하셨습니다.");
			view = new MemberSelectSearchListView();
		}
		view.input();
	}
}

}
/*1. 회원 가입
2. 회원 정보 수정
3. 회원 탈퇴
4. 내 정보 조회
5. 회원 리스트 조회
6. 검색 조회
7. 종료*/