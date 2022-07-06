/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkout.ordered;

import utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author huy
 */
public class OrderedDAO implements Serializable{
     public int addOrder(String cusName, Timestamp time, String cusAddrress, String cusPhone_no, Double total) 
        throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 1. Connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2. Create SQL String
                String sql = "INSERT INTO Ordered (order_date, total, customer_name, customer_address, customer_phone) "
                        + "VALUES (?, ?, ?, ?, ?)";
                // 3. Create Statement & Assign Paramaters' values
                stmt = con.prepareStatement(sql);
                stmt.setTimestamp(1, time);
                stmt.setDouble(2, total);
                stmt.setString(3, cusName);
                stmt.setString(4, cusAddrress);
                stmt.setString(5, cusPhone_no);
                // 4. Execute Query
                int row = stmt.executeUpdate();
                
                // 5. Process rs
                if (row > 0) {
                    //get order id references to Order_Detail
                    sql = "SELECT TOP 1 order_id FROM Ordered ORDER BY order_id DESC ";
                    stmt = con.prepareStatement(sql);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        return rs.getInt("order_id");
                    }
                }
            } // EndIf con is connected
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con!= null) {
                con.close();
            }
        }
        return 0;
    }
}
