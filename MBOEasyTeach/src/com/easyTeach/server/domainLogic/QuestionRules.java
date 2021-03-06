package com.easyTeach.server.domainLogic;

import java.sql.SQLException;

import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.QuestionWrapper;
import com.easyTeach.server.databaseWrapper.TagWrapper;

public class QuestionRules {

    /**
     * Class used for manipulating with {@link Question} entities. It contains,
     * among other things, the logic for calling the CRUD procedures from the
     * {@link QuestionWrapper}. The constructor is private as there should never
     * be created an instance of the QuestionRules class itself.
     * 
     * @author Tonni Hyldgaard, Morten Faarkrog
     * @version 1.0
     * @date 11. December, 2013
     * @see Question
     * @see QuestionWrapper
     */

    private QuestionRules() {
    }

    /**
     * Adds a {@link Question} to the database.
     * 
     * @param question
     *            the question that should be added.
     * @return a Response object with a success status if the Question was
     *         added. Otherwise false.
     */
    public static Response addQuestion(Question question) {
        if (QuestionWrapper.insertIntoQuestion(question)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to insert Question");
    }

    /**
     * Method used for retrieving a single {@link Question} entity with a given
     * questionNo.
     * 
     * @param question
     *            the question entity containing the questionNo one is trying to
     *            retrieve
     * @return if successful, a Response object with a success status and
     *         specific tag from the the DB. Otherwise, a failure response.
     * @see Question
     * @see QuestionWrapper
     */
    public static Response getQuestion(Question question) {
        Question newQuestion = null;
        try {
            newQuestion = QuestionWrapper.getQuestionRowWithQuestionNo(question
                    .getQuestionNo());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (newQuestion != null) {
            return new Response(ResponseStatus.SUCCESS, newQuestion);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    /**
     * @param exercise
     *            entity containing the exerciseNo of the exercise one is
     *            finding questions for.
     * @return a Response object with a success status and all the classes
     *         associated to a specific exercises.
     * @see Exercise
     * @see Question
     * @see QuestionWrapper
     */
    public static Response getQuestionRowsWithExerciseNo(Exercise exercise) {
        ResourceSet questions = new ResourceSet();

        for (Question questionEntity : QuestionWrapper
                .getQuestionRowsWithExerciseNo(exercise.getExerciseNo())) {
            questions.add(questionEntity);
        }

        return new Response(ResponseStatus.SUCCESS, questions);
    }

    /**
     * @param Tag
     *            entity containing the tagNo of the tag one is finding
     *            questions for.
     * @return a Response object with a success status and all the classes
     *         associated to a specific exercises.
     * @see Tag
     * @see TagWrapper
     * @see Question
     * @see QuestionWrapper
     */
    public static Response getQuestionRowsWithTagNo(Tag tag) {
        ResourceSet questions = new ResourceSet();

        for (Question questionEntity : QuestionWrapper
                .getQuestionRowsWithTagNo(tag.getTagNo())) {
            questions.add(questionEntity);
        }

        return new Response(ResponseStatus.SUCCESS, questions);
    }

    /**
     * Retrieves and returns a list with all the {@link Question}s from the DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all questions in the DB. Otherwise, a failure response.
     * @see Response
     * @see Question
     * @see QuestionWrapper
     */
    public static Response getQuestions() {
        ResourceSet newQuestion = new ResourceSet();

        for (Question question : QuestionWrapper.getQuestionRows()) {
            newQuestion.add(question);
        }

        return new Response(ResponseStatus.SUCCESS, newQuestion);
    }

    /**
     * Updates a {@link Question} row in the database.
     * 
     * @param questionEntity
     *            the class that should be updated.
     * @return a Response object with a success status if the Question was
     *         updated. Otherwise false.
     */
    public static Response updateQuestion(Question question) {
        if (QuestionWrapper.updateQuestionRow(question)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update Question");
    }

    /**
     * Deletes a {@link Question} row in the database.
     * 
     * @param question
     *            the question that should be deleted.
     * @return a Response object with a success status if the Question was
     *         deleted. Otherwise false.
     */
    public static Response deleteQuestion(Question question) {
        if (QuestionWrapper.deleteQuestionRow(question.getQuestionNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to delete Question");
    }

}
