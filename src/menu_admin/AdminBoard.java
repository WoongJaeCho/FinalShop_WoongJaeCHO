package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=====[ 관리자 게시판 ]=====");
		System.out.println("[1] 게시글목록\n[2] 게시글삭제\n[3] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		int sel = Util.getValue("메뉴 ", 0, 3);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) { // 게시글 목록 출력 (5개씩 끊어서)
			boardDAO.printBoard();
		} else if (sel == 2) { // 게시글 삭제
			boardDAO.deleteBoard();
		} else if (sel == 3) {
			cont.setNext("AdminMain"); //adminmain 화면
		} 
		 
		return false;
	}

}
