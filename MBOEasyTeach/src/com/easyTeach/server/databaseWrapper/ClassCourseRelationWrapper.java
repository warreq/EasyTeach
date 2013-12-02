package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.easyTeach.server.databaseConnector.ConnectionManager;
import com.easyTeach.server.entity.ClassCourseRelation;

/**
 * The ClassCourseRelationWrapper is the class responsible for handling 
 * all the prepared CRUD SQL statements for manipulating with the
 * ClassCourseRelation table residing in the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see ClassCourseRelation
 * @date 1. December, 2013
 */

public class ClassCourseRelationWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new ClassCourseRelation row into the ClassCourseRelation table 
     * within the easyTeach database. The prepared statement needs the 
     * classCourseRelation's classNo and courseNo.
     * 
     * @param classCourseRelationEntity is an instance of the class 
     * ClassCourseRelation.
     * @return true if the classCourseRelationEntity is successfully inserted 
     * into the easyTeach database, otherwise false.
     * @see ClassCourseRelation
     */
    public static boolean insertIntoClassCourseRelation(ClassCourseRelation classCourseRelationEntity) {
        String sql = "{call insertIntoClassCourseRelation(?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, classCourseRelationEntity.getClassNo());
            stmt.setString(2, classCourseRelationEntity.getCourseNo());
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
    /**
     * Deletes an existing ClassCourseRelation row in the ClassCourseRelation 
     * table within the easyTeach database. The prepared statement needs 
     * the classCourseRelation's classNo and courseNo.  
     * 
     * @param classNo is part of the primary key of the ClassCourseRelation 
     * table.
     * @param courseNo is part of the primary key of the ClassCourseRelation 
     * table.
     * @return true if the ClassCourseRelation row is successfully deleted in 
     * the easyTeach database, otherwise false.
     * @see ClassCourseRelation
     */
    public static boolean deleteClassCourseRelationRow(String classNo, String courseNo) {
        String sql = "{call deleteClassCourseRelationRow(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, classNo);
            stmt.setString(2, courseNo);
            
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    
}