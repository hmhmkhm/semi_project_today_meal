package salesAnalysis.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Receipt {
	private int orderCount;
	private int orderNo;
	private Date saleDate;
	private int coin;
	private Date deliveryDate;
	private int orderSum;
	private int userNo;
	private int productNo;
	private int orderStateNo;

	private List<ProductDetail> productList;

	public Receipt() {
		super();
	}

	
	
	
	public Receipt(int orderCount, int orderNo, Date saleDate, int orderSum, int productNo, int orderStateNo, List<ProductDetail> productList) {
		super();
		this.orderCount = orderCount;
		this.orderNo = orderNo;
		this.saleDate = saleDate;
		this.coin = coin;
		this.deliveryDate = deliveryDate;
		this.orderSum = orderSum;
		this.userNo = userNo;
		this.productNo = productNo;
		this.orderStateNo = orderStateNo;
		this.productList = productList;
	}




	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(int orderSum) {
		this.orderSum = orderSum;
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

	public int getOrderStateNo() {
		return orderStateNo;
	}

	public void setOrderStateNo(int orderStateNo) {
		this.orderStateNo = orderStateNo;
	}

	public List<ProductDetail> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDetail> productList) {
		this.productList = productList;
	}


	@Override
	public String toString() {
		return "Receipt [orderCount=" + orderCount + ", orderNo=" + orderNo + ", saleDate=" + saleDate + ", coin="
				+ coin + ", deliveryDate=" + deliveryDate + ", orderSum=" + orderSum + ", userNo=" + userNo
				+ ", productNo=" + productNo + ", orderStateNo=" + orderStateNo + ", productList=" + productList + "]";
	}

	

	
}
