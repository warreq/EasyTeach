package com.easyTeach.common.entity;

/** 
 * <p>
 * The ExerciseParameter class represents a row from the ExerciseParameter 
 * table in the easyTeach database. The class contains getters and setters 
 * for the six attributes derived from the ExerciseParameter table, namely; 
 * exerciseParameterNo, isTest, isLocked, accessBegin, accessEnd, timeLimit. 
 * </p>
 * 
 * <p>
 * In the database the primary key of the ExerciseParameter table is the 
 * exerciseParameterNo.
 * </p>
 * 
 * <p>
 * In the database the Exercise table references ExerciseParameter.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class ExerciseParameter {

    private String exerciseParameterNo; // INTEGER in DB
    private boolean isTest;
    private boolean isLocked;
    private java.sql.Timestamp accessBegin;
    private java.sql.Timestamp accessEnd;
    private int timeLimit;
    
    public String getExerciseParameterNo() {
        return exerciseParameterNo;
    }
    
    public void setExerciseParameterNo(String exerciseParameterNo) {
        this.exerciseParameterNo = exerciseParameterNo;
    }
    
    public boolean getIsTest() {
        return isTest;
    }
    
    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }
    
    public boolean getIsLocked() {
        return isLocked;
    }
    
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    
    public java.sql.Timestamp getAccessBegin() {
        return accessBegin;
    }
    
    public void setAccessBegin(java.sql.Timestamp accessBegin) {
        this.accessBegin = accessBegin;
    }
    
    public java.sql.Timestamp getAccessEnd() {
        return accessEnd;
    }
    
    public void setAccessEnd(java.sql.Timestamp accessEnd) {
        this.accessEnd = accessEnd;
    }
    
    public int getTimeLimit() {
        return timeLimit;
    }
    
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
}