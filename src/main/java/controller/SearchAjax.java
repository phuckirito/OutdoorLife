/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gear;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchAjax", urlPatterns = {"/searchajax"})
public class SearchAjax extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String txtSearch = request.getParameter("txt");
        DBContext db = new DBContext();
        GearDAO dao = new GearDAO(db.getConnection());
        List<Gear> list = dao.searchByName(txtSearch);
        PrintWriter out = response.getWriter();
        for (Gear g : list) {
            out.println("<div class=\"card border-e shadow rounded-3 w-100\">");
            out.println("    <img class=\"card-img-top\" src=\"img/" + g.getGearImage() + "\" alt=\"Card image cap\">");
            out.println("    <div class=\"card-body\">");
            out.println("        <h5 class=\"card-title\">" + g.getGearName() + "</h5>");
            out.println("        <h6 class=\"price\">" + g.getGearPrice() + "</h6>");
            out.println("        <h6 class=\"category\">" + g.getGearDecription() + "</h6>");
            out.println("        <div class=\"mt-3 d-flex justify-content-between\">");
            out.println("            <a href=\"cartservlet?id=" + g.getGearId() + "\" class=\"btn btn-primary\">Add to Cart</a>");
            out.println("            <a href=\"ordernow?quantity=1&id=" + g.getGearId() + "\" class=\"btn btn-primary\">Buy Now</a>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchAjax.class.getName()).log(Level.SEVERE, null, ex);
        }
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
