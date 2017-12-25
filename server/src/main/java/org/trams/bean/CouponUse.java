/*
 * Created on 31 thg 8 2016 ( Time 16:05:52 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class CouponUse implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer userId;

    @NotNull
    private Integer couponId;


    private Integer statusUse;


    private Date deadline;


    private Date createDate;


    private Date updateDate;



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

    public void setCouponId( Integer couponId ) {
        this.couponId = couponId;
    }
    public Integer getCouponId() {
        return this.couponId;
    }

    public void setStatusUse( Integer statusUse ) {
        this.statusUse = statusUse;
    }
    public Integer getStatusUse() {
        return this.statusUse;
    }

    public void setDeadline( Date deadline ) {
        this.deadline = deadline;
    }
    public Date getDeadline() {
        return this.deadline;
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


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(couponId);
        sb.append("|");
        sb.append(statusUse);
        sb.append("|");
        sb.append(deadline);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
