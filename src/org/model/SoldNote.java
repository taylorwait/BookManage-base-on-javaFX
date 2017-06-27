package org.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class SoldNote implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Soldcode;
    private SimpleStringProperty Bcode;
    private SimpleStringProperty Ccode;
    private SimpleIntegerProperty Samount;
    private SimpleFloatProperty Sprice;
    private SimpleFloatProperty STprice;
    private SimpleStringProperty Sdate;
    private SimpleStringProperty Ecode;

    public SoldNote(String Soldcode, String Bcode, String Ccode, Integer Samount, float Sprice, float STprice, Date Sdate,String Ecode){
        super();
        this.Soldcode=new SimpleStringProperty(Soldcode);
        this.Bcode=new SimpleStringProperty(Bcode);
        this.Ccode=new SimpleStringProperty(Ccode);
        this.Samount=new SimpleIntegerProperty(Samount);
        this.Sprice=new SimpleFloatProperty(Sprice);
        this.STprice=new SimpleFloatProperty(STprice);
        this.Sdate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(Sdate));
        this.Ecode=new SimpleStringProperty(Ecode);
    }
    public SoldNote(){

    }
    //getter


    public String getSoldcode() {
        return Soldcode.get();
    }

    public SimpleStringProperty soldcodeProperty() {
        return Soldcode;
    }

    public String getBcode() {
        return Bcode.get();
    }

    public SimpleStringProperty bcodeProperty() {
        return Bcode;
    }

    public String getCcode() {
        return Ccode.get();
    }

    public SimpleStringProperty ccodeProperty() {
        return Ccode;
    }

    public int getSamount() {
        return Samount.get();
    }

    public SimpleIntegerProperty samountProperty() {
        return Samount;
    }

    public float getSprice() {
        return Sprice.get();
    }

    public SimpleFloatProperty spriceProperty() {
        return Sprice;
    }

    public float getSTprice() {
        return STprice.get();
    }

    public SimpleFloatProperty STpriceProperty() {
        return STprice;
    }

    public String getSdate() {
        return Sdate.get();
    }

    public SimpleStringProperty sdateProperty() {
        return Sdate;
    }

    public String getEcode() {
        return Ecode.get();
    }

    public SimpleStringProperty ecodeProperty() {
        return Ecode;
    }

    //setter

    public void setSoldcode(String soldcode) {
        this.Soldcode.set(soldcode);
    }

    public void setBcode(String bcode) {
        this.Bcode.set(bcode);
    }

    public void setCcode(String ccode) {
        this.Ccode.set(ccode);
    }

    public void setSamount(int samount) {
        this.Samount.set(samount);
    }

    public void setSprice(float sprice) {
        this.Sprice.set(sprice);
    }

    public void setSTprice(float STprice) {
        this.STprice.set(STprice);
    }

    public void setSdate(String sdate) {
        this.Sdate.set(sdate);
    }

    public void setEcode(String ecode) {
        this.Ecode.set(ecode);
    }
}
