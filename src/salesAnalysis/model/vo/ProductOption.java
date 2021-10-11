package salesAnalysis.model.vo;

public class ProductOption {
	
	private String optionName;
	private int optionQty;
	
	public ProductOption() {
		super();
	}
	
	public ProductOption(String optionName, int optionQty) {
		super();
		this.optionName = optionName;
		this.optionQty = optionQty;
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

	@Override
	public String toString() {
		return "ProductOption [optionName=" + optionName + ", optionQty=" + optionQty + "]";
	}
	
	
	
}
