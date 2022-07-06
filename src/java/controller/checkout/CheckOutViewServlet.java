/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.checkout;

import cart.CartObject;
import dao.product.ProductDAO;
import dao.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
public class CheckOutViewServlet extends HttpServlet {
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
        String url = sitemap.get("checkout");
        Map<ProductDTO,Integer> map = new HashMap<>();
        try {
            ProductDTO dto = null;
            //get product
            ProductDAO dao = new ProductDAO();
            //get cart obj
            CartObject cart = (CartObject)request.getSession().getAttribute("CART");
            
            double total = 0;
            if(cart != null) {
                Map<String,Integer> items = (Map<String,Integer>)cart.getItems();
                if(items!=null) {
                    for (String key : items.keySet()) {
                        dto = dao.getItem(key);
                        if(dto != null) {
                            map.put(dto, items.get(key));
                            total += (dto.getPrice() * items.get(key));
                        }
                    }   
                }
            }
            request.setAttribute("ITEMS_IN_CHECKOUT", map);
            request.setAttribute("TOTAL_PRICE", total);
        }catch(SQLException ex){
            log("CheckOutViewServlet _ SQL _ " + ex.getMessage());
        }catch(NamingException ex){
            log("CheckOutViewServlet _ Naming _ " + ex.getMessage());
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
