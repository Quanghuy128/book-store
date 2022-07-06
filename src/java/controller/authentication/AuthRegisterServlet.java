/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dao.account.EncryptedPassword;
import dao.account.AccountDAO;
import dao.account.InvalidAccountHandling;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AuthRegisterServlet extends HttpServlet {
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
        sitemap = (Map<String, String>) request.getServletContext().getAttribute("SITE_MAP");
        String url = sitemap.get("register");
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String address = request.getParameter("txtAddress");
        String phone_no = request.getParameter("txtPhoneNumber");
        String sex = request.getParameter("txtSex");
        
        boolean checkSuccess = false;
        
        InvalidAccountHandling errors = new InvalidAccountHandling();
        boolean foundErr = false;
        try {
            if (username.trim().length() <= 0) {
                foundErr = true;
                errors.setUsernameLengthError("Username is required not null!");
            }
            if (password.trim().length() < 5) {
                foundErr = true;
                errors.setPasswordLenghtError("Password is required length >= 5");
            } else if (!confirmPassword.equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm password is not matched!");
            }
            if (fullname.trim().length() <= 0) {
                foundErr = true;
                errors.setFullNameLengthError("FullName is required not null!");
            }
            if (address.trim().length() <= 0) {
                foundErr = true;
                errors.setAddressLengthError("Address is required not null!");
            }
            if (phone_no.trim().length() <= 0) {
                foundErr = true;
                errors.setPhoneNumberLengthError("Address is required not null!");
            }
            if (sex.trim().length() <= 0) {
                foundErr = true;
                errors.setSexLengthError("Address is required not null!");
            }
            
            if (foundErr == true) {
                request.setAttribute("ERROR", errors);
            } else {
                AccountDAO dao = new AccountDAO();
                //encrypt password to MD5
                password = EncryptedPassword.toHexString(EncryptedPassword.getSHA(password));
                boolean result = dao.createAccount(username, password, fullname, address, phone_no, sex);
                if (result) {
                    checkSuccess = true;
                    url = sitemap.get("login");
                }
            }
           
        }catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AuthRegisterServlet _ SQL _ " + msg);
            if(msg.contains("duplicate")) {
                errors.setUsernameExisted(username + " existed!!!");
                request.setAttribute("ERROR", errors);
            }
        }catch (NamingException ex) {
            log("AuthRegisterServlet _ Naming _ " + ex.getMessage());
            
        }finally {
            if(checkSuccess) {
                response.sendRedirect(url);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
