package com.easyTeach.server.databaseWrapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.easyTeach.common.entity.User;
import com.easyTeach.server.databaseConnector.ConnectionManager;

/**
 * The UserWrapper is the class responsible for handling all the prepared 
 * CRUD SQL statements for manipulating with the User table residing in 
 * the MBO EasyTeach's database.
 * 
 * @author Morten Faarkrog
 * @version 1.1
 * @see User
 * @date 06. December, 2013
 */

public class UserWrapper {

    private static Connection conn = 
            ConnectionManager.getInstance().getConnection();
    
    /**
     * Inserts a new User row into the User table within the easyTeach 
     * database. The prepared statement needs the user's email, userType,
     * firstName, lastName, password and dateAdded.
     * 
     * @param userEntity is an instance of the class User
     * @return true if the userEntity is successfully inserted into the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean insertIntoUser(User userEntity) {
        String sql = "{call insertIntoUser(?,?,?,?,?)}";

        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userEntity.getEmail());
            stmt.setString(2, userEntity.getUserType());
            stmt.setString(3, userEntity.getFirstName());
            stmt.setString(4, userEntity.getLastName());
            stmt.setString(5, userEntity.getPassword());
            
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
     * Updates an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the user's uesrNo, email, 
     * userType, firstName, lastName and password.
     * 
     * @param userEntity is an instance of the class User
     * @return true if the User row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean updateUserRow(User userEntity) {
        String sql = "{call updateUserRow(?,?,?,?,?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userEntity.getUserNo());
            stmt.setString(2, userEntity.getUserType());
            stmt.setString(3, userEntity.getEmail());
            stmt.setString(4, userEntity.getFirstName());
            stmt.setString(5, userEntity.getLastName());
            stmt.setString(6, userEntity.getPassword());
            
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
     * Updates an existing User's password in the User table within the 
     * easyTeach database. The prepared statement needs the user's userNo
     * and the new password.
     * 
     * @param userNo is the primary key of the User table;
     * @param password is the new password 
     * @return true if the User row is successfully updated in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean updateUserPassword(String userNo, String password) {
        String sql = "{call updatePassword(?,?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            stmt.setString(2, password);
            
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
     * Deletes an existing User row in the User table within the easyTeach
     * database. The prepared statement needs the User's userNo.
     * 
     * @param userNo is the primary key of the User table.
     * @return true if the User row is successfully deleted in the
     * easyTeach database, otherwise false.
     * @see User
     */
    public static boolean deleteuserRow(String userNo) {
        String sql = "{call deleteUserRow(?)}";
        
        try (
                CallableStatement stmt = conn.prepareCall(sql);
                ) {
            stmt.setString(1, userNo);
            
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
     * Returns all the rows from the database's User table in the form of a 
     * HashSet containing User entities.   
     * 
     * @return a HashSet with all the rows in the User table from the
     * easyTeach database. The rows are converted into User entities.
     * @see User
     */
    public static HashSet<User> getUserRows() {
        String sql = "{call selectUserRows()}";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                ){

            HashSet<User> hashSet = new HashSet<User>();
            
            while (rs.next()) {
                User userEntity = new User();
                userEntity.setUserNo(rs.getString("userNo"));
                userEntity.setEmail(rs.getString("email"));
                userEntity.setUserType(rs.getString("userType"));
                userEntity.setFirstName(rs.getString("firstName"));
                userEntity.setLastName(rs.getString("lastName"));
                userEntity.setPassword(rs.getString("password"));
                userEntity.setDateAdded(rs.getDate("dateAdded"));
                
                hashSet.add(userEntity);
            }
            return hashSet;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
    
    /**
     * Returns a row from the database's User table with a specific userNo.
     * 
     * @param userNo is the primary key of the User table.
     * @return An instance of User
     * @see User
     */
    public static User getUserRowWithUserNo(String userNo) {
        String sql = "{call selectUserRowWithUserNo(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, userNo);
            rs = stmt.executeQuery();
            rs.next();
            
            User userEntity = new User();
            userEntity.setUserNo(rs.getString("userNo"));
            userEntity.setEmail(rs.getString("email"));
            userEntity.setUserType(rs.getString("userType"));
            userEntity.setFirstName(rs.getString("firstName"));
            userEntity.setLastName(rs.getString("lastName"));
            userEntity.setPassword(rs.getString("password"));
            userEntity.setDateAdded(rs.getDate("dateAdded"));
            
            return userEntity;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e);                
            }
        }
    }
    
    /**
     * Returns a row from the database's User table with a specific email.
     * 
     * @author Oliver Nielsen
     * 
     * @param email The email of a specific user
     * @return An instance of User with the specific email
     * @see User
     */
    public static User getUserRowWithEmail(String email) {
        String sql = "{call selectUserRowWithEmail(?)}";
        ResultSet rs = null;
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {   
	            User userEntity = new User();
	            userEntity.setUserNo(rs.getString("userNo"));
	            userEntity.setEmail(rs.getString("email"));
	            userEntity.setUserType(rs.getString("userType"));
	            userEntity.setFirstName(rs.getString("firstName"));
	            userEntity.setLastName(rs.getString("lastName"));
	            userEntity.setPassword(rs.getString("password"));
	            userEntity.setDateAdded(rs.getDate("dateAdded"));
            
	            return userEntity;
            }
           
            return null;
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e);                
            }
        }
    }
    
}
