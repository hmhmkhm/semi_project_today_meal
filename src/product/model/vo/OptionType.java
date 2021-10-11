package product.model.vo;

import java.util.List;

public class OptionType {
	private int optionTypeNo;
	private List<Option> optionList;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((optionType == null) ? 0 : optionType.hashCode());
		result = prime * result + optionTypeNo;
		result = prime * result + pNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OptionType other = (OptionType) obj;
		if (optionType == null) {
			if (other.optionType != null)
				return false;
		} else if (!optionType.equals(other.optionType))
			return false;
		if (optionTypeNo != other.optionTypeNo)
			return false;
		if (pNo != other.pNo)
			return false;
		return true;
	}

	private String optionType;
	private int pNo;
	/* 	OPTION_TYPE_NO	NUMBER
		OPTION_TYPE	VARCHAR2(30 BYTE)
		PRODUCT_NO	NUMBER */
	
	public OptionType() {}
	
	public OptionType(int optionTypeNo, String optionType, int pNo) {
		super();
		this.optionTypeNo = optionTypeNo;
		this.optionType = optionType;
		this.pNo = pNo;
	}

	public int getOptionTypeNo() {
		return optionTypeNo;
	}

	public void setOptionTypeNo(int optionTypeNo) {
		this.optionTypeNo = optionTypeNo;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	
	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "OptionType [optionTypeNo=" + optionTypeNo + ", optionList=" + optionList + ", optionType=" + optionType
				+ ", pNo=" + pNo + "]";
	}

	
	
	
}
