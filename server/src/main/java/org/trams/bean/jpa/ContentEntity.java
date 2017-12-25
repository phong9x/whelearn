/*
 * Created on 19 thg 12 2016 ( Time 16:23:58 )
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
 * Persistent class for entity stored in table "content"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="content", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ContentEntity.countAll", query="SELECT COUNT(x) FROM ContentEntity x" )
} )
public class ContentEntity implements Serializable {

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

    @Column(name="category_name", length=50)
    private String     categoryName ;

    @Column(name="title", nullable=false, length=300)
    private String     title        ;

    @Column(name="summary", nullable=false, length=500)
    private String     summary      ;

    @Column(name="image_url", length=500)
    private String     imageUrl     ;

    @Column(name="image_name", length=500)
    private String     imageName    ;

    @Column(name="video_url", length=500)
    private String     videoUrl     ;

    @Column(name="content", nullable=false)
    private String     content      ;

    @Column(name="is_like")
    private Integer    isLike       ;

    @Column(name="like_number")
    private Integer    likeNumber   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="is_delete")
    private Integer    isDelete     ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="content", targetEntity=ContentsRecomEntity.class)
    private List<ContentsRecomEntity> listOfContentsRecom;

	@JsonIgnore
    @OneToMany(mappedBy="content", targetEntity=ContentRelatedEntity.class)
    private List<ContentRelatedEntity> listOfContentRelated;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ContentEntity() {
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

    //--- DATABASE MAPPING : image_url ( VARCHAR ) 
    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }

    //--- DATABASE MAPPING : image_name ( VARCHAR ) 
    public void setImageName( String imageName ) {
        this.imageName = imageName;
    }
    public String getImageName() {
        return this.imageName;
    }

    //--- DATABASE MAPPING : video_url ( VARCHAR ) 
    public void setVideoUrl( String videoUrl ) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }

    //--- DATABASE MAPPING : content ( TEXT ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
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

    //--- DATABASE MAPPING : is_delete ( INT ) 
    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return this.isDelete;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfContentsRecom( List<ContentsRecomEntity> listOfContentsRecom ) {
        this.listOfContentsRecom = listOfContentsRecom;
    }
    public List<ContentsRecomEntity> getListOfContentsRecom() {
        return this.listOfContentsRecom;
    }

    public void setListOfContentRelated( List<ContentRelatedEntity> listOfContentRelated ) {
        this.listOfContentRelated = listOfContentRelated;
    }
    public List<ContentRelatedEntity> getListOfContentRelated() {
        return this.listOfContentRelated;
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
        sb.append("|");
        sb.append(imageUrl);
        sb.append("|");
        sb.append(imageName);
        sb.append("|");
        sb.append(videoUrl);
        // attribute 'content' not usable (type = String Long Text)
        sb.append("|");
        sb.append(isLike);
        sb.append("|");
        sb.append(likeNumber);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(isDelete);
        return sb.toString(); 
    } 

}
