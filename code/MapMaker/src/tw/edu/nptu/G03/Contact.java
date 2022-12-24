package tw.edu.nptu.G03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "MapMaker") // 給contactmanager查詢用
@Table(name = "map_maker") // table名稱
public class Contact {
    @Id // 不能刪掉

    @Column(name = "UnitID")
    private int UnitID;

    @Column(name = "UnitX")
    private int UnitX;

    @Column(name = "UnitY")
    private int UnitY;

    @Column(name = "UnitW")
    private int UnitW;

    @Column(name = "UnitH")
    private int UnitH;

    @Column(name = "UnitR")
    private int UnitR;

    @Column(name = "UnitG")
    private int UnitG;

    @Column(name = "UnitB")
    private int UnitB;

    @Column(name = "PanelR")
    private int PanelR;

    @Column(name = "PanelG")
    private int PanelG;

    @Column(name = "PanelB")
    private int PanelB;

    public Contact(){}
    public Contact(int UnitID, int UnitX, int UnitY, int UnitW, int UnitH, int UnitR, int UnitG, int UnitB, int PanelR,int PanelG, int PanelB) {
        this.UnitID = UnitID;
        this.UnitX = UnitX;
        this.UnitY = UnitY;
        this.UnitW = UnitW;
        this.UnitH = UnitH;
        this.UnitR = UnitR;
        this.UnitG = UnitG;
        this.UnitB = UnitB;
        this.PanelR = PanelR;
        this.PanelG = PanelG;
        this.PanelB = PanelB;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int UnitID) {
        this.UnitID = UnitID;
    }

    public int getUnitX() {
        return UnitX;
    }

    public void setUnitX(int UnitX) {
        this.UnitX = UnitX;
    }

    public int getUnitY() {
        return UnitY;
    }

    public void setUnitY(int UnitY) {
        this.UnitY = UnitY;
    }

    public int getUnitW() {
        return UnitW;
    }

    public void setUnitW(int UnitW) {
        this.UnitW = UnitW;
    }

    public int getUnitH() {
        return UnitH;
    }

    public void setUnitH(int UnitH) {
        this.UnitH = UnitH;
    }

    // color Unit
    public int getUnitR() {
        return UnitR;
    }

    public void setUnitR(int UnitR) {
        this.UnitR = UnitR;
    }

    public int getUnitG() {
        return UnitG;
    }

    public void setUnitG(int UnitG) {
        this.UnitG = UnitG;
    }

    public int getUnitB() {
        return UnitB;
    }

    public void setUnitB(int UnitB) {
        this.UnitB = UnitB;
    }

    // color panel
    public int getPanelR() {
        return PanelR;
    }

    public void setPanelR(int PanelR) {
        this.PanelR = PanelR;
    }

    public int getPanelG() {
        return PanelG;
    }

    public void setPanelG(int PanelG) {
        this.PanelG = PanelG;
    }

    public int getPanelB() {
        return PanelB;
    }

    public void setPanelB(int PanelB) {
        this.PanelB = PanelB;
    }
}