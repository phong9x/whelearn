/*
 * Created on 14 thg 10 2016 ( Time 17:10:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Program implements Serializable {

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
    private Integer categoryId;

    @NotNull
    private Integer userId;

    @NotNull
    @Size( min = 1, max = 300 )
    private String categoryName;

    @NotNull
    @Size( min = 1, max = 300 )
    private String title;

    @NotNull
    @Size( min = 1, max = 300 )
    private String summary;

    @Size( max = 65535 )
    private String content;

    @Size( max = 65535 )
    private String introduceTeacher;

    @Size( max = 65535 )
    private String introduceProgram;

    @Size( max = 65535 )
    private String introduceStudyProgram;


    private Integer fee;

    @Size( max = 500 )
    private String address;

    @Size( max = 500 )
    private String place;

    @Size( max = 500 )
    private String addressGuide;


    private Integer totalPeople;


    private Integer hearingFaculty;

    @Size( max = 50 )
    private String totalTime;


    private Date deadLine;

    @Size( max = 500 )
    private String imageUrl;

    @Size( max = 500 )
    private String videoUrl;

    @Size( max = 500 )
    private String imageName;


    private Integer includeFee;


    private Integer includeMaterialFee;


    private Integer includeLearningBook;


    private Integer includeWifi;


    private Integer includeParking;


    private Integer includeDrinking;


    private Double latitude;


    private Double longitude;


    private Float point;

    @NotNull
    private Integer isDelete;


    private Date updateDate;


    private Date createDate;


    private Integer isLike;


    private Integer likeNumber;


    private Long totalMoney;


    private Long moneyPaid;


    private Integer statusPayment;


    private Date datePayment;



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
    public void setCategoryId( Integer categoryId ) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setSummary( String summary ) {
        this.summary = summary;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setIntroduceTeacher( String introduceTeacher ) {
        this.introduceTeacher = introduceTeacher;
    }
    public String getIntroduceTeacher() {
        return this.introduceTeacher;
    }

    public void setIntroduceProgram( String introduceProgram ) {
        this.introduceProgram = introduceProgram;
    }
    public String getIntroduceProgram() {
        return this.introduceProgram;
    }

    public void setIntroduceStudyProgram( String introduceStudyProgram ) {
        this.introduceStudyProgram = introduceStudyProgram;
    }
    public String getIntroduceStudyProgram() {
        return this.introduceStudyProgram;
    }

    public void setFee( Integer fee ) {
        this.fee = fee;
    }
    public Integer getFee() {
        return this.fee;
    }

    public void setAddress( String address ) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    public void setPlace( String place ) {
        this.place = place;
    }
    public String getPlace() {
        return this.place;
    }

    public void setAddressGuide( String addressGuide ) {
        this.addressGuide = addressGuide;
    }
    public String getAddressGuide() {
        return this.addressGuide;
    }

    public void setTotalPeople( Integer totalPeople ) {
        this.totalPeople = totalPeople;
    }
    public Integer getTotalPeople() {
        return this.totalPeople;
    }

    public void setHearingFaculty( Integer hearingFaculty ) {
        this.hearingFaculty = hearingFaculty;
    }
    public Integer getHearingFaculty() {
        return this.hearingFaculty;
    }

    public void setTotalTime( String totalTime ) {
        this.totalTime = totalTime;
    }
    public String getTotalTime() {
        return this.totalTime;
    }

    public void setDeadLine( Date deadLine ) {
        this.deadLine = deadLine;
    }
    public Date getDeadLine() {
        return this.deadLine;
    }

    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setVideoUrl( String videoUrl ) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setImageName( String imageName ) {
        this.imageName = imageName;
    }
    public String getImageName() {
        return this.imageName;
    }

    public void setIncludeFee( Integer includeFee ) {
        this.includeFee = includeFee;
    }
    public Integer getIncludeFee() {
        return this.includeFee;
    }

    public void setIncludeMaterialFee( Integer includeMaterialFee ) {
        this.includeMaterialFee = includeMaterialFee;
    }
    public Integer getIncludeMaterialFee() {
        return this.includeMaterialFee;
    }

    public void setIncludeLearningBook( Integer includeLearningBook ) {
        this.includeLearningBook = includeLearningBook;
    }
    public Integer getIncludeLearningBook() {
        return this.includeLearningBook;
    }

    public void setIncludeWifi( Integer includeWifi ) {
        this.includeWifi = includeWifi;
    }
    public Integer getIncludeWifi() {
        return this.includeWifi;
    }

    public void setIncludeParking( Integer includeParking ) {
        this.includeParking = includeParking;
    }
    public Integer getIncludeParking() {
        return this.includeParking;
    }

    public void setIncludeDrinking( Integer includeDrinking ) {
        this.includeDrinking = includeDrinking;
    }
    public Integer getIncludeDrinking() {
        return this.includeDrinking;
    }

    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }
    public Double getLatitude() {
        return this.latitude;
    }

    public void setLongitude( Double longitude ) {
        this.longitude = longitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }

    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return this.isDelete;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setIsLike( Integer isLike ) {
        this.isLike = isLike;
    }
    public Integer getIsLike() {
        return this.isLike;
    }

    public void setLikeNumber( Integer likeNumber ) {
        this.likeNumber = likeNumber;
    }
    public Integer getLikeNumber() {
        return this.likeNumber;
    }

    public void setTotalMoney( Long totalMoney ) {
        this.totalMoney = totalMoney;
    }
    public Long getTotalMoney() {
        return this.totalMoney;
    }

    public void setMoneyPaid( Long moneyPaid ) {
        this.moneyPaid = moneyPaid;
    }
    public Long getMoneyPaid() {
        return this.moneyPaid;
    }

    public void setStatusPayment( Integer statusPayment ) {
        this.statusPayment = statusPayment;
    }
    public Integer getStatusPayment() {
        return this.statusPayment;
    }

    public void setDatePayment( Date datePayment ) {
        this.datePayment = datePayment;
    }
    public Date getDatePayment() {
        return this.datePayment;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(categoryId);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(categoryName);
        sb.append("|");
        sb.append(title);
        sb.append("|");
        sb.append(summary);
        // attribute 'content' not usable (type = String Long Text)
        // attribute 'introduceTeacher' not usable (type = String Long Text)
        // attribute 'introduceProgram' not usable (type = String Long Text)
        // attribute 'introduceStudyProgram' not usable (type = String Long Text)
        sb.append("|");
        sb.append(fee);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(place);
        sb.append("|");
        sb.append(addressGuide);
        sb.append("|");
        sb.append(totalPeople);
        sb.append("|");
        sb.append(hearingFaculty);
        sb.append("|");
        sb.append(totalTime);
        sb.append("|");
        sb.append(deadLine);
        sb.append("|");
        sb.append(imageUrl);
        sb.append("|");
        sb.append(videoUrl);
        sb.append("|");
        sb.append(imageName);
        sb.append("|");
        sb.append(includeFee);
        sb.append("|");
        sb.append(includeMaterialFee);
        sb.append("|");
        sb.append(includeLearningBook);
        sb.append("|");
        sb.append(includeWifi);
        sb.append("|");
        sb.append(includeParking);
        sb.append("|");
        sb.append(includeDrinking);
        sb.append("|");
        sb.append(latitude);
        sb.append("|");
        sb.append(longitude);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(isDelete);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(isLike);
        sb.append("|");
        sb.append(likeNumber);
        sb.append("|");
        sb.append(totalMoney);
        sb.append("|");
        sb.append(moneyPaid);
        sb.append("|");
        sb.append(statusPayment);
        sb.append("|");
        sb.append(datePayment);
        return sb.toString(); 
    } 


}