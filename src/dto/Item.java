package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;
	
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
	
}
