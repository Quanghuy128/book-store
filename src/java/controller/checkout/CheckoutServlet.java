/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.checkout;

import cart.CartObject;
import checkout.CheckoutError;
import checkout.order_detail.OrderedDetailDAO;
import checkout.ordered.OrderedDAO;
import dao.product.ProductDAO;
import dao.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
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
public class CheckoutServlet extends HttpServlet {
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
        
        //get site map
        sitemap = (Map<String, String>) request.getServletContext().getAttribute("SITE_MAP");
        String url = sitemap.get("checkout");
        //get request parameters
        String cusName = request.getParameter("txtCusName");
        String cusAddress = request.getParameter("txtCusAddress");
        String phone_no = request.getParameter("txtCusPhone_no");
        String total = request.getParameter("total_price");
        //intialize Error object
        CheckoutError errors = new CheckoutError();
        boolean isError = false;

        try {
            //validate
            if (cusName.trim().length() <= 0) {
                errors.setCustNameIsNullError("Name cannot be blank!");
                isError = true;
            }//end if cusName is not null
            if (cusAddress.trim().length() <= 0) {
                errors.setAddressIsNullError("Address cannot be blank!");
                isError = true;
            }//end if cusAddress is not null
            if (phone_no.trim().length() <= 0) {
                errors.setAddressIsNullError("Phone Number cannot be blank!");
                isError = true;
            }
            
            //check if error existed
            if (isError) {
                request.setAttribute("ERROR", errors);
            } else {
                // 1.Goes to cart place
                HttpSession session = request.getSession(false);
                if (session != null) {
                    //2.Take customer's cart
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        Map<String, Integer> items = cart.getItems();
                        if (items != null) {
                            //3.Create order
                            OrderedDAO orderDAO = new OrderedDAO();
                            int orderId = orderDAO.addOrder(cusName, 
                                                            new Timestamp(System.currentTimeMillis()),  
                                                            cusAddress,
                                                            phone_no, 
                                                            Double.parseDouble(total));
                            //4.Get each item and add to order
                            ProductDAO productDAO = new ProductDAO();
                            ProductDTO dto = null;
                            OrderedDetailDAO detailDAO = new OrderedDetailDAO();
                            for (String key : items.keySet()) {
                                dto = productDAO.getItem(key);
                                detailDAO.addOrderDetail(items.get(key),
                                                        items.get(key) * dto.getPrice(),
                                                        key,
                                                        orderId);
                            }//end for items.keySet
                        }//end if items is not null
                        session.removeAttribute("CART");
                    }//end if cart is not null
                }//end if session is not null
            }//end else isError == false

        } catch (SQLException ex) {
            log("CheckoutServlet _ SQL _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutServlet _ Naming _ " + ex.getMessage());
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
