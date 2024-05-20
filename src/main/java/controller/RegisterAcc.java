package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import dao.UserDAO;
import dao.UserDaoImpl;

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
        String errorMessage = null;
        String successMessage = null;

        UserDaoImpl guestDao = new UserDaoImpl();

        if (agreed != null) {
            try {
                // Check if the email already exists
                boolean emailExists = guestDao.emailExists(email);
                if (emailExists==true) {
                    errorMessage = "Email is already registered. Please use a different email.";
                } else {
                    // Proceed with the registration process
                    User guest = new User();
                    guest.setFirstName(firstName);
                    guest.setLastName(lastName);
                    guest.setEmail(email);
                    guest.setPhoneNumber(phone);
                    guest.setPasswordHash(password);

                    boolean status = guestDao.insertGuest(firstName, lastName, phone, email, password);
                    if (status) {
                        successMessage = "Registration successful!";
                    } else {
                        errorMessage = "An error occurred during registration.";
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(RegisterAcc.class.getName()).log(Level.SEVERE, null, ex);
                errorMessage = "An error occurred while checking email.";
            }
        } else {
            errorMessage = "Please accept the Terms of Service.";
        }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("successMessage", successMessage);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

}
