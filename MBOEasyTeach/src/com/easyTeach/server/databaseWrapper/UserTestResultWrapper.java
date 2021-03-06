package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.HashSet;

import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The UserTestResultWrapper is the class responsible for handling all the
 * prepared CRUD SQL statements for manipulating with the UserTestResult table
 * residing in MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @see UserTestResult
 * @date 30. November, 2013
 */

public class UserTestResultWrapper {

    /**
     * Inserts a new UserTestResult row into the UserTestResult table within the
     * easyTeach database. The prepared statement needs the userTestResult's
     * userNo, exerciseNo and score.
     * 
     * @param userTestResultEntity
     *            is an instance of the class UserTestResult.
     * @return true if the userTestResultEntity is successfully inserted into
     *         the easyTeach database, otherwise false.
     * @see UserTestResult
     */
    public static boolean insertIntoUserTestResult(
            UserTestResult userTestResultEntity) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        String sql = "{call insertIntoUserTestResult(?,?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userTestResultEntity.getUserNo());
            stmt.setString(2, userTestResultEntity.getExerciseNo());
            stmt.setInt(3, userTestResultEntity.getScore());

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return insertIntoUserTestResult(userTestResultEntity);
        } catch (SQLTransientException SQLte) {
            return insertIntoUserTestResult(userTestResultEntity);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes an existing UserTestResult row in the UserTestResult table within
     * the easyTeach database. The prepared statement needs the userTestResult's
     * userNo and exerciseNo.
     * 
     * @param userNo
     *            is part of the primary key of the UserTestResult table.
     * @param exerciseNo
     *            is part of the primary key of the UserTestResult table.
     * @return true if the UserTestResult row is successfully deleted in the
     *         easyTeach database, otherwise false.
     * @see UserTestResult
     */
    public static boolean deleteUserTestResultRow(String userNo,
            String exerciseNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        String sql = "{call deleteUserTestResultRow(?,?)}";

        try (CallableStatement stmt = conn.prepareCall(sql);) {
            stmt.setString(1, userNo);
            stmt.setString(2, exerciseNo);

            stmt.execute();
            return true;

        } catch (SQLTransientConnectionException SQLtce) {
            return deleteUserTestResultRow(userNo, exerciseNo);
        } catch (SQLTransientException SQLte) {
            return deleteUserTestResultRow(userNo, exerciseNo);
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns all the rows from the database's UserTestResult table in the form
     * of a HashSet containing UserTestResult entities.
     * 
     * @return a HashSet with all the rows in the UserTestResult table from the
     *         easyTeach database. The rows are converted into UserTestResult
     *         entities.
     * @see UserTestResult
     */
    public static HashSet<UserTestResult> getUserTestResultRows() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectUserTestResultRows()}";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();) {

            HashSet<UserTestResult> hashSet = new HashSet<UserTestResult>();

            while (rs.next()) {
                UserTestResult userTestResultEntity = new UserTestResult();
                userTestResultEntity.setUserNo(rs.getString("userNo"));
                userTestResultEntity.setExerciseNo(rs.getString("exerciseNo"));
                userTestResultEntity.setScore(rs.getInt("score"));

                hashSet.add(userTestResultEntity);
            }
            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserTestResultRows();
        } catch (SQLTransientException SQLte) {
            return getUserTestResultRows();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns all the rows from the database's UserTestResult table with a
     * specific userNo in the form of a HashSet containing UserTestResult
     * entities.
     * 
     * @param userNo
     *            is part of the primary key of the UserTestResult table
     * @return a HashSet with all the matching rows in the UserTestResult table
     *         from the easyTeach database. The rows are converted into
     *         UserTestResult entities.
     * @see UserTestResult
     */
    public static HashSet<UserTestResult> getUserTestResultRowsWithUserNo(
            String userNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectUserTestResultRowsWithUserNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, userNo);
            rs = stmt.executeQuery();

            HashSet<UserTestResult> hashSet = new HashSet<UserTestResult>();

            while (rs.next()) {
                UserTestResult userTestResultEntity = new UserTestResult();
                userTestResultEntity.setUserNo(rs.getString("userNo"));
                userTestResultEntity.setExerciseNo(rs.getString("exerciseNo"));
                userTestResultEntity.setScore(rs.getInt("score"));

                hashSet.add(userTestResultEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserTestResultRowsWithUserNo(userNo);
        } catch (SQLTransientException SQLte) {
            return getUserTestResultRowsWithUserNo(userNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Returns all the rows from the database's UserTestResult table with a
     * specific exerciseNo in the form of a HashSet containing UserTestResult
     * entities.
     * 
     * @param exerciseNo
     *            is part of the primary key of the UserTestResult table
     * @return a HashSet with all the matching rows in the UserTestResult table
     *         from the easyTeach database. The rows are converted into
     *         UserTestResult entities.
     * @see UserTestResult
     */
    public static HashSet<UserTestResult> getUserTestResultRowsWithExerciseNo(
            String exerciseNo) {
        Connection conn = ConnectionManager.getInstance().getConnection();
        
        String sql = "{call selectUserTestResultRowsWithExerciseNo(?)}";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, exerciseNo);
            rs = stmt.executeQuery();

            HashSet<UserTestResult> hashSet = new HashSet<UserTestResult>();

            while (rs.next()) {
                UserTestResult userTestResultEntity = new UserTestResult();
                userTestResultEntity.setUserNo(rs.getString("userNo"));
                userTestResultEntity.setExerciseNo(rs.getString("exerciseNo"));
                userTestResultEntity.setScore(rs.getInt("score"));

                hashSet.add(userTestResultEntity);
            }

            return hashSet;

        } catch (SQLTransientConnectionException SQLtce) {
            return getUserTestResultRowsWithExerciseNo(exerciseNo);
        } catch (SQLTransientException SQLte) {
            return getUserTestResultRowsWithExerciseNo(exerciseNo);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}