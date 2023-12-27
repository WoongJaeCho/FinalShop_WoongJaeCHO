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

	public String createToData() {
		return "%d/%s/%s/%s/%s/%d\n".formatted(boradNum,title,contents,id,date,hits);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		Board.num = num;
	}
	
	
}
