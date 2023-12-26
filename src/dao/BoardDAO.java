package dao;

import java.util.ArrayList;

import dto.Board;

public class BoardDAO {
	private ArrayList<Board> boardList = new ArrayList<Board>();
	
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
}
