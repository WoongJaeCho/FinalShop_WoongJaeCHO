package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dto.Item;
import util.Util;

public class ItemDAO {
	
	private static ItemDAO instance = new ItemDAO();
	private static int cnt;
	
	public int getCnt() {
		return cnt;
	}
	public static ItemDAO getInstance() {
		return instance;
	}
	
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<String> categoryList = new ArrayList<String>();
	
	private int checkItemName(String itemName) {
		if(cnt==0) return -1;
		for(int k=0; k<cnt; k+=1) {
			if(itemList.get(k).getItemName().equals(itemName)) {
				return k;
			}
		}
		for(int k=0; k<cnt; k+=1) {
			if(itemList.get(k).getCategoryName().equals(itemName)) {
				return k;
			}
		}
		return -1;
	}
	private int checkItemNum(int itemNum) {
		for(int k=0;k<cnt;k+=1) {
			if(itemList.get(k).getItemNum()==itemNum) {
				return k;
			}
		}
		return -1;
	}
	private void printItemsList() {
		System.out.println("===== 카테고리별 아이템 목록 =====");
		ArrayList<Item> temp = new ArrayList<>();
		for(Item i : itemList) {
			temp.add(i);
		}
		// 카테고리별 내림차순으로 정리할 것.
		// 같은 카테고리 내에서 오름차순.
		Collections.sort(temp);
		for(Item t : temp) {
			System.out.println(t);
		}
	}
	
	public void printSaleList(CartDAO cartDAO) {
		System.out.println("===== 판매된 아이템 목록 =====");
		if(cnt==0) {
			System.out.println("판매된 아이템이 없습니다.");
			return;
		}
		Map<String, Integer> map = new HashMap<>();
		for(Item i : itemList) {
			int count = cartDAO.countSoldOneItem(i.getItemNum());
			if(count!=0) map.put(i.getItemName(), count);
		}
		
		List<String> keys = new ArrayList<>(map.keySet());
		
		Collections.sort(keys, (o1,o2) -> map.get(o2).compareTo(map.get(o1)));
		
		for(String key : keys) {
			for(Item i : itemList) {
				if(i.getItemName().equals(key)) {
					System.out.println(i+" "+map.get(key)+"개");
				}
			}
		}
		
		//countList.forEach((key, value) -> System.out.println(key+" "+value+"개"));
		// 판매 갯수 합 구해서 내림차순으로 출력할 것.
	}
	
	public String[] oneItemInfo(int itemNum) {
		String[] temp = new String[2];
		for(Item i : itemList) {
			if(i.getItemNum()==itemNum) {
				temp[0] = i.getItemName();
				temp[1] = i.getPrice()+"";
				return temp;
			}
		}
		return null;
	}
	
	public void printOneCategoryInItem(int sel) {
		System.out.println("0) 뒤로가기");
		System.out.printf("[ %s 의 아이템 목록 ]\n",categoryList.get(sel));
		int idx = 0;
		for(Item i : itemList) {
			if(i.getCategoryName().equals(categoryList.get(sel))) {
				System.out.printf("[%d] %s %d\n",++idx,i.getItemName(),i.getPrice());
			}
		}
	}
	
	public int selectOneItem(String itemName, int sel) {
		if(itemName.equals("0")) return -2;
		for(Item i : itemList) {
			if(i.getCategoryName().equals(categoryList.get(sel)) && 
					i.getItemName().equals(itemName)) {
				return i.getItemNum();
			}
		}
		return -1;
	}
	
	public void printCategory() {
		int idx=0;
		for(String ca : categoryList) {
			System.out.printf("[%d] %s\n",++idx,ca);
		}
	}
	
	public void addOneItem() {
		printItemsList();
		String itemName = Util.getValue("아이템 ");
		int idx = checkItemName(itemName);
		if(idx!=-1) {
			System.out.println("이미 있는 카테고리/아이템 이름 입니다");
			return;
		}
		String categoryName = Util.getValue("카테고리 ");
		int price = Util.getValue("가격", 100, 1000000);
		
		int itemNum = Item.getNum()+1;
		Item i = new Item(itemNum, categoryName, itemName, price);
		i.setNum(itemNum);
		itemList.add(i);
		cnt++;
		System.out.println(i);
		inputCategory(categoryName);
		System.out.println("아이템 추가 완료");
	}
	
	public void deleteOneItme(CartDAO cartDAO) {
		printItemsList();
		System.out.println("[ 아이템 삭제시 구매 내역이 사라집니다 ]");
		int itemNum = Util.getValue("삭제할 아이템 번호 ", 1, Item.getNum());
		int idx = checkItemNum(itemNum);
		if(idx==-1) {
			System.out.println("해당 번호의 아이템이 없습니다.");
			return;
		}
		cartDAO.deleteOneItem(itemNum);
		itemList.remove(idx);
		cnt--;
		System.out.println("아이템 삭제 완료");
	}
	
	public String saveToData() {
		String data="";
		for(Item i : itemList) {
			data+=i.createToData();
		}
		return data;
	}
	
	private void inputCategory(String category) {
		boolean check = true;
		for(String ca : categoryList) {
			if(ca.equals(category)) {
				check = false;
			}
		}
		if(check) {
			categoryList.add(category);
		}
	}
	
	public void loadToData(String data) {
		String[] temp = data.split("\n");
		cnt = temp.length;
		
		for(int k=0; k<cnt ;k+=1) {
			String[] info = temp[k].split("/");
			
			int itemNum = Integer.parseInt(info[0]);
			int price = Integer.parseInt(info[3]);
			
			Item i = new Item(itemNum, info[1], info[2], price);
			inputCategory(info[1]);
			itemList.add(i);
		}
	}
	
	public void updateMaxno() {
		if(cnt==0) return;
		int maxNo = 0;
		for(Item i : itemList) {
			if(maxNo < i.getItemNum()) {
				maxNo = i.getItemNum();
				i.setNum(maxNo);
			}
		}
	}
	

}
