package org.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class Bank implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty BankCode;
    private SimpleStringProperty BankName;

    public Bank(String BankCode,String BankName){
        super();
        this.BankCode=new SimpleStringProperty(BankCode);
        this.BankName=new SimpleStringProperty(BankName);

    }
    public Bank(){

    }
    //getter

    public String getBankCode() {
        return BankCode.get();
    }

    public SimpleStringProperty bankCodeProperty() {
        return BankCode;
    }

    public String getBankName() {
        return BankName.get();
    }

    public SimpleStringProperty bankNameProperty() {
        return BankName;
    }
    //setter

    public void setBankCode(String bankCode) {
        this.BankCode.set(bankCode);
    }

    public void setBankName(String bankName) {
        this.BankName.set(bankName);
    }
}
