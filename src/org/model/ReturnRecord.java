package org.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class ReturnRecord implements Serializable{
        private static final long serialVersionUID = 1L;

        private SimpleStringProperty Rcode;
        private SimpleStringProperty Bcode;
        private SimpleIntegerProperty Ramount;
        private SimpleFloatProperty RTprice;
        private SimpleStringProperty Pdate;
        private SimpleStringProperty Ecode;
        private SimpleStringProperty SupplierCode;
        private SimpleStringProperty Rtype;

        public ReturnRecord(String Rcode, String Bcode, int Ramount, float RTprice, Date Pdate, String Ecode, String SupplierCode, String Rtype){
            super();
            this.Rcode=new SimpleStringProperty(Rcode);
            this.Bcode=new SimpleStringProperty(Bcode);
            this.Ramount=new SimpleIntegerProperty(Ramount);
            this.RTprice=new SimpleFloatProperty(RTprice);
            this.Pdate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(Pdate));
            this.Ecode=new SimpleStringProperty(Ecode);
            this.SupplierCode=new SimpleStringProperty(SupplierCode);
            this.Rtype=new SimpleStringProperty(Rtype);
        }
        public ReturnRecord(){

        }
        //getter

    public String getRcode() {
        return Rcode.get();
    }

    public SimpleStringProperty rcodeProperty() {
        return Rcode;
    }

    public String getBcode() {
        return Bcode.get();
    }

    public SimpleStringProperty bcodeProperty() {
        return Bcode;
    }

    public int getRamount() {
        return Ramount.get();
    }

    public SimpleIntegerProperty ramountProperty() {
        return Ramount;
    }

    public float getRTprice() {
        return RTprice.get();
    }

    public SimpleFloatProperty RTpriceProperty() {
        return RTprice;
    }

    public String getPdate() {
        return Pdate.get();
    }

    public SimpleStringProperty pdateProperty() {
        return Pdate;
    }

    public String getEcode() {
        return Ecode.get();
    }

    public SimpleStringProperty ecodeProperty() {
        return Ecode;
    }

    public String getSupplierCode() {
        return SupplierCode.get();
    }

    public SimpleStringProperty supplierCodeProperty() {
        return SupplierCode;
    }

    public String getRtype() {
        return Rtype.get();
    }

    public SimpleStringProperty rtypeProperty() {
        return Rtype;
    }
    //setter

    public void setRcode(String rcode) {
        this.Rcode.set(rcode);
    }

    public void setBcode(String bcode) {
        this.Bcode.set(bcode);
    }

    public void setRamount(int ramount) {
        this.Ramount.set(ramount);
    }

    public void setRTprice(float RTprice) {
        this.RTprice.set(RTprice);
    }

    public void setPdate(String pdate) {
        this.Pdate.set(pdate);
    }

    public void setEcode(String ecode) {
        this.Ecode.set(ecode);
    }

    public void setSupplierCode(String supplierCode) {
        this.SupplierCode.set(supplierCode);
    }

    public void setRtype(String rtype) {
        this.Rtype.set(rtype);
    }
}
