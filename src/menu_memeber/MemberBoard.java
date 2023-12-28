package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("=====[ 게시판 ]=====\n");
		System.out.println("[1] 게시글보기\n[2] 게시글추가\n[3] 내개시글(삭제)\n"
				+ "[4] 뒤로가기\n[0] 종료");
		System.out.println("=====================");
	}

	@Override
	public boolean update() {
		BoardDAO boardDAO = BoardDAO.getInstance();
		String id = cont.getLoginId();
		
		int sel = Util.getValue("메뉴 ", 0, 4);
		if (sel == 0) {
			System.out.println("[ 프로그램 종료 ]");
			cont.setNext(null);
		} else if (sel == 1) { 
			boardDAO.printBoard();
		} else if (sel == 2) { 
			boardDAO.addBoard(id);
		} else if (sel == 3) { 
			boardDAO.printOneMemberBoard(id);
		} else if (sel == 4) { // 멤버 화면 돌아가기
			cont.setNext("MemberMain"); 
		} 

		return false;
	}
}
