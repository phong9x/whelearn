/*
 * Created on 14 thg 10 2016 ( Time 17:10:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.bean.jpa;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "program"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="program", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ProgramEntity.countAll", query="SELECT COUNT(x) FROM ProgramEntity x" )
} )
public class ProgramEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="category_id", nullable=false)
    private Integer    categoryId   ;

    @Column(name="category_name", nullable=false, length=300)
    private String     categoryName ;

    @Column(name="title", nullable=false, length=300)
    private String     title        ;

    @Column(name="summary", nullable=false, length=300)
    private String     summary      ;

    @Column(name="content")
    private String     content      ;

    @Column(name="introduce_teacher")
    private String     introduceTeacher ;

    @Column(name="introduce_program")
    private String     introduceProgram ;

    @Column(name="introduce_study_program")
    private String     introduceStudyProgram ;

    @Column(name="fee")
    private Integer    fee          ;

    @Column(name="address", length=500)
    private String     address      ;

    @Column(name="place", length=500)
    private String     place        ;

    @Column(name="address_guide", length=500)
    private String     addressGuide ;

    @Column(name="total_people")
    private Integer    totalPeople  ;

    @Column(name="hearing_faculty")
    private Integer    hearingFaculty ;

    @Column(name="total_time", length=50)
    private String     totalTime    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dead_line")
    private Date       deadLine     ;

    @Column(name="image_url", length=500)
    private String     imageUrl     ;

    @Column(name="video_url", length=500)
    private String     videoUrl     ;

    @Column(name="image_name", length=500)
    private String     imageName    ;

    @Column(name="include_fee")
    private Integer    includeFee   ;

    @Column(name="include_material_fee")
    private Integer    includeMaterialFee ;

    @Column(name="include_learning_book")
    private Integer    includeLearningBook ;

    @Column(name="include_wifi")
    private Integer    includeWifi  ;

    @Column(name="include_parking")
    private Integer    includeParking ;

    @Column(name="include_drinking")
    private Integer    includeDrinking ;

    @Column(name="latitude")
    private Double     latitude     ;

    @Column(name="longitude")
    private Double     longitude    ;

    @Column(name="point")
    private Float      point        ;

    @Column(name="is_delete", nullable=false)
    private Integer    isDelete     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="is_like")
    private Integer    isLike       ;

    @Column(name="like_number")
    private Integer    likeNumber   ;

    @Column(name="total_money")
    private Long       totalMoney   ;

    @Column(name="money_paid")
    private Long       moneyPaid    ;

    @Column(name="status_payment")
    private Integer    statusPayment ;

    @Temporal(TemporalType.DATE)
    @Column(name="date_payment")
    private Date       datePayment  ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=ContentRelatedEntity.class)
    private List<ContentRelatedEntity> listOfContentRelated;

	@JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=KeywordProgramEntity.class)
    private List<KeywordProgramEntity> listOfKeywordProgram;

	
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=PurchaseEntity.class)
    private List<PurchaseEntity> listOfPurchase;

    @JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=ProgramRegisterEntity.class)
    private List<ProgramRegisterEntity> listOfProgramRegister;

    @JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=ProgramViewEntity.class)
    private List<ProgramViewEntity> listOfProgramView;

    @JsonIgnore
    @OneToMany(mappedBy="program", targetEntity=TimeLearnEntity.class, fetch = FetchType.EAGER)
    private List<TimeLearnEntity> listOfTimeLearn;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ProgramEntity() {
		super();
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
    //--- DATABASE MAPPING : category_id ( INT ) 
    public void setCategoryId( Integer categoryId ) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryId() {
        return this.categoryId;
    }

    //--- DATABASE MAPPING : category_name ( VARCHAR ) 
    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return this.categoryName;
    }

    //--- DATABASE MAPPING : title ( VARCHAR ) 
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    //--- DATABASE MAPPING : summary ( VARCHAR ) 
    public void setSummary( String summary ) {
        this.summary = summary;
    }
    public String getSummary() {
        return this.summary;
    }

    //--- DATABASE MAPPING : content ( TEXT ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : introduce_teacher ( TEXT ) 
    public void setIntroduceTeacher( String introduceTeacher ) {
        this.introduceTeacher = introduceTeacher;
    }
    public String getIntroduceTeacher() {
        return this.introduceTeacher;
    }

    //--- DATABASE MAPPING : introduce_program ( TEXT ) 
    public void setIntroduceProgram( String introduceProgram ) {
        this.introduceProgram = introduceProgram;
    }
    public String getIntroduceProgram() {
        return this.introduceProgram;
    }

    //--- DATABASE MAPPING : introduce_study_program ( TEXT ) 
    public void setIntroduceStudyProgram( String introduceStudyProgram ) {
        this.introduceStudyProgram = introduceStudyProgram;
    }
    public String getIntroduceStudyProgram() {
        return this.introduceStudyProgram;
    }

    //--- DATABASE MAPPING : fee ( INT ) 
    public void setFee( Integer fee ) {
        this.fee = fee;
    }
    public Integer getFee() {
        return this.fee;
    }

    //--- DATABASE MAPPING : address ( VARCHAR ) 
    public void setAddress( String address ) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }

    //--- DATABASE MAPPING : place ( VARCHAR ) 
    public void setPlace( String place ) {
        this.place = place;
    }
    public String getPlace() {
        return this.place;
    }

    //--- DATABASE MAPPING : address_guide ( VARCHAR ) 
    public void setAddressGuide( String addressGuide ) {
        this.addressGuide = addressGuide;
    }
    public String getAddressGuide() {
        return this.addressGuide;
    }

    //--- DATABASE MAPPING : total_people ( INT ) 
    public void setTotalPeople( Integer totalPeople ) {
        this.totalPeople = totalPeople;
    }
    public Integer getTotalPeople() {
        return this.totalPeople;
    }

    //--- DATABASE MAPPING : hearing_faculty ( INT ) 
    public void setHearingFaculty( Integer hearingFaculty ) {
        this.hearingFaculty = hearingFaculty;
    }
    public Integer getHearingFaculty() {
        return this.hearingFaculty;
    }

    //--- DATABASE MAPPING : total_time ( VARCHAR ) 
    public void setTotalTime( String totalTime ) {
        this.totalTime = totalTime;
    }
    public String getTotalTime() {
        return this.totalTime;
    }

    //--- DATABASE MAPPING : dead_line ( DATETIME ) 
    public void setDeadLine( Date deadLine ) {
        this.deadLine = deadLine;
    }
    public Date getDeadLine() {
        return this.deadLine;
    }

    //--- DATABASE MAPPING : image_url ( VARCHAR ) 
    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }

    //--- DATABASE MAPPING : video_url ( VARCHAR ) 
    public void setVideoUrl( String videoUrl ) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }

    //--- DATABASE MAPPING : image_name ( VARCHAR ) 
    public void setImageName( String imageName ) {
        this.imageName = imageName;
    }
    public String getImageName() {
        return this.imageName;
    }

    //--- DATABASE MAPPING : include_fee ( INT ) 
    public void setIncludeFee( Integer includeFee ) {
        this.includeFee = includeFee;
    }
    public Integer getIncludeFee() {
        return this.includeFee;
    }

    //--- DATABASE MAPPING : include_material_fee ( INT ) 
    public void setIncludeMaterialFee( Integer includeMaterialFee ) {
        this.includeMaterialFee = includeMaterialFee;
    }
    public Integer getIncludeMaterialFee() {
        return this.includeMaterialFee;
    }

    //--- DATABASE MAPPING : include_learning_book ( INT ) 
    public void setIncludeLearningBook( Integer includeLearningBook ) {
        this.includeLearningBook = includeLearningBook;
    }
    public Integer getIncludeLearningBook() {
        return this.includeLearningBook;
    }

    //--- DATABASE MAPPING : include_wifi ( INT ) 
    public void setIncludeWifi( Integer includeWifi ) {
        this.includeWifi = includeWifi;
    }
    public Integer getIncludeWifi() {
        return this.includeWifi;
    }

    //--- DATABASE MAPPING : include_parking ( INT ) 
    public void setIncludeParking( Integer includeParking ) {
        this.includeParking = includeParking;
    }
    public Integer getIncludeParking() {
        return this.includeParking;
    }

    //--- DATABASE MAPPING : include_drinking ( INT ) 
    public void setIncludeDrinking( Integer includeDrinking ) {
        this.includeDrinking = includeDrinking;
    }
    public Integer getIncludeDrinking() {
        return this.includeDrinking;
    }

    //--- DATABASE MAPPING : latitude ( DOUBLE ) 
    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }
    public Double getLatitude() {
        return this.latitude;
    }

    //--- DATABASE MAPPING : longitude ( DOUBLE ) 
    public void setLongitude( Double longitude ) {
        this.longitude = longitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }

    //--- DATABASE MAPPING : point ( FLOAT ) 
    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    //--- DATABASE MAPPING : is_delete ( INT ) 
    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return this.isDelete;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : is_like ( INT ) 
    public void setIsLike( Integer isLike ) {
        this.isLike = isLike;
    }
    public Integer getIsLike() {
        return this.isLike;
    }

    //--- DATABASE MAPPING : like_number ( INT ) 
    public void setLikeNumber( Integer likeNumber ) {
        this.likeNumber = likeNumber;
    }
    public Integer getLikeNumber() {
        return this.likeNumber;
    }

    //--- DATABASE MAPPING : total_money ( BIGINT ) 
    public void setTotalMoney( Long totalMoney ) {
        this.totalMoney = totalMoney;
    }
    public Long getTotalMoney() {
        return this.totalMoney;
    }

    //--- DATABASE MAPPING : money_paid ( BIGINT ) 
    public void setMoneyPaid( Long moneyPaid ) {
        this.moneyPaid = moneyPaid;
    }
    public Long getMoneyPaid() {
        return this.moneyPaid;
    }

    //--- DATABASE MAPPING : status_payment ( INT ) 
    public void setStatusPayment( Integer statusPayment ) {
        this.statusPayment = statusPayment;
    }
    public Integer getStatusPayment() {
        return this.statusPayment;
    }

    //--- DATABASE MAPPING : date_payment ( DATE ) 
    public void setDatePayment( Date datePayment ) {
        this.datePayment = datePayment;
    }
    public Date getDatePayment() {
        return this.datePayment;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfContentRelated( List<ContentRelatedEntity> listOfContentRelated ) {
        this.listOfContentRelated = listOfContentRelated;
    }
    public List<ContentRelatedEntity> getListOfContentRelated() {
        return this.listOfContentRelated;
    }

    public void setListOfKeywordProgram( List<KeywordProgramEntity> listOfKeywordProgram ) {
        this.listOfKeywordProgram = listOfKeywordProgram;
    }
    public List<KeywordProgramEntity> getListOfKeywordProgram() {
        return this.listOfKeywordProgram;
    }

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setListOfPurchase( List<PurchaseEntity> listOfPurchase ) {
        this.listOfPurchase = listOfPurchase;
    }
    public List<PurchaseEntity> getListOfPurchase() {
        return this.listOfPurchase;
    }

    public void setListOfProgramRegister( List<ProgramRegisterEntity> listOfProgramRegister ) {
        this.listOfProgramRegister = listOfProgramRegister;
    }
    public List<ProgramRegisterEntity> getListOfProgramRegister() {
        return this.listOfProgramRegister;
    }

    public void setListOfProgramView( List<ProgramViewEntity> listOfProgramView ) {
        this.listOfProgramView = listOfProgramView;
    }
    public List<ProgramViewEntity> getListOfProgramView() {
        return this.listOfProgramView;
    }

    public void setListOfTimeLearn( List<TimeLearnEntity> listOfTimeLearn ) {
        this.listOfTimeLearn = listOfTimeLearn;
    }
    public List<TimeLearnEntity> getListOfTimeLearn() {
        return this.listOfTimeLearn;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(categoryId);
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
