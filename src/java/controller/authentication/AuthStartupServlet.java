/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dao.account.AccountDAO;
import dao.account.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huy
 */
public class AuthStartupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "login_page";  
        try {
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                //read information
                String username = null;
                String password = null;
                for (int i=cookies.length-1; i >= 0;i--) {
                    if(!cookies[i].getName().equals("JSESSIONID")) {
                        username = cookies[i].getName();    
                        password = cookies[i].getValue();
                    }
                }
                
                //call Dao
                AccountDAO dao = new AccountDAO();
                AccountDTO result = dao.getUser(username, password);

                if(result!=null) {
                    if(result.getRole().equalsIgnoreCase("Admin")){
                        url = "search_page";
                    }else{
                        url = "shopping_page";
                    }
                    request.getSession(true).setAttribute("USER", result);
                }
            }//end cookies has existed
        } catch (SQLException ex) {
            log("AuthStartupController _ SQL _" + ex.getMessage());
        } catch (NamingException ex) {
            log("AuthStartupController _ Naming _" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
