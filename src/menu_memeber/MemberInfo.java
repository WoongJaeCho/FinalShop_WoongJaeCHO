package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("=====[ 내정보 ]=====\n");
		System.out.println("[1] 비밀번호변경\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		cont = MallController.getInstance();
		String id = cont.getLoginId();
		memberDAO.printOneMemberInfo(id);
		int sel = Util.getValue("메뉴 ", 0, 2);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		else if (sel == 1) { // 기존 비밀번호 입력 -> 신규 비밀번호  동일 제한
			memberDAO.modifyPw(id);
		} else if (sel == 2) { // 멤버 화면 돌아가기
			cont.setNext("MemberMain"); 
		} 
		
		return false;
	}
}
