package dto;

public class Cart{
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
	
	public Cart() {}

	public String createToData() {
		return "%d/%s/%d/%d\n".formatted(cartNum,id,itemNum,itemCnt);
	}

	public static int getNum() {
		return num;
	}

	public void setNum(int num) {
		Cart.num = num;
	}

	public int getCartNum() {
		return cartNum;
	}

	public String getId() {
		return id;
	}

	public int getItemNum() {
		return itemNum;
	}

	public int getItemCnt() {
		return itemCnt;
	}

}
