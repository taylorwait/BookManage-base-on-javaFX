package org.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Ecode;
    private SimpleStringProperty Ename;
    private SimpleStringProperty Etel;
    private SimpleStringProperty Esex;
    private SimpleStringProperty Eaddress;
    private SimpleStringProperty Eduty;

    public Employee(String Ecode, String Ename, String Etel, String Esex, String Eaddress, String Eduty){
        super();
        this.Ecode=new SimpleStringProperty(Ecode);
        this.Ename=new SimpleStringProperty(Ename);
        this.Etel=new SimpleStringProperty(Etel);
        this.Esex=new SimpleStringProperty(Esex);
        this.Eaddress=new SimpleStringProperty(Eaddress);
        this.Eduty=new SimpleStringProperty(Eduty);
    }
    public Employee(){

    }

    //getter

    public String getEcode() {
        return Ecode.get();
    }

    public SimpleStringProperty ecodeProperty() {
        return Ecode;
    }

    public String getEname() {
        return Ename.get();
    }

    public SimpleStringProperty enameProperty() {
        return Ename;
    }

    public String getEtel() {
        return Etel.get();
    }

    public SimpleStringProperty etelProperty() {
        return Etel;
    }

    public String getEsex() {
        return Esex.get();
    }

    public SimpleStringProperty esexProperty() {
        return Esex;
    }

    public String getEaddress() {
        return Eaddress.get();
    }

    public SimpleStringProperty eaddressProperty() {
        return Eaddress;
    }

    public String getEduty() {
        return Eduty.get();
    }

    public SimpleStringProperty edutyProperty() {
        return Eduty;
    }

    //setter

    public void setEcode(String ecode) {
        this.Ecode.set(ecode);
    }

    public void setEname(String ename) {
        this.Ename.set(ename);
    }

    public void setEtel(String etel) {
        this.Etel.set(etel);
    }

    public void setEsex(String esex) {
        this.Esex.set(esex);
    }

    public void setEaddress(String eaddress) {
        this.Eaddress.set(eaddress);
    }

    public void setEduty(String eduty) {
        this.Eduty.set(eduty);
    }
}
