/*
 * Created on 22 thg 11 2016 ( Time 13:10:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class KeywordProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer programId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer gender;


    private Integer age;


    private Integer genitive;


    private Integer experience;


    private Integer placeStudy;


    private Integer area;


    private Integer time1;


    private Integer time2;


    private Integer feeStudy;


    private Integer studyMode;


    private Integer sizeClass;


    private Date createDate;


    private Date updateDate;



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
    public void setGender( Integer gender ) {
        this.gender = gender;
    }
    public Integer getGender() {
        return this.gender;
    }

    public void setAge( Integer age ) {
        this.age = age;
    }
    public Integer getAge() {
        return this.age;
    }

    public void setGenitive( Integer genitive ) {
        this.genitive = genitive;
    }
    public Integer getGenitive() {
        return this.genitive;
    }

    public void setExperience( Integer experience ) {
        this.experience = experience;
    }
    public Integer getExperience() {
        return this.experience;
    }

    public void setPlaceStudy( Integer placeStudy ) {
        this.placeStudy = placeStudy;
    }
    public Integer getPlaceStudy() {
        return this.placeStudy;
    }

    public void setArea( Integer area ) {
        this.area = area;
    }
    public Integer getArea() {
        return this.area;
    }

    public void setTime1( Integer time1 ) {
        this.time1 = time1;
    }
    public Integer getTime1() {
        return this.time1;
    }

    public void setTime2( Integer time2 ) {
        this.time2 = time2;
    }
    public Integer getTime2() {
        return this.time2;
    }

    public void setFeeStudy( Integer feeStudy ) {
        this.feeStudy = feeStudy;
    }
    public Integer getFeeStudy() {
        return this.feeStudy;
    }

    public void setStudyMode( Integer studyMode ) {
        this.studyMode = studyMode;
    }
    public Integer getStudyMode() {
        return this.studyMode;
    }

    public void setSizeClass( Integer sizeClass ) {
        this.sizeClass = sizeClass;
    }
    public Integer getSizeClass() {
        return this.sizeClass;
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
        sb.append(programId);
        sb.append("|");
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