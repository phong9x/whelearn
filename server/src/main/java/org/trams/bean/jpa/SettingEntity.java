/*
 * Created on 27 thg 9 2016 ( Time 11:02:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.bean.jpa;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "setting"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="setting", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="SettingEntity.countAll", query="SELECT COUNT(x) FROM SettingEntity x" )
} )
public class SettingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="upload_content_notification", nullable=false)
    private Integer    uploadContentNotification ;

    @Column(name="open_course_notification", nullable=false)
    private Integer    openCourseNotification ;

    @Column(name="comment_notification", nullable=false)
    private Integer    commentNotification ;

    @Column(name="coupon_notification", nullable=false)
    private Integer    couponNotification ;

    @Column(name="notice_notification", nullable=false)
    private Integer    noticeNotification ;

    @Column(name="finish_register_course_notification", nullable=false)
    private Integer    finishRegisterCourseNotification ;

    @Column(name="finish_pay_course_notification", nullable=false)
    private Integer    finishPayCourseNotification ;

    @Column(name="post_question_course_notification", nullable=false)
    private Integer    postQuestionCourseNotification ;

    @Column(name="post_comment_course_notification", nullable=false)
    private Integer    postCommentCourseNotification ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public SettingEntity() {
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
    //--- DATABASE MAPPING : upload_content_notification ( INT ) 
    public void setUploadContentNotification( Integer uploadContentNotification ) {
        this.uploadContentNotification = uploadContentNotification;
    }
    public Integer getUploadContentNotification() {
        return this.uploadContentNotification;
    }

    //--- DATABASE MAPPING : open_course_notification ( INT ) 
    public void setOpenCourseNotification( Integer openCourseNotification ) {
        this.openCourseNotification = openCourseNotification;
    }
    public Integer getOpenCourseNotification() {
        return this.openCourseNotification;
    }

    //--- DATABASE MAPPING : comment_notification ( INT ) 
    public void setCommentNotification( Integer commentNotification ) {
        this.commentNotification = commentNotification;
    }
    public Integer getCommentNotification() {
        return this.commentNotification;
    }

    //--- DATABASE MAPPING : coupon_notification ( INT ) 
    public void setCouponNotification( Integer couponNotification ) {
        this.couponNotification = couponNotification;
    }
    public Integer getCouponNotification() {
        return this.couponNotification;
    }

    //--- DATABASE MAPPING : notice_notification ( INT ) 
    public void setNoticeNotification( Integer noticeNotification ) {
        this.noticeNotification = noticeNotification;
    }
    public Integer getNoticeNotification() {
        return this.noticeNotification;
    }

    //--- DATABASE MAPPING : finish_register_course_notification ( INT ) 
    public void setFinishRegisterCourseNotification( Integer finishRegisterCourseNotification ) {
        this.finishRegisterCourseNotification = finishRegisterCourseNotification;
    }
    public Integer getFinishRegisterCourseNotification() {
        return this.finishRegisterCourseNotification;
    }

    //--- DATABASE MAPPING : finish_pay_course_notification ( INT ) 
    public void setFinishPayCourseNotification( Integer finishPayCourseNotification ) {
        this.finishPayCourseNotification = finishPayCourseNotification;
    }
    public Integer getFinishPayCourseNotification() {
        return this.finishPayCourseNotification;
    }

    //--- DATABASE MAPPING : post_question_course_notification ( INT ) 
    public void setPostQuestionCourseNotification( Integer postQuestionCourseNotification ) {
        this.postQuestionCourseNotification = postQuestionCourseNotification;
    }
    public Integer getPostQuestionCourseNotification() {
        return this.postQuestionCourseNotification;
    }

    //--- DATABASE MAPPING : post_comment_course_notification ( INT ) 
    public void setPostCommentCourseNotification( Integer postCommentCourseNotification ) {
        this.postCommentCourseNotification = postCommentCourseNotification;
    }
    public Integer getPostCommentCourseNotification() {
        return this.postCommentCourseNotification;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(uploadContentNotification);
        sb.append("|");
        sb.append(openCourseNotification);
        sb.append("|");
        sb.append(commentNotification);
        sb.append("|");
        sb.append(couponNotification);
        sb.append("|");
        sb.append(noticeNotification);
        sb.append("|");
        sb.append(finishRegisterCourseNotification);
        sb.append("|");
        sb.append(finishPayCourseNotification);
        sb.append("|");
        sb.append(postQuestionCourseNotification);
        sb.append("|");
        sb.append(postCommentCourseNotification);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
