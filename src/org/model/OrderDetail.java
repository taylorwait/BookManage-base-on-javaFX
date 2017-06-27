package org.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Ocode;
    private SimpleStringProperty Odate;
    private SimpleStringProperty BankCode;
    private SimpleFloatProperty Tpayment;
    private SimpleStringProperty ExpriyDate;
    private SimpleStringProperty PmethodCode;

    public OrderDetail(String Ocode, Date Odate, String BankCode, float Tpayment, Date ExpriyDate, String PmethodCode){
        super();
        this.Ocode=new SimpleStringProperty(Ocode);
        this.Odate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(Odate));
        this.BankCode=new SimpleStringProperty(BankCode);
        this.Tpayment=new SimpleFloatProperty(Tpayment);
        this.ExpriyDate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(ExpriyDate));
        this.PmethodCode=new SimpleStringProperty(PmethodCode);
    }
    public OrderDetail(){

    }

    //getter

    public String getOcode() {
        return Ocode.get();
    }

    public SimpleStringProperty ocodeProperty() {
        return Ocode;
    }

    public String getOdate() {
        return Odate.get();
    }

    public SimpleStringProperty odateProperty() {
        return Odate;
    }

    public String getBankCode() {
        return BankCode.get();
    }

    public SimpleStringProperty bankCodeProperty() {
        return BankCode;
    }

    public float getTpayment() {
        return Tpayment.get();
    }

    public SimpleFloatProperty tpaymentProperty() {
        return Tpayment;
    }

    public String getExpriyDate() {
        return ExpriyDate.get();
    }

    public SimpleStringProperty expriyDateProperty() {
        return ExpriyDate;
    }

    public String getPmethodCode() {
        return PmethodCode.get();
    }

    public SimpleStringProperty pmethodCodeProperty() {
        return PmethodCode;
    }

    //setter

    public void setOcode(String ocode) {
        this.Ocode.set(ocode);
    }

    public void setOdate(String odate) {
        this.Odate.set(odate);
    }

    public void setBankCode(String bankCode) {
        this.BankCode.set(bankCode);
    }

    public void setTpayment(float tpayment) {
        this.Tpayment.set(tpayment);
    }

    public void setExpriyDate(String expriyDate) {
        this.ExpriyDate.set(expriyDate);
    }

    public void setPmethodCode(String pmethodCode) {
        this.PmethodCode.set(pmethodCode);
    }
}
