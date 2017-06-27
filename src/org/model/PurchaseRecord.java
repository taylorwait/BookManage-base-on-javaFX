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
public class PurchaseRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Purchasecode;
    private SimpleStringProperty Bcode;
    private SimpleIntegerProperty Pamount;
    private SimpleFloatProperty PTprice;
    private SimpleStringProperty Pdate;
    private SimpleStringProperty Ecode;
    private SimpleStringProperty SupplierCode;
    
    public PurchaseRecord(String Purchasecode, String Bcode, int Pamount, float PTprice, Date Pdate, String Ecode, String SupplierCode){
        super();
        this.Purchasecode=new SimpleStringProperty(Purchasecode);
        this.Bcode=new SimpleStringProperty(Bcode);
        this.Pamount=new SimpleIntegerProperty(Pamount);
        this.PTprice=new SimpleFloatProperty(PTprice);
        this.Pdate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(Pdate));
        this.Ecode=new SimpleStringProperty(Ecode);
        this.SupplierCode=new SimpleStringProperty(SupplierCode);
    }
    public PurchaseRecord(){

    }
    //getter


    public String getPurchasecode() {
        return Purchasecode.get();
    }

    public SimpleStringProperty purchasecodeProperty() {
        return Purchasecode;
    }

    public String getBcode() {
        return Bcode.get();
    }

    public SimpleStringProperty bcodeProperty() {
        return Bcode;
    }

    public int getPamount() {
        return Pamount.get();
    }

    public SimpleIntegerProperty pamountProperty() {
        return Pamount;
    }

    public float getPTprice() {
        return PTprice.get();
    }

    public SimpleFloatProperty PTpriceProperty() {
        return PTprice;
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

    //setter

    public void setPurchasecode(String purchasecode) {
        this.Purchasecode.set(purchasecode);
    }

    public void setBcode(String bcode) {
        this.Bcode.set(bcode);
    }

    public void setPamount(int pamount) {
        this.Pamount.set(pamount);
    }

    public void setPTprice(float PTprice) {
        this.PTprice.set(PTprice);
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
}
