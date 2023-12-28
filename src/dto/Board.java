package dto;

public class Board {
	private static int num;
	private int boradNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	
	public Board(int boradNum, String title, String contents, String id, String date, int hits) {
		super();
		this.boradNum = boradNum;
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "[ 제목 : %s\t작성자 : %-7s날짜 : %s\t조회수 : %2d ]".formatted(title,id,date,hits);
	}

	public String createToData() {
		return "%d/%s/%s/%s/%s/%d\n".formatted(boradNum,title,contents,id,date,hits);
	}

	public String getId() {
		return id;
	}

	public static int getNum() {
		return num;
	}

	public void setNum(int num) {
		Board.num = num;
	}

	public int getBoradNum() {
		return boradNum;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getContents() {
		return contents;
	}
	
	
}
