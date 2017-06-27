package org.model;

import javafx.beans.property.SimpleStringProperty;
import org.controlsfx.samples.propertysheet.Address;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/21.
 */
public class Customer implements Serializable {


    private static final long serialVersionUID = 1L;

    private SimpleStringProperty ccode;
    private SimpleStringProperty cname;
    private SimpleStringProperty ctel;
    private SimpleStringProperty csex;
    private SimpleStringProperty caddress;
    private SimpleStringProperty isvip;




    public Customer(String ccode, String cname, String ctel, String csex, String caddress, String isvip){
        super();
        this.ccode=new SimpleStringProperty(ccode);
        this.cname=new SimpleStringProperty(cname);
        this.ctel=new SimpleStringProperty(ctel);
        this.csex=new SimpleStringProperty(csex);
        this.caddress=new SimpleStringProperty(caddress);
        this.isvip=new SimpleStringProperty(isvip);
    }
    public Customer(){

    }

    //getter
    public String getCcode() {
        return ccode.get();
    }


    public String getCname() {
        return cname.get();
    }


    public String getCtel() {
        return ctel.get();
    }


    public String isCsex() {
        return csex.get();
    }


    public String getCaddress() {
        return caddress.get();
    }


    public String isIsvip() {
        return isvip.get();
    }


    //setter
    public void setCcode(String ccode) {
        this.ccode.set(ccode);
    }

    public void setCname(String cname) {
        this.cname.set(cname);
    }

    public void setCtel(String ctel) {
        this.ctel.set(ctel);
    }

    public void setCsex(String csex) {
        this.csex.set(csex);
    }

    public void setCaddress(String caddress) {
        this.caddress.set(caddress);
    }

    public void setIsvip(String isvip) {
        this.isvip.set(isvip);
    }
}
