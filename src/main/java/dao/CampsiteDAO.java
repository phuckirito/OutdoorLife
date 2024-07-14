package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Campsite;
import model.CampsiteOrder;

public class CampsiteDAO extends DBContext {

    public List<Campsite> getAllRiverCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, P.Price FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id WHERE C.Name LIKE N'%Sông%'"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price_id"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public List<Campsite> getAllMountainCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, P.Price FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id WHERE C.Name LIKE N'%Núi%'"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price_id"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public List<Campsite> getAllBeachCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, P.Price FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id WHERE C.Name LIKE N'%Biển%'"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price_id"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public boolean isDateAvailable(int campId, java.sql.Date startDate, java.sql.Date endDate) throws Exception {
        String query = "SELECT * FROM ORDERS WHERE Campsite_id = ? AND ((StartDate <= ? AND EndDate >= ?) OR (StartDate <= ? AND EndDate >= ?))";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, campId);
            pst.setDate(2, endDate);
            pst.setDate(3, startDate);
            pst.setDate(4, startDate);
            pst.setDate(5, endDate);
            ResultSet rs = pst.executeQuery();
            return !rs.next();
        }
    }

    public void bookCampsite(CampsiteOrder order) throws Exception {
        String query = "INSERT INTO ORDERS (Campsite_id, Booker, StartDate, EndDate, ApproveStatus, PaymentStatus, TimeStamp, Quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, order.getCampsiteId());
            pst.setInt(2, order.getBooker());
            pst.setTimestamp(3, order.getStartDate());
            pst.setTimestamp(4, order.getEndDate());
            pst.setBoolean(5, order.isApproveStatus());
            pst.setBoolean(6, order.isPaymentStatus());
            pst.setTimestamp(7, order.getTimeStamp());
            pst.setInt(8, order.getQuantity());
            pst.executeUpdate();
        }
    }

    public Campsite getSingleCampsite(int id) {
        Campsite row = null;
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT * FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id WHERE C.Campsite_id = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    row = new Campsite();
                    row.setCampId(rs.getInt("Campsite_id"));
                    row.setCampName(rs.getString("Name"));
                    row.setCampAddress(rs.getString("Address"));
                    row.setCampDescription(rs.getString("Description"));
                    row.setCampPrice(rs.getInt("Price"));
                    row.setCampImage(rs.getString("Image"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return row;
    }

    public int getTotalCampCartPrice(ArrayList<CampsiteOrder> campsiteOrderList) throws Exception {
        int sum = 0;
        try {
            if (campsiteOrderList.size() > 0) {
                for (CampsiteOrder item : campsiteOrderList) {
                    Connection con = getConnection();
                    PreparedStatement pst = con.prepareStatement("SELECT C.*, P.Price FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id WHERE C.Campsite_id = ?");
                    pst.setInt(1, item.getCampId());
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        Timestamp startDate = rs.getTimestamp("StartDate");
                        Timestamp endDate = rs.getTimestamp("EndDate");
                        long milliseconds = endDate.getTime() - startDate.getTime();
                        int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));
                        sum += rs.getInt("Price") * item.getQuantity() * bookingDays;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM CAMPSITE \n";
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_items");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Campsite> getAllCampsite(int page, int size) throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT C.*, P.Price FROM CAMPSITE C INNER JOIN PRICE P ON C.Price_id = P.Price_id "
                + "ORDER BY C.Campsite_id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Campsite campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampPrice(rs.getInt("Price_id"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsites.add(campsite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public List<Campsite> searchByName(String txtSearch) throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT C.* "
                + "FROM CAMPSITE C "
                + "JOIN PRICE P ON C.Price_id = P.Price_id "
                + "WHERE C.Name LIKE ?";

        try {Connection con = getConnection();
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Campsite campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampPrice(rs.getInt("Price_id"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsites.add(campsite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public void deleteCampsite(String id) throws Exception {
        String query = "DELETE FROM CAMPSITE WHERE Campsite_id = ?";

        try {Connection con = getConnection();
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCampsite(String price, String address, String name, String description, String image, String limit) throws Exception {
        String query = "INSERT INTO CAMPSITE \n"
                + "              VALUES (?,?,?,?,?,?)";
        try {Connection con = getConnection();
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(price));
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setString(4, description);
            ps.setString(5, image);
            ps.setInt(6, Integer.parseInt(limit));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Campsite getCampsiteByID(String id) throws Exception {
        String query = "select * from CAMPSITE\n"
                + "	where Campsite_id = ?";
        try {Connection con = getConnection();
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Campsite(rs.getInt(1),
                        rs.getInt(2), 
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void UpdateCampsite(String id, String name, String price, String decription, String image, String address, boolean status, String limit) throws Exception {
        String query = "update CAMPSITE\n"
                + "set [Name] = ?,\n"
                + "	[Price_id] = ?,\n"
                + "	[Description] = ?,\n"
                + "	[Image] = ?\n"
                + "	[Address] = ?\n"
                + "	[Status] = ?\n"
                + "	[Limite] = ?\n"
                + "	where [Campsite_id] = ?";
        try {Connection con = getConnection();
            con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(price));
            ps.setString(3, decription);
            ps.setString(4, image);
            ps.setString(5, address);
            ps.setBoolean(6, status);
            ps.setInt(7, Integer.parseInt(limit));
            ps.setInt(8, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
