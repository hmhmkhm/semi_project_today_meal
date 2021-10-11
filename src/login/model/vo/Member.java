package login.model.vo;

import java.sql.Date;

public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private String apiToken;
	private int coin;
	private String email;
	private String questionNo;
	private String questionAnswer;
	private String address1;
	private String address2;
	private String address3;
	
	public Member() {}
	
	
	



	public Member(String userId) {
		super();
		this.userId = userId;
	}
	



	public Member(int userNo, String phone, String questionNo, String questionAnswer, String address1, String address2,
			String address3) {
		super();
		this.userNo = userNo;
		this.phone = phone;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}



	public Member(String userId, String userName, String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
	}



	public Member(String userId, String userPwd, String userName, String phone, String email, String questionNo,
			String questionAnswer, String address1, String address2, String address3) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}


	public Member(int userNo, String userId, String userPwd, String userName, String phone, Date enrollDate,
			Date modifyDate, String status, String apiToken, int coin, String email, String questionNo,
			String questionAnswer, String address1, String address2, String address3) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.apiToken = apiToken;
		this.coin = coin;
		this.email = email;
		this.questionNo = questionNo;
		this.questionAnswer = questionAnswer;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", phone=" + phone + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", status="
				+ status + ", apiToken=" + apiToken + ", coin=" + coin + ", email=" + email + ", questionNo="
				+ questionNo + ", questionAnswer=" + questionAnswer + ", address1=" + address1 + ", address2="
				+ address2 + ", address3=" + address3 + "]";
	}
	
	

	
	

	
	

	

}
