/*
 * Created on 28 thg 9 2016 ( Time 16:10:15 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 300 )
    private String keyword;

    @Size( max = 300 )
    private String typeName;


    private Integer typeId;

    @Size( max = 300 )
    private String description;


    private Integer count;


    private Integer rank;


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
    public void setKeyword( String keyword ) {
        this.keyword = keyword;
    }
    public String getKeyword() {
        return this.keyword;
    }

    public void setTypeName( String typeName ) {
        this.typeName = typeName;
    }
    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeId( Integer typeId ) {
        this.typeId = typeId;
    }
    public Integer getTypeId() {
        return this.typeId;
    }

    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setCount( Integer count ) {
        this.count = count;
    }
    public Integer getCount() {
        return this.count;
    }

    public void setRank( Integer rank ) {
        this.rank = rank;
    }
    public Integer getRank() {
        return this.rank;
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
        sb.append(keyword);
        sb.append("|");
        sb.append(typeName);
        sb.append("|");
        sb.append(typeId);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(count);
        sb.append("|");
        sb.append(rank);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
