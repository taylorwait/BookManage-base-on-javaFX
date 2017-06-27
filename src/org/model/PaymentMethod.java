package org.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty PmethodCode;
    private SimpleStringProperty PmethodName;
    private SimpleStringProperty BankCode;

    public PaymentMethod(String PmethodCode,String PmethodName,String BankCode){
        super();
        this.PmethodCode=new SimpleStringProperty(PmethodCode);
        this.PmethodName=new SimpleStringProperty(PmethodName);
        this.BankCode=new SimpleStringProperty(BankCode);

    }
    public PaymentMethod(){

    }
    //getter

    public String getPmethodCode() {
        return PmethodCode.get();
    }

    public SimpleStringProperty pmethodCodeProperty() {
        return PmethodCode;
    }

    public String getPmethodName() {
        return PmethodName.get();
    }

    public SimpleStringProperty pmethodNameProperty() {
        return PmethodName;
    }

    public String getBankCode() {
        return BankCode.get();
    }

    public SimpleStringProperty bankCodeProperty() {
        return BankCode;
    }
    //setter

    public void setPmethodCode(String pmethodCode) {
        this.PmethodCode.set(pmethodCode);
    }

    public void setPmethodName(String pmethodName) {
        this.PmethodName.set(pmethodName);
    }

    public void setBankCode(String bankCode) {
        this.BankCode.set(bankCode);
    }
}
