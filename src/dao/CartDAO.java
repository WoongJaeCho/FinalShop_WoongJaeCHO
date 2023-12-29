package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dto.Cart;
import dto.Item;
import util.Util;

public class CartDAO {
	
	private static CartDAO instance = new CartDAO();
	private static int cnt;
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<Cart> cartList = new ArrayList<Cart>();
	
	public void choiceShoppingList(ItemDAO itemDAO, String id) {
		int sel = Util.getValue("메뉴 ", 0, 5)-1;
		if(sel==-1) return;
		itemDAO.printOneCategoryInItem(sel);
		while(true) {
			String itemName = Util.getValue("구매 아이템 이름 ");
			int itemNum = itemDAO.selectOneItem(itemName, sel);
			if(itemNum ==-2) return;
			if(itemNum ==-1) {
				System.out.println("아이템 이름 오류. 다시 입력해 주세요");
				continue;
			}
			int itemCnt = Util.getValue("아이템 구매 수량", 1, 100);
			int cartNum = Cart.getNum()+1;
			Cart c = new Cart(cartNum, id, itemNum, itemCnt);
			c.setNum(cartNum);
			cartList.add(c);
			System.out.printf("[ %s %d개 구매 완료 ]\n",itemName,itemCnt);
			cnt++;
			return;
		}
	}
	
	public boolean printOneShoppingList(ItemDAO itemDAO,String id) {
		int idx = 0;
		int total = 0;
		int total_cnt =0;
		int sum = 0;
		int sum_cnt = 0;
		Set<Integer> itemNumList = new HashSet<>();
		for(Cart c : cartList) {
			if(c.getId().equals(id)) {
				itemNumList.add(c.getItemNum());
			}
		}
		if(itemNumList.size()==0) {
			System.out.println("구매 내역이 없습니다. 구매해주세요");
			System.out.println("=====================");
			return false;
		}
		for(Integer in : itemNumList) {
			
			for(Cart c : cartList) {
				if(c.getId().equals(id) && in==c.getItemNum()) {
					sum_cnt += c.getItemCnt();
				}
			}
			String[] temp = itemDAO.oneItemInfo(in);
			String itemName = temp[0];
			int price = Integer.parseInt(temp[1]);
			sum = price * sum_cnt;
			System.out.printf("[%2d]\t%-1s(\t%d원)\t%d개 총 %d원\n",++idx,itemName,price,sum_cnt,sum);
			total += sum;
			total_cnt += sum_cnt;
			sum=0;
			sum_cnt=0;
		}
		
		System.out.println("=====================");
		System.out.printf("총 %d 개 ( %d 원 )\n",total_cnt,total);
		return true;
	}
	
	public int countSoldOneItem(int itemNum) {
		int sum = 0;
		for(Cart c : cartList) {
			if(itemNum==c.getItemNum()) {
				sum+=c.getItemCnt();
			}
		}
		return sum;
	}
	
	public void deleteOneItem(int itemNum) {
		for(int i=0; i<cnt; i+=1) {
			if(cartList.get(i).getItemNum()==itemNum) {
				cartList.remove(i);
				i-=1;
				cnt-=1;
			}
		}
		System.out.println("구매 내역에서 아이템 삭제 완료");
	}
	
	public void deleteOneMemberCart(String id) {
		if(cnt==0) return;
		for(int i=0; i<cnt ; i+=1) {
			if(cartList.get(i).getId().equals(id)) {
				cartList.remove(i);
				i-=1;
				cnt-=1;
			}
		}
		System.out.println("회원 구매 내역 삭제 완료");
	}
	
	public String saveToData() {
		String data="";
		for(Cart c : cartList) {
			data+=c.createToData();
		}
		return data;
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int i=0; i<temp.length ;i+=1) {
			String[] info = temp[i].split("/");
			
			int cartNum = Integer.parseInt(info[0]);
			int itemNum = Integer.parseInt(info[2]);
			int itemCnt = Integer.parseInt(info[3]);
			
			Cart c = new Cart(cartNum, info[1], itemNum, itemCnt);
			cartList.add(c);
		}
	}
	
	public void updateMaxno() {
		if(cnt==0) return;
		int maxNo = 0;
		for(Cart c : cartList) {
			if(maxNo < c.getItemNum()) {
				maxNo = c.getItemNum();
				c.setNum(maxNo);
			}
		}
	}
	
}
