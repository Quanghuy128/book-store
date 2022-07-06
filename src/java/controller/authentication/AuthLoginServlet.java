/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dao.account.AccountDAO;
import dao.account.AccountDTO;
import dao.account.EncryptedPassword;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huy
 */
public class AuthLoginServlet extends HttpServlet {
     Map<String, String> siteMap;
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
        siteMap = (Map<String, String>) request.getServletContext().getAttribute("SITE_MAP");
        
        String url = siteMap.get("login");
        String username = request.getParameter("login_username");
        String password = request.getParameter("login_password");
        boolean errorFound = false;
        try {
            //encrypt password to MD5
            password = EncryptedPassword.toHexString(EncryptedPassword.getSHA(password));
            //1.call DAO
            AccountDAO dao = new AccountDAO();
            AccountDTO result = dao.getUser(username, password);
            //2.process
            if(result != null) {
                url = siteMap.get("search");
                
                // cookie
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 3);
                response.addCookie(cookie);
                
                // session
                request.getSession().setAttribute("USER", result);
            }else {
                request.setAttribute("INVALID_MSG", "Invalid username or password!!!");
                errorFound = true;
            }
        }catch (SQLException ex) {
            log("AuthLoginServlet _ SQL _ " + ex.getMessage());
        }catch (NamingException ex) {
            log("AuthLoginServlet _ Naming _ " + ex.getMessage());
        }finally {
            if(errorFound){
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else {
                response.sendRedirect(url);
            }
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
