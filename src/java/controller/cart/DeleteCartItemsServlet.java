/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import cart.CartObject;
import checkout.order_detail.OrderedDetailDAO;
import checkout.ordered.OrderedDAO;
import dao.product.ProductDAO;
import dao.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huy
 */
public class DeleteCartItemsServlet extends HttpServlet {
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
        String url = sitemap.get("CartViewAction");
        String[] selectedItems = request.getParameterValues("chkItem");
        
        try {
            HttpSession session = request.getSession(false);
                if (session != null) {
                    //2.Take customer's cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            //3.Create order
                                for (String key : selectedItems) {
                                    cart.removeBookFromCart(key);
                                }
                                session.setAttribute("CART", cart);
                        }//end if items is not null
                    }//end if cart is not null
            }//end if session is not null
        }finally{
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
