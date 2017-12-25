/*
 * Created on 22 thg 11 2016 ( Time 13:10:44 )
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
 * Persistent class for entity stored in table "keyword_program"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="keyword_program", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="KeywordProgramEntity.countAll", query="SELECT COUNT(x) FROM KeywordProgramEntity x" )
} )
public class KeywordProgramEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="program_id", nullable=false)
    private Integer    programId    ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="gender")
    private Integer    gender       ;

    @Column(name="age")
    private Integer    age          ;

    @Column(name="genitive")
    private Integer    genitive     ;

    @Column(name="experience")
    private Integer    experience   ;

    @Column(name="place_study")
    private Integer    placeStudy   ;

    @Column(name="area")
    private Integer    area         ;

    @Column(name="time1")
    private Integer    time1        ;

    @Column(name="time2")
    private Integer    time2        ;

    @Column(name="fee_study")
    private Integer    feeStudy     ;

    @Column(name="study_mode")
    private Integer    studyMode    ;

    @Column(name="size_class")
    private Integer    sizeClass    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	
    @ManyToOne
    @JoinColumn(name="program_id", referencedColumnName="id", insertable=false, updatable=false)
    private ProgramEntity program     ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public KeywordProgramEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setProgramId( Integer programId ) {
        this.programId = programId ;
    }
    public Integer getProgramId() {
        return this.programId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : gender ( INT ) 
    public void setGender( Integer gender ) {
        this.gender = gender;
    }
    public Integer getGender() {
        return this.gender;
    }

    //--- DATABASE MAPPING : age ( INT ) 
    public void setAge( Integer age ) {
        this.age = age;
    }
    public Integer getAge() {
        return this.age;
    }

    //--- DATABASE MAPPING : genitive ( INT ) 
    public void setGenitive( Integer genitive ) {
        this.genitive = genitive;
    }
    public Integer getGenitive() {
        return this.genitive;
    }

    //--- DATABASE MAPPING : experience ( INT ) 
    public void setExperience( Integer experience ) {
        this.experience = experience;
    }
    public Integer getExperience() {
        return this.experience;
    }

    //--- DATABASE MAPPING : place_study ( INT ) 
    public void setPlaceStudy( Integer placeStudy ) {
        this.placeStudy = placeStudy;
    }
    public Integer getPlaceStudy() {
        return this.placeStudy;
    }

    //--- DATABASE MAPPING : area ( INT ) 
    public void setArea( Integer area ) {
        this.area = area;
    }
    public Integer getArea() {
        return this.area;
    }

    //--- DATABASE MAPPING : time1 ( INT ) 
    public void setTime1( Integer time1 ) {
        this.time1 = time1;
    }
    public Integer getTime1() {
        return this.time1;
    }

    //--- DATABASE MAPPING : time2 ( INT ) 
    public void setTime2( Integer time2 ) {
        this.time2 = time2;
    }
    public Integer getTime2() {
        return this.time2;
    }

    //--- DATABASE MAPPING : fee_study ( INT ) 
    public void setFeeStudy( Integer feeStudy ) {
        this.feeStudy = feeStudy;
    }
    public Integer getFeeStudy() {
        return this.feeStudy;
    }

    //--- DATABASE MAPPING : study_mode ( INT ) 
    public void setStudyMode( Integer studyMode ) {
        this.studyMode = studyMode;
    }
    public Integer getStudyMode() {
        return this.studyMode;
    }

    //--- DATABASE MAPPING : size_class ( INT ) 
    public void setSizeClass( Integer sizeClass ) {
        this.sizeClass = sizeClass;
    }
    public Integer getSizeClass() {
        return this.sizeClass;
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
    public void setProgram( ProgramEntity program ) {
        this.program = program;
    }
    public ProgramEntity getProgram() {
        return this.program;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(programId);
        sb.append("]:"); 
        sb.append(gender);
        sb.append("|");
        sb.append(age);
        sb.append("|");
        sb.append(genitive);
        sb.append("|");
        sb.append(experience);
        sb.append("|");
        sb.append(placeStudy);
        sb.append("|");
        sb.append(area);
        sb.append("|");
        sb.append(time1);
        sb.append("|");
        sb.append(time2);
        sb.append("|");
        sb.append(feeStudy);
        sb.append("|");
        sb.append(studyMode);
        sb.append("|");
        sb.append(sizeClass);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
