package org.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class Vip implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Ccode;
    private SimpleStringProperty Clevel;
    private SimpleStringProperty Discount;

    public Vip(String Ccode,String Clevel,String Discount){
        super();
        this.Ccode=new SimpleStringProperty(Ccode);
        this.Clevel=new SimpleStringProperty(Clevel);
        this.Discount=new SimpleStringProperty(Discount);

    }
    public Vip(){

    }
    //getter
    public String getCcode() {
        return Ccode.get();
    }

    public SimpleStringProperty ccodeProperty() {
        return Ccode;
    }

    public String getClevel() {
        return Clevel.get();
    }

    public SimpleStringProperty clevelProperty() {
        return Clevel;
    }

    public String getDiscount() {
        return Discount.get();
    }

    public SimpleStringProperty discountProperty() {
        return Discount;
    }

    //setter

    public void setCcode(String ccode) {
        this.Ccode.set(ccode);
    }

    public void setClevel(String clevel) {
        this.Clevel.set(clevel);
    }

    public void setDiscount(String discount) {
        this.Discount.set(discount);
    }
}
