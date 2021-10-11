package productBuy.model.vo;

public class Option {
	private int optionNo;
	private String optionName;
	private int optionPrice;
	private String status;
	private int optionTypeNo;
	private int quantity;
	
	public Option() {}

	

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOptionTypeNo() {
		return optionTypeNo;
	}

	public void setOptionTypeNo(int optionTypeNo) {
		this.optionTypeNo = optionTypeNo;
	}

	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "Option [optionNo=" + optionNo + ", optionName=" + optionName + ", optionPrice=" + optionPrice
				+ ", status=" + status + ", optionTypeNo=" + optionTypeNo + ", quantity=" + quantity + "]";
	}



}
