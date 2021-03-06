package com.easyTeach.common.entity;

/**
 * <p>
 * The ExerciseQuestionRelation class represents a row from the
 * ExerciseQuestionRelation table in the easyTeach database. The class contains
 * getters and setters for the two attributes derived from the
 * ExerciseQuestionRelation table, namely; exerciseNo and questionNo. The table
 * maps the relation between Exercises and Questions.
 * </p>
 * 
 * <p>
 * In the database the ExerciseQuestionRelation table's primary key is the
 * composite of exerciseNo and questionNo.
 * </p>
 * 
 * <p>
 * In the database the ExerciseQuestionRelation table references the primary key
 * of the Exercise and Question tables.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class ExerciseQuestionRelation implements Resource {

	private static final long serialVersionUID = -3678477660464296628L;
	private String exerciseNo;
	private String questionNo;

	public ExerciseQuestionRelation() {

	}

	public ExerciseQuestionRelation(String exerciseNo, String questionNo) {
		this.exerciseNo = exerciseNo;
		this.questionNo = questionNo;
	}

	public String getExerciseNo() {
		return this.exerciseNo;
	}

	public void setExerciseNo(String exerciseNo) {
		this.exerciseNo = exerciseNo;
	}

	public String getQuestionNo() {
		return this.questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	@Override
	public String getName() {
		return "ExerciseQuestionRelation";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		ExerciseQuestionRelation relation = (ExerciseQuestionRelation) obj;
		return this.exerciseNo.equals(relation.exerciseNo)
				&& (this.questionNo.equals(relation.questionNo));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.exerciseNo == null) ? 0 : this.exerciseNo.hashCode());
		result = prime * result + this.questionNo.hashCode();
		return result;
	}

}