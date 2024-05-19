package controller;

import dao.userDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user;
import dao.userDao;

@WebServlet(name = "RegisterAcc", urlPatterns = {"/registeracc"})
public class RegisterAcc extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterAcc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterAcc at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String agreed = request.getParameter("agreed");
        System.out.println(firstName+" "+lastName);
        String errorMessage = null;
        String successMessage = null;

        if (agreed != null) {
            // Tiếp tục với quá trình đăng ký
            user guest = new user();
            guest.setFirstName(firstName);
            guest.setLastName(lastName);
            guest.setEmail(email);
            guest.setPhoneNumber(phone);
            guest.setPasswordHash(password);
            userDaoImpl guestDao = new userDaoImpl();
            try {
                System.out.println(guestDao.getConnection());
            } catch (Exception ex) {
                Logger.getLogger(RegisterAcc.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean status = guestDao.insertGuest(firstName, lastName, phone, email, password);
            if (status) {
                successMessage = "Successful Register!";
            } else {
                errorMessage = "Error";
            }
        } else {
            errorMessage = "Please accept Terms of Service";
        }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("successMessage", successMessage);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

}
