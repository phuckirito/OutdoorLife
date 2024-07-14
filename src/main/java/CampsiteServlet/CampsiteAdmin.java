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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campsite;
import model.Gear;

/**
 *
 * @author vietn
 */
@WebServlet(name="CampsiteAdmin", urlPatterns={"/campadmin"})
public class CampsiteAdmin extends HttpServlet {

    private CampsiteDAO campdao;

    @Override
    public void init() throws ServletException {
        try {
            campdao = new CampsiteDAO();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            
            int totalItems = campdao.getTotalItem();
            List<Campsite> camps = campdao.getAllCampsite(page, 8);
            
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
            request.setAttribute("itemsPerPage", 8);
            request.setAttribute("camps", camps);
            request.getRequestDispatcher("/crudCampsite.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CampsiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
