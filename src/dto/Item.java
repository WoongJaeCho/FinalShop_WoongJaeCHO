package dto;

import java.util.Objects;
import java.lang.String;

public class Item implements Comparable<Item>{
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;
	
	public Item() {}
	
	public Item(int itemNum, String categoryName, String itemName, int price) {
		super();
		this.itemNum = itemNum;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}

	public String createToData() {
		return "%d/%s/%s/%d\n".formatted(itemNum,categoryName,itemName,price);
	}

	@Override
	public String toString() {
		return "[%2d] [%6s] [%6s] [%10d원]".formatted(itemNum,categoryName,itemName,price);
	}

	public String getItemName() {
		return itemName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public static int getNum() {
		return num;
	}

	public void setNum(int num) {
		Item.num = num;
	}

	public int getItemNum() {
		return itemNum;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryName, itemName, itemNum, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(categoryName, other.categoryName) && Objects.equals(itemName, other.itemName)
				&& itemNum == other.itemNum && price == other.price;
	}

	@Override
	public int compareTo(Item o) {
		if(categoryName.compareTo(o.categoryName)<0) {
			return 1;
		}else if(categoryName.compareTo(o.categoryName)>0) {
			return -1;
		}else {
			return itemName.compareTo(o.itemName);
		}
		//return 0;
	}
	
	
}
