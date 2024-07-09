package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;
import model.Cart;

@WebServlet(name = "QuantityIncDecServlet", urlPatterns = {"/quantityset"})
public class QuantityIncDecServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

        if (cart_list == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"message\": \"Cart list is empty or not initialized.\"}");
            return;
        }

        boolean itemFound = false;
        if (action != null && id >= 1) {
            if (action.equals("inc")) {
                for (Cart c : cart_list) {
                    if (c.getGearId() == id) {
                        int quantity = c.getQuantity();
                        quantity++;
                        c.setQuantity(quantity);
                        
                        itemFound = true;
                        break;
                    }
                }
            } else if (action.equals("dec")) {
                for (Cart c : cart_list) {
                    if (c.getGearId() == id && c.getQuantity() > 1) {
                        int quantity = c.getQuantity();
                        quantity--;
                        c.setQuantity(quantity);
                       
                        itemFound = true;
                        break;
                    }
                }
            }

            if (itemFound) {
                request.getSession().setAttribute("cart-list", cart_list); // Ensure session is updated
                response.setStatus(HttpServletResponse.SC_OK);
                double total = calculateTotal(cart_list);
                ArrayList<CartInfo> cartInfoList = new ArrayList<>();
                for (Cart c : cart_list) {
                    cartInfoList.add(new CartInfo(c.getGearId(), c.getQuantity() * c.getGearPrice()));
                }
                Gson gson = new Gson();
                String json = gson.toJson(new Response(total, cartInfoList));
                out.println(json);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("{\"message\": \"Item not found or invalid action.\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"message\": \"Invalid action or ID.\"}");
        }
    }

    private double calculateTotal(ArrayList<Cart> cartList) {
        double total = 0;
        for (Cart cart : cartList) {
            total += cart.getQuantity() * cart.getGearPrice();
        }
        return total;
    }

    private class Response {
        double total;
        ArrayList<CartInfo> cartInfoList;

        public Response(double total, ArrayList<CartInfo> cartInfoList) {
            this.total = total;
            this.cartInfoList = cartInfoList;
        }

        public double getTotal() {
            return total;
        }

        public ArrayList<CartInfo> getCartInfoList() {
            return cartInfoList;
        }
    }

    private class CartInfo {
        int gearId;
        double price;

        public CartInfo(int gearId, double price) {
            this.gearId = gearId;
            this.price = price;
        }

        public int getGearId() {
            return gearId;
        }

        public double getPrice() {
            return price;
        }
    }
}
