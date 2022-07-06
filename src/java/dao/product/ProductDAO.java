
package dao.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBHelper;

/**
 *
 * @author huy
 */
public class ProductDAO implements Serializable{
    List<ProductDTO> items = null;

    public List<ProductDTO> getItems() {
        return items;
    }

    
    public void getProducts() throws NamingException, SQLException{
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ProductDTO result = null;
        
        try{
            //1.make connection
            con = DBHelper.makeConnection();
            if(con!=null) {
                //2.write sql string
                String sql = "Select product_id, title, author, public_date, price, available_quantity "
                            +"From Product";
                //3.create statement
                stmt = con.createStatement();
                //4.execute query
                rs = stmt.executeQuery(sql);
                //5.process
                while(rs.next()){
                    result = new ProductDTO(rs.getString("product_id"),
                                            rs.getString("title"),
                                            rs.getString("author"),
                                            rs.getDate("public_date"),
                                            rs.getBigDecimal("price").doubleValue(),
                                            rs.getInt("available_quantity"));
                    if(items==null) {
                        items = new ArrayList<>();
                    }
                    items.add(result);
                }
            }
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public ProductDTO getItem(String id) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductDTO result = null;
        
        try{
            //1.make connection
            con = DBHelper.makeConnection();
            if(con!=null) {
                //2.write sql string
                String sql = "Select product_id, title, author, public_date, price, available_quantity "
                            +"From Product "
                            +"Where product_id = ?";
                
                //3.create statement
                stmt = con.prepareStatement(sql);
                stmt.setString(1, id);
                //4.execute query
                rs = stmt.executeQuery();
                //5.process
                if(rs.next()){
                    result = new ProductDTO(id,
                                            rs.getString("title"),
                                            rs.getString("author"),
                                            rs.getDate("public_date"),
                                            rs.getBigDecimal("price").doubleValue(),
                                            rs.getInt("available_quantity"));
                }
            }
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
}
