package salesAnalysis.model.vo;

import java.util.Date;

public class SalesData {
	private String salesDate;
	private String saleDay;
	private String countSales;
	private String totalSales;
	private String actualSales;
	private String cancelSales;
	private String returnSales;
	
	public SalesData() {
		super();
	}

	
	
	public SalesData(String salesDate, String countSales, String totalSales, String actualSales, String cancelSales,
			String returnSales) {
		super();
		this.salesDate = salesDate;
		this.countSales = countSales;
		this.totalSales = totalSales;
		this.actualSales = actualSales;
		this.cancelSales = cancelSales;
		this.returnSales = returnSales;
	}



	public SalesData(String salesDate, String saleDay, String countSales, String totalSales, String actualSales,
			String cancelSales, String returnSales) {
		super();
		this.salesDate = salesDate;
		this.saleDay = saleDay;
		this.countSales = countSales;
		this.totalSales = totalSales;
		this.actualSales = actualSales;
		this.cancelSales = cancelSales;
		this.returnSales = returnSales;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public String getSaleDay() {
		return saleDay;
	}

	public void setSaleDay(String saleDay) {
		this.saleDay = saleDay;
	}

	public String getCountSales() {
		return countSales;
	}

	public void setCountSales(String countSales) {
		this.countSales = countSales;
	}

	public String getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(String totalSales) {
		this.totalSales = totalSales;
	}

	public String getActualSales() {
		return actualSales;
	}

	public void setActualSales(String actualSales) {
		this.actualSales = actualSales;
	}

	public String getCancelSales() {
		return cancelSales;
	}

	public void setCancelSales(String cancelSales) {
		this.cancelSales = cancelSales;
	}

	public String getReturnSales() {
		return returnSales;
	}

	public void setReturnSales(String returnSales) {
		this.returnSales = returnSales;
	}

	@Override
	public String toString() {
		return "SalesData [salesDate=" + salesDate + ", saleDay=" + saleDay + ", countSales=" + countSales
				+ ", totalSales=" + totalSales + ", actualSales=" + actualSales + ", cancelSales=" + cancelSales
				+ ", returnSales=" + returnSales + "]";
	}
	
	
	
	
	
}
