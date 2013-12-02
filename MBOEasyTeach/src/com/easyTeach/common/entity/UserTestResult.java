package com.easyTeach.common.entity;

/** 
 * <p>
 * The UserTestResult class represents a row from the UserTestResult table 
 * in the easyTeach database. The class contains getters and setters for 
 * the three attributes derived from the UserTestResult table, namely; 
 * userNo, exerciseNo and score. 
 * </p>
 * 
 * <p>
 * In the database the primary key of the UserTestResult table is the composite 
 * of userNo and exerciseNo.
 * </p>
 * 
 * <p>
 * In the database the UserTestResult table references the primary key of 
 * the Exercise and User tables.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class UserTestResult {

    private String userNo;      // INTEGER in DB
    private String exerciseNo;  // INTEGER in DB
    private int score;
    
    public String getUserNo() {
        return userNo;
    }
    
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    
    public String getExerciseNo() {
        return exerciseNo;
    }
    
    public void setExerciseNo(String exerciseNo) {
        this.exerciseNo = exerciseNo;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
}