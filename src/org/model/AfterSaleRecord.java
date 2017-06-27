package org.model;

        import javafx.beans.property.SimpleStringProperty;

        import java.io.Serializable;
        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class AfterSaleRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty AfterSaleRecord;
    private SimpleStringProperty Ccode;
    private SimpleStringProperty AfterSaleReason;
    private SimpleStringProperty AfterSaleDeal;
    private SimpleStringProperty AfterSaleDate;
    private SimpleStringProperty Ecode;

    public AfterSaleRecord(String AfterSaleRecord, String Ccode, String AfterSaleReason, String AfterSaleDeal, Date AfterSaleDate, String Ecode){
        super();
        this.AfterSaleRecord=new SimpleStringProperty(AfterSaleRecord);
        this.Ccode=new SimpleStringProperty(Ccode);
        this.AfterSaleReason=new SimpleStringProperty(AfterSaleReason);
        this.AfterSaleDeal=new SimpleStringProperty(AfterSaleDeal);
        this.AfterSaleDate=new SimpleStringProperty((new SimpleDateFormat("yyyy-MM-dd")).format(AfterSaleDate));
        this.Ecode=new SimpleStringProperty(Ecode);
    }
    public AfterSaleRecord(){

    }

    //getter

    public String getAfterSaleRecord() {
        return AfterSaleRecord.get();
    }

    public SimpleStringProperty afterSaleRecordProperty() {
        return AfterSaleRecord;
    }

    public String getCcode() {
        return Ccode.get();
    }

    public SimpleStringProperty ccodeProperty() {
        return Ccode;
    }

    public String getAfterSaleReason() {
        return AfterSaleReason.get();
    }

    public SimpleStringProperty afterSaleReasonProperty() {
        return AfterSaleReason;
    }

    public String getAfterSaleDeal() {
        return AfterSaleDeal.get();
    }

    public SimpleStringProperty afterSaleDealProperty() {
        return AfterSaleDeal;
    }

    public String getAfterSaleDate() {
        return AfterSaleDate.get();
    }

    public SimpleStringProperty afterSaleDateProperty() {
        return AfterSaleDate;
    }

    public String getEcode() {
        return Ecode.get();
    }

    public SimpleStringProperty ecodeProperty() {
        return Ecode;
    }

    //setter

    public void setAfterSaleRecord(String afterSaleRecord) {
        this.AfterSaleRecord.set(afterSaleRecord);
    }

    public void setCcode(String ccode) {
        this.Ccode.set(ccode);
    }

    public void setAfterSaleReason(String afterSaleReason) {
        this.AfterSaleReason.set(afterSaleReason);
    }

    public void setAfterSaleDeal(String afterSaleDeal) {
        this.AfterSaleDeal.set(afterSaleDeal);
    }

    public void setAfterSaleDate(String afterSaleDate) {
        this.AfterSaleDate.set(afterSaleDate);
    }

    public void setEcode(String ecode) {
        this.Ecode.set(ecode);
    }
}
