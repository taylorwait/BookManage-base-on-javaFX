package org.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty SupplierCode;
    private SimpleStringProperty Sname;
    private SimpleStringProperty Stel;
    private SimpleStringProperty Saddress;

    public Supplier(String SupplierCode, String Sname, String Stel, String Saddress){
        super();
        this.SupplierCode=new SimpleStringProperty(SupplierCode);
        this.Sname=new SimpleStringProperty(Sname);
        this.Stel=new SimpleStringProperty(Stel);
        this.Saddress=new SimpleStringProperty(Saddress);
    }
    public Supplier(){

    }

    //getter

    public String getSupplierCode() {
        return SupplierCode.get();
    }

    public SimpleStringProperty supplierCodeProperty() {
        return SupplierCode;
    }

    public String getSname() {
        return Sname.get();
    }

    public SimpleStringProperty snameProperty() {
        return Sname;
    }

    public String getStel() {
        return Stel.get();
    }

    public SimpleStringProperty stelProperty() {
        return Stel;
    }

    public String getSaddress() {
        return Saddress.get();
    }

    public SimpleStringProperty saddressProperty() {
        return Saddress;
    }
    //setter

    public void setSupplierCode(String supplierCode) {
        this.SupplierCode.set(supplierCode);
    }

    public void setSname(String sname) {
        this.Sname.set(sname);
    }

    public void setStel(String stel) {
        this.Stel.set(stel);
    }

    public void setSaddress(String saddress) {
        this.Saddress.set(saddress);
    }
}
