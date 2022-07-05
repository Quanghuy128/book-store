/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelper;

/**
 *
 * @author huy
 */
public class AccountDAO implements Serializable{
    public boolean checkLogin(String username, String password) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Select username "
                            +"From Account "
                            +"Where username = ? and password = ?";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                //4.execute query
                rs = stmt.executeQuery();
                //5.process
                if(rs.next()) {
                    result = true;
                }
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public AccountDTO getUser(String username, String password) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AccountDTO result = null;
        
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Select username, password, fullname, role, address, phone_no, sex "
                            +"From Account "
                            +"Where username = ? and password = ?";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                //4.execute query
                rs = stmt.executeQuery();
                //5.process
                if(rs.next()) {
                    String fullName = rs.getString("fullname");
                    result = new AccountDTO(username,
                                            password,
                                            fullName,
                                            rs.getString("role"),
                                            rs.getString("address"),
                                            rs.getString("phone_no"),
                                            rs.getString("sex"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    private List<AccountDTO> accounts;

    public List<AccountDTO> getAccounts() {
        return accounts;
    }
    
    public void searchAccounts(String searchValue) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AccountDTO result = null;
        
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Select username, password, fullname, role, address, phone_no, sex "
                            +"From Account "
                            +"Where fullname like ?";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, "%" + searchValue + "%");
                //4.execute query
                rs = stmt.executeQuery();
                //5.process
                while(rs.next()) {
                    //e10adc3949ba59abbe56e057f20f883e
                    result = new AccountDTO(rs.getString("username"),
                                            rs.getString("password"),
                                            rs.getString("fullname"),
                                            rs.getString("role"),
                                            rs.getString("address"),
                                            rs.getString("phone_no"),
                                            rs.getString("sex"));
                    if(this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts are 
                    this.accounts.add(result);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public boolean createAccount(String username, String password,String fullname,
                                String address, String phone_no, String sex) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Insert Into Account(username, password, fullname, address, phone_no, sex) "
                            +"Values(?,?,?,?,?,?) ";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, fullname);
                stmt.setString(4, address);
                stmt.setString(5, phone_no);
                stmt.setString(6, sex);
                //4.execute query
                int  affectedRows = stmt.executeUpdate();
                //5.process
                if(affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public boolean updateAccount(String username, String password, String role) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Update Account "
                            +"Set password = ?, role = ? "
                            +"Where username = ?";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, password);
                stmt.setString(2, role);
                stmt.setString(3, username);
                //4.execute query
                int  affectedRows = stmt.executeUpdate();
                //5.process
                if(affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public boolean deleteAccount(String username) 
            throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            //1.make Connection
            connection = DBHelper.makeConnection();
            if(connection!=null) {
                //2.write sql string
                String sql = "Delete From Account "
                            +"Where username = ?";
                //3.create statement
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, username);
                //4.execute query
                int  affectedRows = stmt.executeUpdate();
                //5.process
                if(affectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
}
