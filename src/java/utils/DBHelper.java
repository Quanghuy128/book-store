/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author huy
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() throws NamingException, SQLException {
        //get initial servlet
        Context context = new InitialContext();
        //1 get tomcat path
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //2. get resource
        DataSource datasource = (DataSource) tomcatContext.lookup("BookStoreDB_Connection");
        //3. open connection
        Connection connection = datasource.getConnection();
        return connection;
    }
}
