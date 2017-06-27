package org.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class PromotionDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty PromotionCode;
    private SimpleStringProperty Bcode;
    private SimpleStringProperty Pdate;
    private SimpleStringProperty Ptype;
    private SimpleStringProperty isCoexist;

    public PromotionDetail(String PromotionCode, String Bcode, Date Pdate, String Ptype, String isCoexist){
        super();
        this.PromotionCode=new SimpleStringProperty(PromotionCode);
        this.Bcode=new SimpleStringProperty(Bcode);
        this.Pdate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(Pdate));
        this.Ptype=new SimpleStringProperty(Ptype);
        this.isCoexist=new SimpleStringProperty(isCoexist);
    }
    public PromotionDetail(){

    }

    //getter

    public String getPromotionCode() {
        return PromotionCode.get();
    }

    public SimpleStringProperty promotionCodeProperty() {
        return PromotionCode;
    }

    public String getBcode() {
        return Bcode.get();
    }

    public SimpleStringProperty bcodeProperty() {
        return Bcode;
    }

    public String getPdate() {
        return Pdate.get();
    }

    public SimpleStringProperty pdateProperty() {
        return Pdate;
    }

    public String getPtype() {
        return Ptype.get();
    }

    public SimpleStringProperty ptypeProperty() {
        return Ptype;
    }

    public String getIsCoexist() {
        return isCoexist.get();
    }

    public SimpleStringProperty isCoexistProperty() {
        return isCoexist;
    }

    //setter

    public void setPromotionCode(String promotionCode) {
        this.PromotionCode.set(promotionCode);
    }

    public void setBcode(String bcode) {
        this.Bcode.set(bcode);
    }

    public void setPdate(String pdate) {
        this.Pdate.set(pdate);
    }

    public void setPtype(String ptype) {
        this.Ptype.set(ptype);
    }

    public void setIsCoexist(String isCoexist) {
        this.isCoexist.set(isCoexist);
    }
}
