
import dao.GearDAO;
import model.Gear;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class NewClass1 {
    public static void main(String[] args) throws Exception {
        GearDAO g = new GearDAO();
        Gear s = g.getGearByID("10");
        System.out.println(s);
    }
}
