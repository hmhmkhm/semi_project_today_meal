package myLike.model.vo;

import java.sql.Date;

import totalReview.model.vo.Review;

public class MyLike {
	private int lno;
	private Date lDate;
	private int userNo;
	private Review review;

	public MyLike(int lno, Date lDate, int userNo, Review review) {
		super();
		this.lno = lno;
		this.lDate = lDate;
		this.userNo = userNo;
		this.review = review;
	}
	
	public MyLike(int lno, Review review) {
		super();
		this.lno = lno;
		this.review = review;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "MyLike [lno=" + lno + ", lDate=" + lDate + ", userNo=" + userNo + ", review=" + review + "]";
	}
	
}
