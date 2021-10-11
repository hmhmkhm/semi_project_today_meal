package saleManagement.model.vo;

public class Option {
	private String name;
	private int buyQuantity;
	private int price;
	
	public Option() {}
	
	public Option(String name, int buyQuantity, int price) {
		super();
		this.name = name;
		this.buyQuantity = buyQuantity;
		this.price = price;
	}

	public Option(String name, int buyQuantity) {
		super();
		this.name = name;
		this.buyQuantity = buyQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Option [name=" + name + ", buyQuantity=" + buyQuantity + ", price=" + price + "]";
	}

}
