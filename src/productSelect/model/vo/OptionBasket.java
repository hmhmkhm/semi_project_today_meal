package productSelect.model.vo;

public class OptionBasket {
	private String optionName;
	private int optionQty;
	private int optionPrice;
	private int optCalcPrice;
	
	public OptionBasket() {
		super();
	}
	
	public OptionBasket(String optionName, int optionPrice) {
		super();
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}

	public OptionBasket(int optCalcPrice, String optionName, int optionQty) {
		super();
		this.optionName = optionName;
		this.optionQty = optionQty;
		this.optCalcPrice = optCalcPrice;
	}

	public OptionBasket(String optionName, int optionQty, int optionPrice) {
		super();
		this.optionName = optionName;
		this.optionQty = optionQty;
		this.optionPrice = optionPrice;
	}
	
	public OptionBasket(String optionName, int optionQty, int optionPrice, int optCalcPrice) {
		super();
		this.optionName = optionName;
		this.optionQty = optionQty;
		this.optionPrice = optionPrice;
		this.optCalcPrice = optCalcPrice;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getOptionQty() {
		return optionQty;
	}

	public void setOptionQty(int optionQty) {
		this.optionQty = optionQty;
	}

	public int getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}

	public int getOptCalcPrice() {
		return optCalcPrice;
	}

	public void setOptCalcPrice(int optCalcPrice) {
		this.optCalcPrice = optCalcPrice;
	}

	@Override
	public String toString() {
		return "OptionBasket [optionName=" + optionName + ", optionQty=" + optionQty + ", optionPrice=" + optionPrice
				+ ", optCalcPrice=" + optCalcPrice + "]";
	}

	
	
}
