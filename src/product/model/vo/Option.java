package product.model.vo;

public class Option {
	private int optionNo;
	private String optionName;
	private int optionPrice;
	private int optionTypeNo;
	/* 	OPTION_NO	NUMBER
		OPTION_NAME	VARCHAR2(30 BYTE)
		OPTION_PRICE	NUMBER
		STATUS	VARCHAR2(1 BYTE)
		OPTION_TYPE_NO	NUMBER */

	public Option() {}
	
	public Option(int optionNo, String optionName, int optionPrice, int optionTypeNo) {
		super();
		this.optionNo = optionNo;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.optionTypeNo = optionTypeNo;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}
	
	public int getOptionTypeNo() {
		return optionTypeNo;
	}
	public void setOptionTypeNo(int optionTypeNo) {
		this.optionTypeNo = optionTypeNo;
	}
	@Override
	public String toString() {
		return "Option [optionNo=" + optionNo + ", optionName=" + optionName + ", optionPrice=" + optionPrice
				+ ", optionTypeNo=" + optionTypeNo + "]";
	}
	
}
