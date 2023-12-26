package dto;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;
	
	public Cart(int cartNum, String id, int itemNum, int itemCnt) {
		super();
		this.cartNum = cartNum;
		this.id = id;
		this.itemNum = itemNum;
		this.itemCnt = itemCnt;
	}
	


}
