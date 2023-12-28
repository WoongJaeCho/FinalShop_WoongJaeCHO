package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 회원목록 ]=====");
		System.out.println("[1] 회원목록\n[2] 회원삭제\n[3] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();
		String id = cont.getLoginId();
		
		int sel = Util.getValue("메뉴 ", 0, 3);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) { // 회원 목록 출력
			memberDAO.printMember();
		} else if (sel == 2) { // 회원 삭제
			if(memberDAO.deleteMember(cartDAO, id)) {
				System.out.println(" 회원 삭제 완료");
			} else {
				System.out.println(" 회원 삭제 실패");
			}
		} else if (sel == 3) {
			cont.setNext("AdminMain"); //adminmain 화면
		} 
		
		return false;
	}
}
