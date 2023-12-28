package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 쇼핑몰관리 ]=====");
		System.out.println("카테고리 순으로 정렬 카테고리가 같으면 아이템 이름순으로 정렬");
		System.out.println("[1] 아이템 추가\n[2] 아이템 삭제\n"
				+ "[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)\n[4] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		ItemDAO itemDAO = ItemDAO.getInstance();
		CartDAO cartDAO = CartDAO.getInstance();
		
		int sel = Util.getValue("메뉴 ", 0, 4);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) { // 아이템 추가
			itemDAO.addOneItem();
		} else if (sel == 2) { // 아이템 삭제
			itemDAO.deleteOneItme(cartDAO);
		} else if (sel == 3) { // 총 매출 아이템 갯수 (판매량 순)
			cartDAO.printSaleList(itemDAO);
		} else if (sel == 4) {
			cont.setNext("AdminMain"); //adminmain 화면
		}
		return false;
	}
}
