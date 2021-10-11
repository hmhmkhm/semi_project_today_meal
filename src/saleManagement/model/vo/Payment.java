package saleManagement.model.vo;

import java.util.Date;

public class Payment {
	private String impUid;
	private String payMehtod;
	private int payment;
	private Date paidAt;
	private String status;
	
	public Payment() {}

	public Payment(String impUid, String payMehtod, int payment, Date paidAt, String status) {
		super();
		this.impUid = impUid;
		this.payMehtod = payMehtod;
		this.payment = payment;
		this.paidAt = paidAt;
		this.status = status;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getPayMehtod() {
		return payMehtod;
	}

	public void setPayMehtod(String payMehtod) {
		this.payMehtod = payMehtod;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Date getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [impUid=" + impUid + ", payMehtod=" + payMehtod + ", payment=" + payment + ", paidAt=" + paidAt
				+ ", status=" + status + "]";
	}
	
}
