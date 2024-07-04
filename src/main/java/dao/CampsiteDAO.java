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
import model.Campsite;

/**
 *
 * @author vietn
 */
public class CampsiteDAO {
    

  private Connection con;

  public CampsiteDAO(Connection con) {
    this.con = con;
  }

  public List<Campsite> getAllCampsite() {
    List<Campsite> campsites = new ArrayList<>();
    try (PreparedStatement pst = this.con.prepareStatement("select * from CAMPSITE");
         ResultSet rs = pst.executeQuery()) {
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
}

