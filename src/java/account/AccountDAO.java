/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

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
}
