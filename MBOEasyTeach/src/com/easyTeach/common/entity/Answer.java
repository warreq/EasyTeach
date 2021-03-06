package com.easyTeach.common.entity;

/** 
 * <p>
 * The Answer class represents a row from the Answer table in the easyTeach 
 * database. The class contains getters and setters for the four attributes 
 * derived from the Answer table, namely; questionNo, answerNo, answer and
 * isCorrect. In the database the questionNo references the primary key of
 * the Question table.
 * </p>
 *  
 * <p>
 * In the database the Answer table's primary key is the composite of 
 * questionNo and answerNo.
 * </p>
 *  
 * <p>
 * In the database the Answer table references the primary key of 
 * the Exercise table.
 * </p>
 *  
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory 
 *          (getters/setters).
 */

public class Answer implements Resource {

	private static final long serialVersionUID = 3835988620378877550L;
	

	private String questionNo;
	private String answerNo;
    private String answer;
    private boolean isCorrect;
    
    public Answer() {
    	
    }

    public Answer(String questionNo, String answerNo, String answer,
			boolean isCorrect) {
		this.questionNo = questionNo;
		this.answerNo = answerNo;
		this.answer = answer;
		this.isCorrect = isCorrect;
	}

    public String getQuestionNo() {
        return this.questionNo;
    }
    
    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }
    
    public String getAnswerNo() {
        return this.answerNo;
    }
    
    public void setAnswerNo(String answerNo) {
        this.answerNo = answerNo;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
    
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

	@Override
	public String getName() {
		return "Answer";
	}
    
}