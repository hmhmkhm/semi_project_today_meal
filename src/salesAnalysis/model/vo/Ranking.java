package salesAnalysis.model.vo;

public class Ranking {
	private String category;
	private String productName;
	private String productImg;
	private int salesQuantity;
	private int total;
	
	public Ranking() {
		super();
	}

	public Ranking(String category, String productName, String productImg, int salesQuantity, int total) {
		super();
		this.category = category;
		this.productName = productName;
		this.productImg = productImg;
		this.salesQuantity = salesQuantity;
		this.total = total;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Ranking [category=" + category + ", productName=" + productName + ", productImg=" + productImg
				+ ", salesQuantity=" + salesQuantity + ", total=" + total + "]";
	}
	
	
}
