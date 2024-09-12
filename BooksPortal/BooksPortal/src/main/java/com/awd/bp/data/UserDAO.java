package com.awd.bp.data;


import com.awd.bp.beans.User;

import java.sql.*;

public class UserDAO {

    public boolean validate(String email, String password) {
    	
    	String databaseURL="jdbc:mysql://localhost:3306/book";
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        try {

        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection(databaseURL, "root", "");
        		
        	System.out.println("connected to.."+databaseURL);

           /* Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/AWD2002");

            conn = dataSource.getConnection();*/

            pst = conn.prepareStatement("select * from User_Table where Email=? and Password=?");
            pst.setString(1, email);
            pst.setString(2, password);

            rs = pst.executeQuery();
            
            
            return rs.next();



        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    public static boolean addUser(User user) {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?" + "user=root&password= ");
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(4, user.getType());


            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public User getUser(String email){

        User userInstance = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {

        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?" + "user=root&password= ");

            String sql = "Select * From User_Table where Email=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            resultset = statement.executeQuery();

            if(resultset.next()) {
                int userId = resultset.getInt("userID");
                String firstname = resultset.getString("FirstName");
                String lastname = resultset.getString("LastName");
                String emailAddress = resultset.getString("Email");
                String password = resultset.getString("Password");
                int userType = resultset.getInt("Type");

                userInstance = new User(userId,firstname,lastname,emailAddress,password, userType);

            }

            return userInstance;

        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


