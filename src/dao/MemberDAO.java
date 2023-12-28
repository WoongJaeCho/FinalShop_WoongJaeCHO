package dao;

import java.util.ArrayList;

import dto.Member;
import util.Util;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	private static int cnt;
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<Member> memberList = new ArrayList<Member>();
	
	public void printOneMemberInfo(String id) {
		for(Member m : memberList) {
			if(m.getId().equals(id)) {
				System.out.println(m);
			}
		}
	}
	
	public void modifyPw(String id) {
		String pw = Util.getValue("비밀번호 ");
		
		String newPw = Util.getValue("신규 비밀번호 ");
		if(pw.equals(newPw)) {
			System.out.println("다른 비밀번호를 입력 하세요");
			return;
		}
		boolean check = false;
		int idx = -1;
		for(int i=0; i<cnt; i+=1) {
			if(memberList.get(i).getId().equals(id) && 
					memberList.get(i).getPw().equals(pw)) {
				check = true;
				idx = i;
			}
		}
		if(check) {
			memberList.set(idx, new Member(memberList.get(idx).getMemberNum(), 
					id, newPw, memberList.get(idx).getMemberName()));
			System.out.println("비밀번호 변경 완료");
		} else {
			System.out.println("비밀번호가 일치하지 않음");
		}
	}
	
	public String isValidMember(String id, String pw) {
		for(Member m : memberList) {
			if(m.getId().equals(id) && m.getPw().equals(pw)) {
				return id;
			}
		}
		return null;
	}
	
	public void joinMember(String id,String pw,String name) {
		int itemNum = Member.getNum()+1;
		Member m = new Member(itemNum,id,pw,name);
		m.setNum(itemNum+1);
		memberList.add(m);
		System.out.print(m);
		System.out.println(" 추가 완료");
		cnt++;
	}
	
	public int checkId(String id) {
		for(int i=0; i<cnt; i+=1) {
			if(memberList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	public void printMember() {
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
	
	public boolean deleteMember(CartDAO cartDAO, String id) {
		System.out.println("회원 삭제시 구매 내역이 사라집니다");
		String delId = null;
		if(!id.equals("admin")) {
			delId = id;
		} else {
			delId = Util.getValue("삭제할 회원 아이디 ");
			if(delId.equals("admin")) {
				System.out.println("관리자 삭제 불가능");
				return false;
			}
		}
		int idx = checkId(delId);
		if(idx==-1) {
			System.out.println("해당 아이디 존재하지 않음");
			return false;
		}
		cartDAO.deleteOneMemberCart(delId);
		memberList.remove(idx);
		return true;
	}
	
	public String saveToData() {
		String data="";
		for(Member m : memberList) {
			data+=m.createToData();
		}
		return data;
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int i=0; i<cnt ;i+=1) {
			String[] info = temp[i].split("/");
			
			int memberNum = Integer.parseInt(info[0]);
			
			Member m = new Member(memberNum, info[1], info[2], info[3]);
			memberList.add(m);
		}
	}
	
	public void updateMaxNo() {
		if(cnt==0) return;
		int maxNo = 0;
		for(Member m : memberList) {
			if(maxNo < m.getMemberNum()) {
				maxNo = m.getMemberNum();
				m.setNum(maxNo);
			}
		}
	}
	
}
