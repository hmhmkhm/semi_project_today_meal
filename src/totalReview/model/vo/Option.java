package totalReview.model.vo;

public class Option {
	private String name;
	private int buyQuantity;
	
	public Option() {}

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

	@Override
	public String toString() {
		return "Option [name=" + name + ", buyQuantity=" + buyQuantity + "]";
	}

}
