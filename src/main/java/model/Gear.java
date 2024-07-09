/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vietn
 */
public class Gear {
    private int gearId;
    private int gearPrice;
    private String gearName;
    private String gearDecription;
    private String gearImage;
    
    public Gear(){
        
    }

    public Gear(int gearId, int gearPrice, String gearName, String gearDecription, String gearImage) {
        this.gearId = gearId;
        this.gearPrice = gearPrice;
        this.gearName = gearName;
        this.gearDecription = gearDecription;
        this.gearImage = gearImage;
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }

    public int getGearPrice() {
        return gearPrice;
    }

    public void setGearPrice(int gearPrice) {
        this.gearPrice = gearPrice;
    }

    public String getGearName() {
        return gearName;
    }

    public void setGearName(String gearName) {
        this.gearName = gearName;
    }

    public String getGearDecription() {
        return gearDecription;
    }

    public void setGearDecription(String gearDecription) {
        this.gearDecription = gearDecription;
    }

    public String getGearImage() {
        return gearImage;
    }

    public void setGearImage(String gearImage) {
        this.gearImage = gearImage;
    }

    @Override
    public String toString() {
        return "Gear{" + "gearId=" + gearId + ", gearPrice=" + gearPrice + ", gearName=" + gearName + ", gearDecription=" + gearDecription + ", gearImage=" + gearImage + '}';
    }

    
    
}
