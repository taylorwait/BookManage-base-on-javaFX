package org.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty StoreCode;
    private SimpleIntegerProperty Scapacity;
    private SimpleIntegerProperty Sremain;
    private SimpleStringProperty Ecode;
    private SimpleStringProperty Saddress;

    public Store(String StoreCode, int Scapacity, int Sremain, String Ecode, String Saddress){
        super();
        this.StoreCode=new SimpleStringProperty(StoreCode);
        this.Scapacity=new SimpleIntegerProperty(Scapacity);
        this.Sremain=new SimpleIntegerProperty(Sremain);
        this.Ecode=new SimpleStringProperty(Ecode);
        this.Saddress=new SimpleStringProperty(Saddress);
    }
    public Store(){

    }

    //getter

    public String getStoreCode() {
        return StoreCode.get();
    }

    public SimpleStringProperty storeCodeProperty() {
        return StoreCode;
    }

    public int getScapacity() {
        return Scapacity.get();
    }

    public SimpleIntegerProperty scapacityProperty() {
        return Scapacity;
    }

    public int getSremain() {
        return Sremain.get();
    }

    public SimpleIntegerProperty sremainProperty() {
        return Sremain;
    }

    public String getEcode() {
        return Ecode.get();
    }

    public SimpleStringProperty ecodeProperty() {
        return Ecode;
    }

    public String getSaddress() {
        return Saddress.get();
    }

    public SimpleStringProperty saddressProperty() {
        return Saddress;
    }
    //setter

    public void setStoreCode(String storeCode) {
        this.StoreCode.set(storeCode);
    }

    public void setScapacity(int scapacity) {
        this.Scapacity.set(scapacity);
    }

    public void setSremain(int sremain) {
        this.Sremain.set(sremain);
    }

    public void setEcode(String ecode) {
        this.Ecode.set(ecode);
    }

    public void setSaddress(String saddress) {
        this.Saddress.set(saddress);
    }
}
