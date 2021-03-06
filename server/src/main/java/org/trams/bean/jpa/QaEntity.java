/*
 * Created on 23 thg 9 2016 ( Time 14:55:05 )
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
 * Persistent class for entity stored in table "qa"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="qa", catalog="whelearn" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="QaEntity.countAll", query="SELECT COUNT(x) FROM QaEntity x" )
} )
public class QaEntity implements Serializable {

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
    @Column(name="ask_user_id", nullable=false)
    private Integer    askUserId    ;

    @Column(name="answer_user_id", nullable=false)
    private Integer    answerUserId ;

    @Column(name="questioin", nullable=false, length=500)
    private String     questioin    ;

    @Column(name="answer", nullable=false, length=500)
    private String     answer       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ask_date")
    private Date       askDate      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="answer_dare")
    private Date       answerDare   ;

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
    public QaEntity() {
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
    //--- DATABASE MAPPING : ask_user_id ( INT ) 
    public void setAskUserId( Integer askUserId ) {
        this.askUserId = askUserId;
    }
    public Integer getAskUserId() {
        return this.askUserId;
    }

    //--- DATABASE MAPPING : answer_user_id ( INT ) 
    public void setAnswerUserId( Integer answerUserId ) {
        this.answerUserId = answerUserId;
    }
    public Integer getAnswerUserId() {
        return this.answerUserId;
    }

    //--- DATABASE MAPPING : questioin ( VARCHAR ) 
    public void setQuestioin( String questioin ) {
        this.questioin = questioin;
    }
    public String getQuestioin() {
        return this.questioin;
    }

    //--- DATABASE MAPPING : answer ( VARCHAR ) 
    public void setAnswer( String answer ) {
        this.answer = answer;
    }
    public String getAnswer() {
        return this.answer;
    }

    //--- DATABASE MAPPING : ask_date ( DATETIME ) 
    public void setAskDate( Date askDate ) {
        this.askDate = askDate;
    }
    public Date getAskDate() {
        return this.askDate;
    }

    //--- DATABASE MAPPING : answer_dare ( DATETIME ) 
    public void setAnswerDare( Date answerDare ) {
        this.answerDare = answerDare;
    }
    public Date getAnswerDare() {
        return this.answerDare;
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
        sb.append(askUserId);
        sb.append("|");
        sb.append(answerUserId);
        sb.append("|");
        sb.append(questioin);
        sb.append("|");
        sb.append(answer);
        sb.append("|");
        sb.append(askDate);
        sb.append("|");
        sb.append(answerDare);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
