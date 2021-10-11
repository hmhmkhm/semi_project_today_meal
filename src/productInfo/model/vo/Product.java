package productInfo.model.vo;

import java.util.Date;
import java.util.List;

import review.model.vo.Review;

public class Product {
	private int productNo;
	private String productName;
	private String productImg;
	private int productPrice;
	private String productDetail;
	private Date productDate;
	private int categoryNo;
	private String status;
	private String reviewText;
	private String reviewImage;
	private Date reviewRegister;
	private List<Review> reviewList;
	
	public Product() {}

	
	
	
	public Product(int productNo, String productName, String productImg, int productPrice, String productDetail,
			Date productDate, int categoryNo, String status, String reviewText, String reviewImage,
			Date reviewRegister) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productDate = productDate;
		this.categoryNo = categoryNo;
		this.status = status;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
	}




	public Product(int productNo, String productName, String productImg, int productPrice, String productDetail,
			Date productDate, int categoryNo, String status) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productDate = productDate;
		this.categoryNo = categoryNo;
		this.status = status;
	}




	public Product(int productNo, String productName, String productImg, int productPrice, String productDetail,
			Date productDate, int categoryNo, String status, List<Review> reviewList) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productImg = productImg;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productDate = productDate;
		this.categoryNo = categoryNo;
		this.status = status;
		this.reviewList = reviewList;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
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

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}
	
	

	public String getReviewText() {
		return reviewText;
	}




	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}




	public String getReviewImage() {
		return reviewImage;
	}




	public void setReviewImage(String reviewImage) {
		this.reviewImage = reviewImage;
	}




	public Date getReviewRegister() {
		return reviewRegister;
	}




	public void setReviewRegister(Date reviewRegister) {
		this.reviewRegister = reviewRegister;
	}




	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productImg=" + productImg
				+ ", productPrice=" + productPrice + ", productDetail=" + productDetail + ", productDate=" + productDate
				+ ", categoryNo=" + categoryNo + ", status=" + status + ", reviewText=" + reviewText + ", reviewImage="
				+ reviewImage + ", reviewRegister=" + reviewRegister + ", reviewList=" + reviewList + "]";
	}




	
	
}