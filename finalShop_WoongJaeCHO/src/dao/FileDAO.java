package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	private static final String CUR_PATH = System.getProperty("user.dir")+"/src/files/";
	BoardDAO boardDAO;
	CartDAO cartDAO;
	ItemDAO itemDAO;
	MemberDAO memberDAO;
	
	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		
		FileName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	
	}

	private FileDAO() {}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			//System.out.println("파일이 이미 있음");
		}
	}
	
	
	
	private void init() {

		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);
		
		boardDAO = new BoardDAO();
		cartDAO = new CartDAO();
		itemDAO = new ItemDAO();
		memberDAO = new MemberDAO();
		
	}
	
	private void saveToFileData(FileName name, String data) {
		try(FileWriter fw = new FileWriter(CUR_PATH+name)) {
			fw.write(data);
			System.out.println(name+" 파일 저장 성공");
		} catch (Exception e) {
			System.out.println(name+" 파일 저장 실패");
		}
	}
	
	public void saveToFile() { // 파일 저장 main
		String boardData = null;
		String memberData = null;
		String itemData = null;
		String cartData = null;
		
		saveToFileData(FileName.BOARD, boardData);
		saveToFileData(FileName.MEMBER, memberData);
		saveToFileData(FileName.ITEM, itemData);
		saveToFileData(FileName.CART, cartData);
	}
	
	private String loadToFileData(FileName name) { // 파일 로드 sub
		try(FileReader fr = new FileReader(CUR_PATH+name);
				BufferedReader br = new BufferedReader(fr);) {
			String data = "";
			while(true) {
				int read = br.read();
				if(read == -1) break;
				
				data += (char)read;
			}
			System.out.println(name+" 파일 로드 성공");
			return data;
		} catch (Exception e) {
			System.out.println(name+" 파일 로드 성공");
			return null;
		}
	}
	
	public void loadToFile() { // 파일 로드 main
		String boardData = loadToFileData(FileName.BOARD);
		String memberData = loadToFileData(FileName.MEMBER);
		String itemData = loadToFileData(FileName.ITEM);
		String cartData = loadToFileData(FileName.CART);
		
		boardDAO.loadToData(boardData);
		cartDAO.loadToData(cartData);
		itemDAO.loadToData(itemData);
		memberDAO.loadToData(memberData);
	}
	

}
