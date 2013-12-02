package com.easyTeach.common.entity;

/** 
 * <p>
 * The UserQuestionState class represents a row from the UserQuestionState 
 * table in the easyTeach database. The class contains getters and setters 
 * for the two attributes derived from the UserQuestionState table, namely; 
 * userNo and questionNo.
 * </p>
 * 
 * <p>
 * In the database the UserQuestionState table's primary key is the 
 * composite of userNo and questionNo.
 * </p>
 * 
 * <p>
 * In the database the UserQuestionState table references the primary key of 
 * the Question and User tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class UserQuestionState {

    private String userNo;      // INTEGER in DB
    private String questionNo;  // INTEGER in DB
    private boolean hasCompleted;
    
    public String getUserNo() {
        return userNo;
    }
    
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    
    public String getQuestionNo() {
        return questionNo;
    }
    
    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }
    
    public boolean getHasCompleted() {
        return hasCompleted;
    }
    
    public void setHasCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }
    
}