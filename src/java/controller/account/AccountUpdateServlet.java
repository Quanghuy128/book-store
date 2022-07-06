/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import dao.account.AccountDAO;
import dao.account.EncryptedPassword;
import dao.account.InvalidAccountHandling;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huy
 */
public class AccountUpdateServlet extends HttpServlet {
    Map<String, String> sitemap;
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
        sitemap = (Map< String, String>) request.getServletContext().getAttribute("SITE_MAP");
        
        //parameter
        String username = request.getParameter("username");
        String new_password = request.getParameter("new_password");
        String role = request.getParameter("role");
        String searchValue = request.getParameter("lastSearchValue");
        //url
        String url = sitemap.get("searchAction") + "?search_value=" + searchValue;
        try {
            AccountDAO dao = new AccountDAO();
            //encrypt password
            new_password = EncryptedPassword.toHexString(EncryptedPassword.getSHA(new_password));
            boolean result = dao.updateAccount(username, new_password, role);
            if (!result) {
                request.setAttribute("UPDATE_USER_INVALID", username);
            }else {
                request.setAttribute("UPDATE_USER_SUCCESS", username);
            }
        }catch(SQLException ex){
            log("AccountUpdateServlet _ SQL _ " + ex.getMessage());
        }catch(NamingException ex){
            log("AccountUpdateServlet _ Naming _ " + ex.getMessage());
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
