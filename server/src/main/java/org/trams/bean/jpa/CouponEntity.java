/*
 * Created on 31 thg 8 2016 ( Time 16:05:52 )
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
 * Persistent class for entity stored in table "coupon"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="coupon", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CouponEntity.countAll", query="SELECT COUNT(x) FROM CouponEntity x" )
} )
public class CouponEntity implements Serializable {

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
    @Column(name="name", length=300)
    private String     name         ;

    @Column(name="content", length=500)
    private String     content      ;

    @Column(name="effect_day")
    private Integer    effectDay    ;

    @Column(name="condition")
    private Integer    condition    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="coupon", targetEntity=CouponUseEntity.class)
    private List<CouponUseEntity> listOfCouponUse;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CouponEntity() {
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
    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : content ( VARCHAR ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : effect_day ( INT ) 
    public void setEffectDay( Integer effectDay ) {
        this.effectDay = effectDay;
    }
    public Integer getEffectDay() {
        return this.effectDay;
    }

    //--- DATABASE MAPPING : condition ( INT ) 
    public void setCondition( Integer condition ) {
        this.condition = condition;
    }
    public Integer getCondition() {
        return this.condition;
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
    public void setListOfCouponUse( List<CouponUseEntity> listOfCouponUse ) {
        this.listOfCouponUse = listOfCouponUse;
    }
    public List<CouponUseEntity> getListOfCouponUse() {
        return this.listOfCouponUse;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(name);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(effectDay);
        sb.append("|");
        sb.append(condition);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}