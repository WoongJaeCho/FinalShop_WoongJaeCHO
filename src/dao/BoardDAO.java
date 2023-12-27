package dao;

import java.util.ArrayList;

import dto.Board;
import dto.Member;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	private static int cnt;
	
	public static BoardDAO getInstance() {
		return instance;
	}
	private static ArrayList<Board> boardList = new ArrayList<Board>();
	
	public String saveToData() {
		String data="";
		for(Board b : boardList) {
			data+=b.createToData();
		}
		return data;
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		//cnt = temp.length;
		
		for(int i=0; i<temp.length ;i+=1) {
			String[] info = temp[i].split("/");
			
			int boradNum = Integer.parseInt(info[0]);
			int hits = Integer.parseInt(info[5]);
			
			Board b = new Board(boradNum, info[1], info[2], info[3], info[4], hits);
			boardList.add(b);
		}
	}
	
	public void updateMaxNo() {
		if(cnt==0) return;
		int maxNo = 0;
		for(Board b : boardList) {
			if(maxNo < b.getNum()) {
				maxNo = b.getNum();
				b.setNum(maxNo);
			}
		}
	}
}
