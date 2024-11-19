package main;

import java.util.Scanner;

import view.MemberInsertView;
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
			view.input();
		}
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