package controller;

import VnpayService.VnpayService;
import dao.DBContext;
import dao.GearDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CampsiteOrder;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.User;

@WebServlet(name = "PartialCheckoutServlet", urlPatterns = {"/partialCheckout"})
public class PartialCheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String priceStr = request.getParameter("price");
        System.out.println(priceStr);
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PartialCheckoutServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PartialCheckoutServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            DBContext db = new DBContext();
            OrderDAO oDao = new OrderDAO(db.getConnection());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp now = new Timestamp(System.currentTimeMillis());

            String[] selectedItems = request.getParameterValues("selectedItems");
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            ArrayList<Cart> cart_list_selected = new ArrayList<>();
            User auth = (User) request.getSession().getAttribute("currentUser");
             CampsiteOrder campsiteOrder = (CampsiteOrder) session.getAttribute("CampsiteOrder");
            int total = 0;
            if (auth != null && campsiteOrder != null) {
                if(selectedItems != null && cart_list != null){
                for (String itemId : selectedItems) {
                    int gearId = Integer.parseInt(itemId);
                    for (Cart c : cart_list) {
                        if (c.getGearId() == gearId) {
                            cart_list_selected.add(c);
//                            Order order = new Order();
//                            order.setGearId(c.getGearId());
//                            order.setBooker(auth.getId());
//                            order.setTimeStamp(now);
//                            order.setQuantity(c.getQuantity());
//
//                            DBContext db = new DBContext();
//                            OrderDAO oDao = new OrderDAO(db.getConnection());
//                            boolean result = oDao.insertOrder(order);
//                            if (!result) {
//                                break;
//                            }
                        }
                    }
                }
                }
//                cart_list.clear();
//                response.sendRedirect("orders.jsp");
                
                session.setAttribute("cart_list_selected", cart_list_selected);
                DBContext dbContext = new DBContext();
                GearDAO pDao = new GearDAO(dbContext.getConnection());
                total = pDao.getTotalCartPrice(cart_list_selected);

                String paymentMethod = request.getParameter("paymentMethod");
                if (paymentMethod.equals("VNPay")) {
                    //Option1 // go to vnpay
                    String url = VnpayService.paymentUrl(request, (long) (total + campsiteOrder.getTotalAmountBooking()));
                    response.sendRedirect(url);
                } else {
                    //Option2//order
                    //create order
                    Order order = new Order(auth.getId(), campsiteOrder.getCampsiteId(), campsiteOrder.getStartDate(), campsiteOrder.getEndDate(), false, false, campsiteOrder.getQuantity(),
                            total + campsiteOrder.getTotalAmountBooking(), campsiteOrder.getTotalAmountBooking());
                    int orderId = oDao.insertOrder2(order);
                    //create order detail
                    for (Cart cart : cart_list_selected) {
                        OrderDetail od = new OrderDetail(orderId, cart.getQuantity(), cart.getGearId(), cart.getGearPrice());
                        orderDetailDAO.insertOrderDetail(od);
                    }

                    //clear cart
                    if(cart_list != null ){
                    cart_list.removeAll(cart_list_selected);
                    session.setAttribute("cart_list", cart_list);
                    }
                    
                    session.removeAttribute("cart_list_selected");
                    response.sendRedirect("orderscamp.jsp");
                }
            } else {
                if (auth == null) {
                    response.sendRedirect("login1.jsp");
                } else {
                    response.sendRedirect("Cart.jsp");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PartialCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Partial checkout for selected items";
    }
}
