package dao;

import java.util.ArrayList;

import dto.Item;

public class ItemDAO {
	private ArrayList<Item> itemList;
	

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
