/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Gear;

public class TentDAO {

  private Connection con;

  public TentDAO(Connection con) {
    this.con = con;
  }

  public List<Gear> getAllTents() {
    List<Gear> tents = new ArrayList<>();
    try (PreparedStatement pst = this.con.prepareStatement("SELECT G.*, P.Price FROM GEAR G INNER JOIN PRICE P ON G.Price_id = P.Price_id WHERE G.Name LIKE N'%Lều%'");
         ResultSet rs = pst.executeQuery()) {
      while (rs.next()) {
        Gear tent = new Gear();
        tent.setGearId(rs.getInt("Gear_id"));
        tent.setGearPrice(rs.getInt("Price"));
        tent.setGearName(rs.getString("Name"));
        tent.setGearDecription(rs.getString("Description"));
        tent.setGearImage(rs.getString("Image"));
        tents.add(tent);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return tents;
  }
}
