package org.trams.bean;

import java.util.Date;

public class CalculateItem {
public String nickname;
public String email;
public Date payDate;
public String couponContent;
public Integer moneyPaid;
public Integer status;
public Integer refundMoney;
public Date refundDate;
public String name;

public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getPayDate() {
	return payDate;
}
public void setPayDate(Date payDate) {
	this.payDate = payDate;
}
public String getCouponContent() {
	return couponContent;
}
public void setCouponContent(String couponContent) {
	this.couponContent = couponContent;
}
public Integer getMoneyPaid() {
	return moneyPaid;
}
public void setMoneyPaid(Integer moneyPaid) {
	this.moneyPaid = moneyPaid;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Integer getRefundMoney() {
	return refundMoney;
}
public void setRefundMoney(Integer refundMoney) {
	this.refundMoney = refundMoney;
}
public Date getRefundDate() {
	return refundDate;
}
public void setRefundDate(Date refundDate) {
	this.refundDate = refundDate;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


}
