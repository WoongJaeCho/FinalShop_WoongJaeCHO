package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private ArrayList<Member> memberList;
	
	public String isValidMember(String id, String pw) {
		
		
		return null;
	}

	public void loadToData(String data) {
		String[] temp = data.split("\n");
		//cnt = temp.length;
		
		for(int i=0; i<temp.length ;i+=1) {
			String[] info = temp[i].split("/");
			
			int memberNum = Integer.parseInt(info[0]);
			
			Member m = new Member(memberNum, info[1], info[2], info[3]);
			memberList.add(m);
		}
	}
	
	
}
