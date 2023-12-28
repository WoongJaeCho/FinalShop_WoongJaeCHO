package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController cont;
	ItemDAO itemDAO = ItemDAO.getInstance();
	
	@Override
	public void init() { // 카테고리 출력 바람.
		cont = MallController.getInstance();
		
		System.out.printf("===== 쇼핑몰에 오신것을 환영합니다 =====\n");
		System.out.println("(0) 뒤로가기");
		itemDAO.printCategory();
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		CartDAO cartDAO = CartDAO.getInstance();
		String id = cont.getLoginId();
				
		cartDAO.choiceShoppingList(itemDAO,id);
		cont.setNext("MemberMain"); 
		return false;
	}

}
