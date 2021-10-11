package productSelect.model.vo;

import java.util.List;

public class OrderBasket {
	private String productName;
	private String productImg;
	private int productPrice;
	private int productQty;
	private int total;
	private List<OptionBasket> optionBasketList;
	
	private String delivery;
	
	public OrderBasket() {
		super();
	}
	
	public OrderBasket(String productName, String productImg, int productPrice) {
		super();
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
	}
	
	
	public OrderBasket(String productName, String productImg, int productPrice, List<OptionBasket> optionBasketList) {
		super();
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.optionBasketList = optionBasketList;
	}
	
	
	public OrderBasket(String productName, String productImg, int productPrice, int productQty, int total,
			String delivery) {
		super();
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.total = total;
		this.delivery = delivery;
	}

	public OrderBasket(String productName, String productImg, int productPrice, int productQty, int total,
			List<OptionBasket> optionBasketList, String delivery) {
		super();
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.total = total;
		this.optionBasketList = optionBasketList;
		this.delivery = delivery;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<OptionBasket> getOptionBasketList() {
		return optionBasketList;
	}

	public void setOptionBasketList(List<OptionBasket> optionBasketList) {
		this.optionBasketList = optionBasketList;
	}
	
	
	
	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "OrderBasket [productName=" + productName + ", productImg=" + productImg + ", productPrice="
				+ productPrice + ", productQty=" + productQty + ", total=" + total + ", optionBasketList="
				+ optionBasketList + ", delivery=" + delivery + "]";
	}

	
	
	
}
