package salesAnalysis.model.vo;

import java.util.List;

public class ProductDetail {
	
	private String catagoryName;
	private int pNo;
	private String productName;
	private String productImg;
	private int productQty;
	private List<ProductOption> optionList;

	public ProductDetail() {
		super();
	}
	
	
	public ProductDetail(String catagoryName, int pNo, String productName, String productImg, int productQty,
			List<ProductOption> optionList) {
		super();
		this.catagoryName = catagoryName;
		this.pNo = pNo;
		this.productName = productName;
		this.productImg = productImg;
		this.productQty = productQty;
		this.optionList = optionList;
	}
	
	

	public ProductDetail(String catagoryName, int pNo, String productName, String productImg, int productQty) {
		super();
		this.catagoryName = catagoryName;
		this.pNo = pNo;
		this.productName = productName;
		this.productImg = productImg;
		this.productQty = productQty;
	}


	public ProductDetail(String catagoryName, String productName, String productImg, int productQty) {
		super();
		this.catagoryName = catagoryName;
		this.productName = productName;
		this.productImg = productImg;
		this.productQty = productQty;
	}


	public ProductDetail(String catagoryName, String productName, String productImg, int productQty,
			List<ProductOption> optionList) {
		super();
		this.catagoryName = catagoryName;
		this.productName = productName;
		this.productImg = productImg;
		this.productQty = productQty;
		this.optionList = optionList;
	}
	
	
	
	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}
	
	public int getpNo() {
		return pNo;
	}


	public void setpNo(int pNo) {
		this.pNo = pNo;
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

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public List<ProductOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<ProductOption> optionList) {
		this.optionList = optionList;
	}


	@Override
	public String toString() {
		return "ProductDetail [catagoryName=" + catagoryName + ", pNo=" + pNo + ", productName=" + productName
				+ ", productImg=" + productImg + ", productQty=" + productQty + ", optionList=" + optionList + "]";
	}


	
	
	
}
