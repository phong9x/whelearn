/*
 * Created on 1 thg 9 2016 ( Time 10:04:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class QuestionAnswerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer programId;


    private Integer askUserId;


    private Integer answerUserId;

    @Size( max = 500 )
    private String questioin;

    @Size( max = 500 )
    private String answer;


    private Date askDate;


    private Date answerDate;

    private String askUserName;
    
    private String answerUserName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public Integer getAskUserId() {
		return askUserId;
	}

	public void setAskUserId(Integer askUserId) {
		this.askUserId = askUserId;
	}

	public Integer getAnswerUserId() {
		return answerUserId;
	}

	public void setAnswerUserId(Integer answerUserId) {
		this.answerUserId = answerUserId;
	}

	public String getQuestioin() {
		return questioin;
	}

	public void setQuestioin(String questioin) {
		this.questioin = questioin;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getAskDate() {
		return askDate;
	}

	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}


	public String getAskUserName() {
		return askUserName;
	}

	public void setAskUserName(String askUserName) {
		this.askUserName = askUserName;
	}

	public String getAnswerUserName() {
		return answerUserName;
	}

	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName;
	}

    
}
