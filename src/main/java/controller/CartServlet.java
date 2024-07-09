/*2
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Cart;

@WebServlet(name="CartServlet", urlPatterns={"/cartservlet"})
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){
            ArrayList<Cart> cartList = new ArrayList();
            int id = Integer.parseInt(request.getParameter("id"));
            Cart c = new Cart();
            c.setQuantity(1);
            c.setGearId(id);
            
            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list =(ArrayList<Cart>) session.getAttribute("cart-list");
            if(cart_list == null){
                cartList.add(c);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("viewgear.jsp");
      
            }else {
                cartList = cart_list;
                boolean exist = false;
                for (Cart cm : cart_list){
                    if(cm.getGearId()== id){
                        exist = true;
                        response.sendRedirect("viewgear.jsp");                       
                    }
                                    
                }   
                if(!exist){
                        cartList.add(c);
                        response.sendRedirect("viewgear.jsp");
                    }  
            }
            
            
        }
        
    } 
}
