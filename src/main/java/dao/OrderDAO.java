package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;
import model.*;

public class OrderDAO {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDAO(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order order) {
        boolean result = false;
        try {
            String sql = "INSERT INTO ORDERS (Book_id, TimeStamp, Booker, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setInt(1, order.getGearId());
            pst.setTimestamp(2, order.getTimeStamp());
            pst.setInt(3, order.getBooker());
            pst.setInt(4, order.getQuantity());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Booker = ? AND Book_id <> 0 ORDER BY Orders_id DESC");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                int pId = rs.getInt("Book_id");
                GearDAO gearDAO = new GearDAO(this.con);
                Gear gear = gearDAO.getSingleGear(pId);
                order.setOrdersId(rs.getInt("Orders_id"));
                order.setGearId(pId);
                order.setGearName(gear.getGearName());
                order.setGearDecription(gear.getGearDecription());
                order.setGearPrice(gear.getGearPrice() * rs.getInt("Quantity"));
                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setQuantity(rs.getInt("Quantity")); 
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void createOrder(int bookerId, int gearId, Timestamp startDate, Timestamp endDate) {
        String query = "INSERT INTO ORDERS (Booker, Book_id, TimeStamp, StartDate, EndDate, ApproveStatus, PaymentStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = this.con.prepareStatement(query)) {
            pst.setInt(1, bookerId);
            pst.setInt(2, gearId);
            pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(4, startDate);
            pst.setTimestamp(5, endDate);
            pst.setBoolean(6, false);
            pst.setBoolean(7, false);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelOrder(int id) {
        try {
            query = "DELETE FROM ORDERS WHERE Orders_id = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
}
