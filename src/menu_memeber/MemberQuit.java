package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("=====[ %s 님 회원탈퇴 ]=====\n",cont.getLoginId());
		System.out.println("회원 탈퇴시 구매 내역이 사라집니다\n정말 탈퇴하시겠습니까?");
		System.out.println("[1] 예\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();
		String id = cont.getLoginId();
		int sel = Util.getValue("메뉴", 0, 2);
		if(sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) {//회원 삭제 / 구매 내역 삭제
			memberDAO.deleteMember(cartDAO, id);
			cont.setLoginId(null);
			cont.setNext("MallMain");
		} else if (sel == 2) {
			cont.setNext("MemberMain");
		}
		return false;
	}
	
}
