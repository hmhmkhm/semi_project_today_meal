package product.model.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Product {
	
	private int pNo;
	private String pName;
	private String pImg;
	private int pPrice;
	private String pDetail;
	private Date pDate;
	private int cNo;
	private String status;
	private String cName;
	private List<OptionType> deleteOtList = new ArrayList<>();
	private List<Option> deleteOList = new ArrayList<>();
	// private List<String> optionTypeList = new ArrayList<>();
	private List<OptionType> optionTypeList = new ArrayList<>();
	private List<Option> optionList = new ArrayList<>();
	/*
	 * private String[] opType; private String[] opName; private int[] opPrice;
	 */

	/* 	PRODUCT_NO	NUMBER
		PRODUCT_NAME	VARCHAR2(50 BYTE)
		PRODUCT_IMG	VARCHAR2(1500 BYTE)
		PRODUCT_PRICE	NUMBER
		PRODUCT_DETAIL	VARCHAR2(1500 BYTE)
		PRODUCT_DATE	DATE
		CATEGORY_NO	NUMBER
		STATUS	VARCHAR2(1 BYTE) */
	
	public Product() {}

	public Product(int pNo, String cName, String pName) {
		super();
		this.pNo = pNo;
		this.cName = cName;
		this.pName = pName;
	}
	

	public Product(int pNo, String pName, String pImg, int pPrice, String pDetail, int cNo) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
		this.cNo = cNo;
	}

	public Product(int pNo, String pName, String pImg, int pPrice, String pDetail, Date pDate, int cNo, String status) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.pDetail = pDetail;
		this.pDate = pDate;
		this.cNo = cNo;
		this.status = status;
	}
	
	public Product(int cNo, String pName, String pImg, int pPrice, List<OptionType> optionTypeList, String pDetail) {
		super();
		this.cNo = cNo;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.optionTypeList = optionTypeList;
		this.pDetail = pDetail;
	}
	
	public Product(int pNo, int cNo, String pName, String pImg, int pPrice, 
					List<OptionType> optionTypeList, String pDetail) {
		super();
		this.pNo = pNo;
		this.cNo = cNo;
		this.pName = pName;
		this.pImg = pImg;
		this.pPrice = pPrice;
		this.optionTypeList = optionTypeList;
		this.pDetail = pDetail;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpDetail() {
		return pDetail;
	}

	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public List<OptionType> getDeleteOtList() {
		return deleteOtList;
	}

	public void setDeleteOtList(List<OptionType> deleteOtList) {
		this.deleteOtList = deleteOtList;
	}

	public List<Option> getDeleteOList() {
		return deleteOList;
	}

	public void setDeleteOList(List<Option> deleteOList) {
		this.deleteOList = deleteOList;
	}

	public List<OptionType> getOptionTypeList() {
		return optionTypeList;
	}
	
	public void setOptionTypeList(List<OptionType> optionTypeList) {
		this.optionTypeList = optionTypeList;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pName=" + pName + ", pImg=" + pImg + ", pPrice=" + pPrice + ", pDetail="
				+ pDetail + ", pDate=" + pDate + ", cNo=" + cNo + ", status=" + status + ", cName=" + cName
				+ ", deleteOtList=" + deleteOtList + ", deleteOList=" + deleteOList + ", optionTypeList="
				+ optionTypeList + ", optionList=" + optionList + "]";
	}

}
