package dao;

import java.util.ArrayList;

import dto.Cart;

public class CartDAO {
	private ArrayList<Cart> cartList = new ArrayList<Cart>();
	
	
	public String saveToData() {
		String data="";
		for(Cart c : cartList) {
			data+=c.createToData();
		}
		return data;
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		//cnt = temp.length;
		
		for(int i=0; i<temp.length ;i+=1) {
			String[] info = temp[i].split("/");
			
			int cartNum = Integer.parseInt(info[0]);
			int itemNum = Integer.parseInt(info[2]);
			int itemCnt = Integer.parseInt(info[3]);
			
			Cart c = new Cart(cartNum, info[1], itemNum, itemCnt);
			cartList.add(c);
		}
	}
}
