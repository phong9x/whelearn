/*
 * Created on 11 thg 10 2016 ( Time 15:37:50 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Integer userId;

    @NotNull
    private Integer programId;

    @Size( max = 200 )
    private String fullname;

    @Size( max = 15 )
    private String phone;


    private Integer couponId;

    @NotNull
    private Integer totalMoney;

    @Size( max = 500 )
    private String paymentMethodName;


    private Integer refundMoney;


    private Date refundDate;

    @Size( max = 200 )
    private String tid;


    private Date createDate;


    private Date updateDate;


    private Integer orderProgram;

    @Size( max = 500 )
    private String accountInfo;


    public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	//----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setProgramId( Integer programId ) {
        this.programId = programId;
    }
    public Integer getProgramId() {
        return this.programId;
    }

    public void setFullname( String fullname ) {
        this.fullname = fullname;
    }
    public String getFullname() {
        return this.fullname;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }
    public String getPhone() {
        return this.phone;
    }

    public void setCouponId( Integer couponId ) {
        this.couponId = couponId;
    }
    public Integer getCouponId() {
        return this.couponId;
    }

    public void setTotalMoney( Integer totalMoney ) {
        this.totalMoney = totalMoney;
    }
    public Integer getTotalMoney() {
        return this.totalMoney;
    }

    public void setPaymentMethodName( String paymentMethodName ) {
        this.paymentMethodName = paymentMethodName;
    }
    public String getPaymentMethodName() {
        return this.paymentMethodName;
    }

    public void setRefundMoney( Integer refundMoney ) {
        this.refundMoney = refundMoney;
    }
    public Integer getRefundMoney() {
        return this.refundMoney;
    }

    public void setRefundDate( Date refundDate ) {
        this.refundDate = refundDate;
    }
    public Date getRefundDate() {
        return this.refundDate;
    }

    public void setTid( String tid ) {
        this.tid = tid;
    }
    public String getTid() {
        return this.tid;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setOrderProgram( Integer orderProgram ) {
        this.orderProgram = orderProgram;
    }
    public Integer getOrderProgram() {
        return this.orderProgram;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(programId);
        sb.append("|");
        sb.append(fullname);
        sb.append("|");
        sb.append(phone);
        sb.append("|");
        sb.append(couponId);
        sb.append("|");
        sb.append(totalMoney);
        sb.append("|");
        sb.append(paymentMethodName);
        sb.append("|");
        sb.append(refundMoney);
        sb.append("|");
        sb.append(refundDate);
        sb.append("|");
        sb.append(tid);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(orderProgram);
        return sb.toString(); 
    } 


}