/*
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
import model.CampsiteOrder;

/**
 *
 * @author vietn
 */
@WebServlet(name="PaymentServlet", urlPatterns={"/payment"})
public class PaymentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            ArrayList<CampsiteOrder> campCartList = new ArrayList();
            int id = Integer.parseInt(request.getParameter("id"));
            CampsiteOrder c = new CampsiteOrder();
            c.setCampId(id);

            HttpSession session = request.getSession();
            ArrayList<CampsiteOrder> cart_list = (ArrayList<CampsiteOrder>) session.getAttribute("campsite-cart-list");
            if (cart_list == null) {
                campCartList.add(c);
                session.setAttribute("campsite-cart-list", campCartList);
            } else {
                campCartList = cart_list;
                boolean exist = false;
                for (CampsiteOrder cm : cart_list) {
                    if (cm.getCampId() == id) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    campCartList.add(c);
                }
            }

            // Lấy giá trị của tham số redirectPage
            String redirectPage = request.getParameter("redirectPage");
            if (redirectPage == null || redirectPage.isEmpty()) {
                // Đặt giá trị mặc định nếu cần thiết
                redirectPage = "viewgear.jsp";
            }
            response.sendRedirect(redirectPage);
        }
    } 
}
