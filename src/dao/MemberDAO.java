package dao;

import java.util.ArrayList;

import dto.Member;

public class MemberDAO {
	
	private int cnt;
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private ArrayList<Member> memberList = new ArrayList<Member>();
	
	public String isValidMember(String id, String pw) {
		
		
		return null;
	}
	
	public void joinMember(String id,String pw,String name) {
		Member.setNum(Member.getNum()+1);
		Member m = new Member(Member.getNum(),id,pw,name);
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
		//cnt = temp.length;
		
		for(int i=0; i<temp.length ;i+=1) {
			String[] info = temp[i].split("/");
			
			int memberNum = Integer.parseInt(info[0]);
			
			Member m = new Member(memberNum, info[1], info[2], info[3]);
			memberList.add(m);
		}
	}
	
	
}
