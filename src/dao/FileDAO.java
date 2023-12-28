package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	private String txtPath = "src/files";
	private Charset charSet = StandardCharsets.UTF_8;
	//private static final String CUR_PATH = System.getProperty("user.dir")+"/src/files/";
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
	
//	private void saveToFileData(FileName name, String data) {
//		try(FileWriter fw = new FileWriter(CUR_PATH+name.getName())) {
//			fw.write(data);
//			System.out.println(name.getName()+" 파일 저장 성공");
//		} catch (Exception e) {
//			System.out.println(name.getName()+" 파일 저장 실패");
//		}
//	}
	
	private void saveToFileData(FileName name, String data) {
		Path path = Paths.get("src/files/"+name.getName());
		try(FileOutputStream fos = new FileOutputStream(path.toString());
				OutputStreamWriter ow = new OutputStreamWriter(fos, charSet);
				BufferedWriter bw = new BufferedWriter(ow);
				) {
			bw.write(data);
		} catch (Exception e) {
			
		}
	}
	
	public void saveToFile() { // 파일 저장 main
		String boardData = boardDAO.saveToData();
		String memberData = memberDAO.saveToData();
		String itemData = itemDAO.saveToData();
		String cartData = cartDAO.saveToData();
		
		saveToFileData(FileName.BOARD, boardData);
		saveToFileData(FileName.MEMBER, memberData);
		saveToFileData(FileName.ITEM, itemData);
		saveToFileData(FileName.CART, cartData);
	}
	
//	private String loadToFileData(FileName name) { // 파일 로드 sub
//		try(FileReader fr = new FileReader(CUR_PATH+name.getName());
//				BufferedReader br = new BufferedReader(fr);) {
//			String data = "";
//			while(true) {
//				int read = br.read();
//				if(read == -1) break;
//				
//				data += (char)read;
//			}
//			System.out.println(name.getName()+" 파일 로드 성공");
//			return data;
//		} catch (Exception e) {
//			System.out.println(name.getName()+" 파일 로드 실패");
//			return null;
//		}
//	}
	
	private String loadToFileData(FileName name) {
		Path path = Paths.get(txtPath, name.getName());
		StringBuilder data = new StringBuilder();
		
		try(FileInputStream fis = new FileInputStream(path.toString());
			InputStreamReader ir = new InputStreamReader(fis, charSet);
			BufferedReader br = new BufferedReader(ir);
				) {
			String line;
			while((line = br.readLine()) != null) {
				data.append(line);
				data.append("\n");
			}
			
		} catch (Exception e) {
			
		}
		
		return data.toString().substring(0, data.toString().length()-1);
	}
	
	public void loadToFile() { // 파일 로드 main
		init();
		String boardData = loadToFileData(FileName.BOARD);
		String memberData = loadToFileData(FileName.MEMBER);
		String itemData = loadToFileData(FileName.ITEM);
		String cartData = loadToFileData(FileName.CART);
		
		boardDAO.loadToData(boardData);
		cartDAO.loadToData(cartData);
		itemDAO.loadToData(itemData);
		memberDAO.loadToData(memberData);
		
		memberDAO.updateMaxNo(); //boradNum max값 구하기
		boardDAO.updateMaxNo();  //memberNum max값 구하기
		itemDAO.updateMaxno();   //itemNum max값 구하기
		System.out.println("== 데이터 로드 완료 ==");
	}
	

}
