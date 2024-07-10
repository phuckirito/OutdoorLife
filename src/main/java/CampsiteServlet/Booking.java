package controller;

import dao.CampsiteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campsite;

@WebServlet(name = "Booking", urlPatterns = {"/booking"})
public class Booking extends HttpServlet {

    private CampsiteDAO campsiteDAO;

    @Override
    public void init() {
        campsiteDAO = new CampsiteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String campId = request.getParameter("campId");
        if (campId != null) {
            int id = Integer.parseInt(campId);
            Campsite campsite = campsiteDAO.getSingleCampsite(id);
            request.setAttribute("campsite", campsite);
            request.getRequestDispatcher("form_book.jsp").forward(request, response);
        } else {
            response.sendRedirect("campsites");
        }
    }
}
