package dao;

import java.util.Date;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dto.Board;
import dto.Member;
import util.Util;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	private static int cnt;
	
	public static BoardDAO getInstance() {
		return instance;
	}

	private static ArrayList<Board> boardList = new ArrayList<Board>();
	
	public void deleteBoard() {
		int sel = Util.getValue("삭제할 게시글 번호 ", 1, cnt)-1;
		boardList.remove(sel);
		cnt-=1;
		System.out.println("게시글 삭제 완료");
	}
	
	private int checkIdx(String id) {
		int sel = Util.getValue("삭제할 게시글 번호 ", 1, Board.getNum());
		for(int i=0; i<cnt; i+=1) {
			if(boardList.get(i).getId().equals(id)) {
				if(i==sel) {
					return i-1;
				}
			}
		}
		return -1;
	}
	
	public void printOneMemberBoard(String id) {
		int count = 0;
		for(Board b : boardList) {
			if(b.getId().equals(id)) {
				System.out.printf("[%2d]",b.getBoradNum());
				System.out.println(b);
				System.out.println(b.getContents());
				count+=1;
			}
		}
		if(count==0) {
			System.out.println("본인의 게시글이 없습니다.");
			return;
		}
		System.out.println("[1] 삭제\n[0] 뒤로가기");
		int sel = Util.getValue("메뉴 ", 0, 2);
		if(sel==0) return;
		else if(sel==1) {
			int idx = checkIdx(id);
			if(idx==-1) {
				System.out.println("본인의 게시글만 삭제할 수 있습니다");
				return;
			}
			boardList.remove(idx);
			cnt--;
			System.out.println("게시글 삭제 완료");
		}
	}
	
	public void addBoard(String id) {
		
		System.out.println("[ 게시글 추가하기 ]");
		String title = Util.getValue("게시글 제목 ");
		String contents = Util.getValue("게시글 내용 ");
		int boardNum = Board.getNum()+1 ;
		Date date = new Date();
		String today =
		date.toInstant().atOffset(ZoneOffset.UTC)
						.format(DateTimeFormatter
						.ofPattern("yyyy-MM-dd"));
		Board b = new Board(boardNum, title, contents, id, today, 0);
		b.setNum(boardNum+1);
		boardList.add(b);
		cnt++;
		System.out.print("["+boardNum+"]");
		System.out.println(b);
	}
	
	public void printBoard() {
		if(cnt==0) {
			System.out.println("현재 게시글이 없습니다.");
			return;
		}
		int pageSize = 5;
		int start = 0;
		int curPage = 1;
		while(true) {
			int pageCount = cnt/pageSize +1;
			int end = start+pageSize < cnt? start+pageSize : cnt;
			System.out.println("===== 전체 게시글 목록 =====");
			System.out.printf("총 게시글 %d 개\n",cnt);
			System.out.printf("현재 페이지 [%d / %d]\n",curPage,pageCount);
			for(int i=start;i<end;i+=1) {
				System.out.printf("(%3d)", i+1 );
				System.out.println(boardList.get(i));
			}
			System.out.println("[1] 이전\n[2] 이후\n[3] 게시글보기\n[0] 뒤로가기");
			int sel = Util.getValue("메뉴 ", 0, 3);
			if(sel==0) {
				return;
			} else if(sel==1) {
				if(start>=5) {
					start -= 5;
					curPage -= 1;
				}
			} else if(sel==2) {
				if(cnt>start+5) {
					curPage += 1;
					start += 5;
				}
			} else if(sel==3) {
				sel = Util.getValue("게시글 번호 입력 ", start+1, end)-1;
				System.out.printf("\n[%2d]",sel+1);
				boardList.get(sel).setHits(boardList.get(sel).getHits()+1);
				System.out.println(boardList.get(sel));
				System.out.println("-------------------------------------------------------------------");
				System.out.println("\t"+boardList.get(sel).getContents()+"\n");
				return;
			}
		}
	}
	
	
	public String saveToData() {
		String data="";
		for(Board b : boardList) {
			data+=b.createToData();
		}
		return data;
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int i=0; i<cnt ;i+=1) {
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
			if(maxNo < b.getBoradNum()) {
				maxNo = b.getBoradNum();
				b.setNum(maxNo);
			}
		}
	}
}
