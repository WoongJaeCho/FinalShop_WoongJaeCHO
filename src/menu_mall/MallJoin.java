package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController cont;
	

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=====[ 회원 가입 ]=====");
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		
		while(true) {
			String id = Util.getValue("아이디 ");
			int idx = dao.checkId(id);
			if(idx!=-1) {
				System.out.println("중복 ID가 있습니다.");
				return false;
			}
			String pw = Util.getValue("비밀번호 ");
			String name = Util.getValue("이름 ");
			
			dao.joinMember(id,pw,name);
			System.out.println(" [ 회원 가입 완료 ] ");
			cont.setNext("MallMain");
			
			return false;
		}
		
		
		
		
	}

}
