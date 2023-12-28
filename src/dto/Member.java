package dto;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;
	
	public Member() {}
	
	public Member(int memberNum, String id, String pw, String memberName) {
		super();
		this.memberNum = memberNum;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
	}

	public String createToData() {
		return "%d/%s/%s/%s\n".formatted(memberNum,id,pw,memberName);
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public static int getNum() {
		return num;
	}

	public void setNum(int num) {
		Member.num = num;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberName() {
		return memberName;
	}

	@Override
	public String toString() {
		return "[%4d] [%10s] [%10s] [%10s]".formatted(memberNum,id,pw,memberName);
	}
	
	
	
}
