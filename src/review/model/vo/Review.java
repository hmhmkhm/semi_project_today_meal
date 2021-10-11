package review.model.vo;

import java.util.Date;
import java.util.List;

import productInfo.model.vo.Product;

public class Review {
	private int reviewNo;
	private double point;
	private String reviewText;
	private String reviewImage;
	private Date reviewRegister;
	private int userNo;				
	private int productNo;
	private int orderNo;
	private String status;
	private String userName;
	private String productName;
	private List<Product> productList;
	
	/*
	 * review_no	number		NOT NULL,
	point	number(2,1)	DEFAULT 0	NOT NULL,
	review_text	varchar2(1500)		NOT NULL,
	review_image	varchar2(1500)		NULL,
	review_register	date	DEFAULT SYSDATE	NOT NULL,
	user_id	varchar2(12)		NOT NULL,
	product_no	number		NOT NULL,
	order_no	number		NOT NULL,
	status	varchar2(1)	DEFAULT 'Y'	NOT NULL
	 * */
	
	public Review() {}



	public Review(int reviewNo, double point, String reviewText, String reviewImage, Date reviewRegister, int userNo,
			int productNo, int orderNo, String status, String userName, List<Product> productList) {
		super();
		this.reviewNo = reviewNo;
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
		this.userNo = userNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.status = status;
		this.userName = userName;
		this.productList = productList;
	}



	public Review(int reviewNo, double point, String reviewText, String reviewImage, Date reviewRegister, int userNo,
			int productNo, int orderNo, String status, String userName) {
		super();
		this.reviewNo = reviewNo;
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
		this.userNo = userNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.status = status;
		this.userName = userName;
	}



	public Review(double point, String reviewText, String reviewImage, int userNo, int orderNo) {
		super();
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.userNo = userNo;
		this.orderNo = orderNo;
	}

	public Review(double point, String reviewText, String reviewImage, int userNo, int productNo, int orderNo) {
		super();
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.userNo = userNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
	}



	public Review(int reviewNo, double point, String reviewText, String reviewImage, Date reviewRegister, int userNo,
			int productNo, String status) {
		super();
		this.reviewNo = reviewNo;
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
		this.userNo = userNo;
		this.productNo = productNo;
		this.status = status;
	}



	public Review(int reviewNo, String reviewText, String reviewImage) {
		super();
		this.reviewNo = reviewNo;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
	}



//	public Review(String reviewText, String reviewImage) {
//		super();
//		this.reviewText = reviewText;
//		this.reviewImage = reviewImage;
//	}



	public Review(int reviewNo, double point, String reviewText, String reviewImage, Date reviewRegister, int userNo,
			int orderNo, String status, String productName) {
		super();
		this.reviewNo = reviewNo;
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
		this.userNo = userNo;
		this.orderNo = orderNo;
		this.status = status;
		this.productName = productName;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public Review(double point, String reviewText, String reviewImage) {
		super();
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
	}


	public Review(double point, String reviewText, String reviewImage, int userNo) {
		super();
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.userNo = userNo;
	}

	public Review(int reviewNo, double point, String reviewText, String reviewImage, Date reviewRegister, int userNo,
			int productNo, int orderNo, String status) {
		super();
		this.reviewNo = reviewNo;
		this.point = point;
		this.reviewText = reviewText;
		this.reviewImage = reviewImage;
		this.reviewRegister = reviewRegister;
		this.userNo = userNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.status = status;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
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

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public List<Product> getProductList() {
		return productList;
	}



	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}



	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", point=" + point + ", reviewText=" + reviewText + ", reviewImage="
				+ reviewImage + ", reviewRegister=" + reviewRegister + ", userNo=" + userNo + ", productNo=" + productNo
				+ ", orderNo=" + orderNo + ", status=" + status + ", userName=" + userName + ", productName="
				+ productName + ", productList=" + productList + "]";
	}




	
	
}
