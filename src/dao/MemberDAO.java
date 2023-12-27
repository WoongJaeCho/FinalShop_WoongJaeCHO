package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	private static int cnt;
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<Member> memberList = new ArrayList<Member>();
	
	public String isValidMember(String id, String pw) {
		for(Member m : memberList) {
			if(m.getId().equals(id) && m.getPw().equals(pw)) {
				return id;
			}
		}
		return null;
	}
	
	public void joinMember(String id,String pw,String name) {
		Member mm = new Member();
		mm.setNum(mm.getNum()+1);
		Member m = new Member(mm.getNum(),id,pw,name);
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
	
	public static void updateMaxNo() {
		if(cnt==0) return;
		int maxNo = 0;
		for(Member m : memberList) {
			if(maxNo < m.getNum()) {
				maxNo = m.getNum();
				m.setNum(maxNo);
			}
		}
	}
	
}
