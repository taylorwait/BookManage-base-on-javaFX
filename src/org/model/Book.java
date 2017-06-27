package org.model;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class Book  implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleStringProperty Bcode;
    private SimpleStringProperty Bname;
    private SimpleStringProperty Author;
    private SimpleStringProperty Introduction;
    private SimpleIntegerProperty Stock;
    private SimpleStringProperty Shelf;
    private SimpleStringProperty Pnumber;
    private SimpleFloatProperty Sprice;
    private SimpleIntegerProperty Storenumber;

    public Book(String Bcode, String Bname, String Author, String Introduction, int Stock, String Shelf,String Pnumber, float Sprice, int Storenumber){
        super();
        this.Bcode=new SimpleStringProperty(Bcode);
        this.Bname=new SimpleStringProperty(Bname);
        this.Author=new SimpleStringProperty(Author);
        this.Introduction=new SimpleStringProperty(Introduction);
        this.Stock=new SimpleIntegerProperty(Stock);
        this.Shelf=new SimpleStringProperty(Shelf);
        this.Pnumber=new SimpleStringProperty(Pnumber);
        this.Sprice=new SimpleFloatProperty(Sprice);
        this.Storenumber=new SimpleIntegerProperty(Storenumber);
    }
    public Book(){

    }
    //getter

    public String getBcode() {
        return Bcode.get();
    }

    public SimpleStringProperty bcodeProperty() {
        return Bcode;
    }

    public String getBname() {
        return Bname.get();
    }

    public SimpleStringProperty bnameProperty() {
        return Bname;
    }

    public String getAuthor() {
        return Author.get();
    }

    public SimpleStringProperty authorProperty() {
        return Author;
    }

    public String getIntroduction() {
        return Introduction.get();
    }

    public SimpleStringProperty introductionProperty() {
        return Introduction;
    }

    public int getStock() {
        return Stock.get();
    }

    public SimpleIntegerProperty stockProperty() {
        return Stock;
    }

    public String getShelf() {
        return Shelf.get();
    }

    public SimpleStringProperty shelfProperty() {
        return Shelf;
    }

    public String getPnumber() {
        return Pnumber.get();
    }

    public SimpleStringProperty pnumberProperty() {
        return Pnumber;
    }

    public float getSprice() {
        return Sprice.get();
    }

    public SimpleFloatProperty spriceProperty() {
        return Sprice;
    }

    public int getStorenumber() {
        return Storenumber.get();
    }

    public SimpleIntegerProperty storenumberProperty() {
        return Storenumber;
    }
    //setter

    public void setBcode(String bcode) {
        this.Bcode.set(bcode);
    }

    public void setBname(String bname) {
        this.Bname.set(bname);
    }

    public void setAuthor(String author) {
        this.Author.set(author);
    }

    public void setIntroduction(String introduction) {
        this.Introduction.set(introduction);
    }

    public void setStock(int stock) {
        this.Stock.set(stock);
    }

    public void setShelf(String shelf) {
        this.Shelf.set(shelf);
    }

    public void setPnumber(String pnumber) {
        this.Pnumber.set(pnumber);
    }

    public void setSprice(float sprice) {
        this.Sprice.set(sprice);
    }

    public void setStorenumber(int storenumber) {
        this.Storenumber.set(storenumber);
    }
}
