package controller;

import dao.CampsiteDAO;
import dao.CampsiteOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CampsiteOrder;
import model.Discount;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "BookingServlet", urlPatterns = {"/bookingservlet"})
public class BookingServlet extends HttpServlet {

    private CampsiteDAO campsiteDAO;
    private CampsiteOrderDAO campsiteOrderDAO;
    private Connection con; // Biến connection

    @Override
    public void init() {
        campsiteDAO = new CampsiteDAO();
        con = (Connection) getServletContext().getAttribute("connection"); // Lấy connection từ ServletContext
        campsiteOrderDAO = new CampsiteOrderDAO(con); // Khởi tạo CampsiteOrderDAO với connection
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String campIdStr = request.getParameter("campId");
        String checkInStr = request.getParameter("CheckIn");
        String checkOutStr = request.getParameter("CheckOut");
        String personQuantity = request.getParameter("Person");
        String discountCode = request.getParameter("DiscountCode");

        if (campIdStr != null && checkInStr != null && checkOutStr != null) {
            int campId = Integer.parseInt(campIdStr);

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date checkInDate = dateFormat.parse(checkInStr);
                Date checkOutDate = dateFormat.parse(checkOutStr);
                int quantity = Integer.parseInt(personQuantity);

                if (campsiteDAO.isDateAvailable(campId, new java.sql.Date(checkInDate.getTime()), new java.sql.Date(checkOutDate.getTime()))) {
                    CampsiteOrder order = new CampsiteOrder();
                    order.setCampsiteId(campId);
                    order.setBooker(currentUser.getId());
                    order.setStartDate(new Timestamp(checkInDate.getTime()));
                    order.setEndDate(new Timestamp(checkOutDate.getTime()));
                    order.setApproveStatus(false);
                    order.setPaymentStatus(false);
                    order.setTimeStamp(new Timestamp(System.currentTimeMillis()));
                    order.setQuantity(quantity);

                    // Kiểm tra và áp dụng mã giảm giá nếu có
                    if (discountCode != null && !discountCode.isEmpty()) {
                        Discount discount = campsiteOrderDAO.getDiscountByCode(discountCode);
                        if (discount != null) {
                            order.setDiscount(discountCode); // Lưu mã giảm giá vào order
                            order.setDiscountApplied(true); // Đánh dấu là đã áp dụng giảm giá
                        }
                    }

                    campsiteDAO.bookCampsite(order); // Sử dụng campsiteOrderDAO để bookCampsite
                    response.sendRedirect("success.jsp");
                } else {
                    request.setAttribute("errorMessage", "Selected dates are not available. Please choose different dates.");
                    request.getRequestDispatcher("failure.jsp").forward(request, response);
                }
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } catch (Exception ex) {
                Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("campsites.jsp");
        }
    }
}
