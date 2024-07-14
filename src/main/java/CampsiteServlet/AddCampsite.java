/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package CampsiteServlet;

import dao.CampsiteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author vietn
 */
@WebServlet(name="AddCampsite", urlPatterns={"/addcamp"})
public class AddCampsite extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String price = request.getParameter("campPrice");
        String name = request.getParameter("campName");
        String address = request.getParameter("campAddress");
        String description = request.getParameter("campDescription");
        String image = request.getParameter("campImage");
        String limit = request.getParameter("campLimit");
        
        try {
            CampsiteDAO camp = new CampsiteDAO();
            camp.insertCampsite(price, name, address, description, image, limit);
        } catch(Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("campadmin");
    }

}
