package dao;

import java.util.ArrayList;

import dto.Item;

public class ItemDAO {
	
	private static ItemDAO instance = new ItemDAO();
	private static int cnt;
	
	public static ItemDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	
	
	public String saveToData() {
		String data="";
		for(Item i : itemList) {
			data+=i.createToData();
		}
		return data;
	}
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		//cnt = temp.length;
		
		for(int k=0; k<temp.length ;k+=1) {
			String[] info = temp[k].split("/");
			
			int itemNum = Integer.parseInt(info[0]);
			int price = Integer.parseInt(info[3]);
			
			Item i = new Item(itemNum, info[1], info[2], price);
			itemList.add(i);
		}
	}
	

}
