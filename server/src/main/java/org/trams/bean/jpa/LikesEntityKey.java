/*
 * Created on 25 thg 8 2016 ( Time 17:03:11 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean.jpa;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "LikesEntity" ( stored in table "likes" )
 *
 * @author Telosys Tools Generator
 *
 */
 @Embeddable
public class LikesEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="user_id", nullable=false)
    private Integer    userId       ;
    @Column(name="thread_id", nullable=false)
    private Integer    threadId     ;
    @Column(name="type", nullable=false)
    private Integer    type         ;

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public LikesEntityKey() {
        super();
    }

    public LikesEntityKey( Integer userId, Integer threadId, Integer type ) {
        super();
        this.userId = userId ;
        this.threadId = threadId ;
        this.type = type ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setUserId( Integer value ) {
        this.userId = value;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setThreadId( Integer value ) {
        this.threadId = value;
    }
    public Integer getThreadId() {
        return this.threadId;
    }

    public void setType( Integer value ) {
        this.type = value;
    }
    public Integer getType() {
        return this.type;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		LikesEntityKey other = (LikesEntityKey) obj; 
		//--- Attribute userId
		if ( userId == null ) { 
			if ( other.userId != null ) 
				return false ; 
		} else if ( ! userId.equals(other.userId) ) 
			return false ; 
		//--- Attribute threadId
		if ( threadId == null ) { 
			if ( other.threadId != null ) 
				return false ; 
		} else if ( ! threadId.equals(other.threadId) ) 
			return false ; 
		//--- Attribute type
		if ( type == null ) { 
			if ( other.type != null ) 
				return false ; 
		} else if ( ! type.equals(other.type) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute userId
		result = prime * result + ((userId == null) ? 0 : userId.hashCode() ) ; 
		//--- Attribute threadId
		result = prime * result + ((threadId == null) ? 0 : threadId.hashCode() ) ; 
		//--- Attribute type
		result = prime * result + ((type == null) ? 0 : type.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(userId); 
		sb.append("|"); 
		sb.append(threadId); 
		sb.append("|"); 
		sb.append(type); 
        return sb.toString();
    }
}