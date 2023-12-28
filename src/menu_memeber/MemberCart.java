package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("=====[ 구매내역  ]=====\n");
		System.out.println("[1] 쇼핑하기\n[2] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		String id = cont.getLoginId();
		CartDAO cartDAO = CartDAO.getInstance();
		ItemDAO itemDAO = ItemDAO.getInstance();
		cartDAO.printOneShoppingList(itemDAO,id);
		
		int sel = Util.getValue("메뉴 ", 0, 2);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		}
		else if (sel == 1) { 
			cont.setNext("MemberShopping"); 
		} else if (sel == 2) { // 멤버 화면 돌아가기
			cont.setNext("MemberMain"); 
		} 
		return false;
	}

}
