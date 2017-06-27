package org.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jdbc.JdbcUtils;
import org.model.Book;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class BookControl  implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> Bcode;
    @FXML
    private TableColumn<Book, String> Bname;
    @FXML
    private TableColumn<Book, String> Author;
    @FXML
    private TableColumn<Book,String> Introduction;
    @FXML
    private TableColumn<Book,Integer> Stock;
    @FXML
    private TableColumn<Book,String> Shelf;
    @FXML
    private TableColumn<Book, String> Pnumber;
    @FXML
    private TableColumn<Book, Float> Sprice;
    @FXML
    private TableColumn<Book, Integer> Storenumber;

    //增
    @FXML
    private TextField Bcode_add;
    @FXML
    private TextField Bname_add;
    @FXML
    private TextField Author_add;
    @FXML
    private TextField Introduction_add;
    @FXML
    private TextField Stock_add;
    @FXML
    private TextField Shelf_add;
    @FXML
    private TextField Pnumber_add;
    @FXML
    private TextField Sprice_add;
    @FXML
    private TextField Storenumber_add;

    //改
    @FXML
    private TextField Bcode_update;
    @FXML
    private TextField Bname_update;
    @FXML
    private TextField Author_update;
    @FXML
    private TextField Introduction_update;
    @FXML
    private TextField Stock_update;
    @FXML
    private TextField Shelf_update;
    @FXML
    private TextField Pnumber_update;
    @FXML
    private TextField Sprice_update;
    @FXML
    private TextField Storenumber_update;

    //删
    @FXML
    private TextField Bcode_remove;

    //查
    @FXML TextField Bcode_select;
    @FXML TextField Bname_select;
    @FXML TextField Author_select;
    @FXML TextField Introduction_select;
    @FXML TextField Stock_select;
    @FXML TextField Shelf_select;
    @FXML TextField Pnumber_select;
    @FXML TextField Sprice_select;
    @FXML TextField Storenumber_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Book> list = FXCollections.observableArrayList();

    public BookControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bcode.setCellValueFactory(new PropertyValueFactory<Book, String>("Bcode"));
        Bname.setCellValueFactory(new PropertyValueFactory<Book, String>("Bname"));
        Author.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        Introduction.setCellValueFactory(new PropertyValueFactory<Book,String>("Introduction"));
        Stock.setCellValueFactory(new PropertyValueFactory<Book,Integer>("Stock"));
        Shelf.setCellValueFactory(new PropertyValueFactory<Book,String>("Shelf"));
        Pnumber.setCellValueFactory(new PropertyValueFactory<Book, String>("Pnumber"));
        Sprice.setCellValueFactory(new PropertyValueFactory<Book, Float>("Sprice"));
        Storenumber.setCellValueFactory(new PropertyValueFactory<Book,Integer>("Storenumber"));
        BookData("select * from Book");
        tableView.setItems(list);

    }

    public void BookData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Book(
                        rs.getString("Bcode"),
                        rs.getString("Bname"),
                        rs.getString("Author"),
                        rs.getString("Introduction"),
                        rs.getInt("Stock"),
                        rs.getString("Shelf"),
                        rs.getString("Pnumber"),
                        rs.getFloat("Sprice"),
                        rs.getInt("Storenumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Bcode__ = Bcode_add.getText();
        String Bname__ = Bname_add.getText();
        String Author__ = Author_add.getText();
        String Introduction__=Introduction_add.getText();
        String Stock__=Stock_add.getText();
        String Shelf__=Shelf_add.getText();
        String Pnumber__ = Pnumber_add.getText();
        String Sprice__=Sprice_add.getText();
        String Storenumber__=Storenumber_add.getText();

        List<Book> addlist = new ArrayList<Book>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Book values (?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Bcode__);
        params.add(Bname__);
        params.add(Author__);
        params.add(Introduction__);
        params.add(Stock__);
        params.add(Shelf__);
        params.add(Pnumber__);
        params.add(Sprice__);
        params.add(Storenumber__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        BookData("select * from Book");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Bcode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Bcode){
        String sql = "update Book set "+column+" = ? where Bcode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Bcode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            BookData("select * from Book");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Bcode__ = Bcode_update.getText();
        String Bname__ = Bname_update.getText();
        String Author__ = Author_update.getText();
        String Introduction__=Introduction_update.getText();
        String Stock__=Stock_update.getText();
        String Shelf__=Shelf_update.getText();
        String Pnumber__ = Pnumber_update.getText();
        String Sprice__ = Sprice_update.getText();
        String Storenumber__=Storenumber_update.getText();

        boolean flage=false;

        if(Bcode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Bname__.equals("")!=true){
                updateOne("Bname",Bname__,Bcode__);
                flage=true;
            }
            if(Author__.equals("")!=true){
                updateOne("Author",Author__,Bcode__);
                flage=true;
            }
            if(Pnumber__.equals("")!=true){
                updateOne("Pnumber",Pnumber__,Bcode__);
                flage=true;
            }
            if(Introduction__.equals("")!=true){
                updateOne("Introduction",Introduction__,Bcode__);
            }
            if(Stock__.equals("")!=true){
                updateOne("Stock",Stock__,Bcode__);
            }
            if(Shelf__.equals("")!=true){
                updateOne("Shelf",Shelf__,Bcode__);
            }
            if(Sprice__.equals("")!=true){
                updateOne("Sprice",Sprice__,Bcode__);
                flage=true;
            }
            if(Sprice__.equals("")!=true){
                updateOne("Storenumber",Storenumber__,Bcode__);
                flage=true;
            }

            if(flage==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "请至少修改一个字段");
                warning.setTitle("警告信息！");
                warning.showAndWait();
            }
        }


    }

    //remove
    @FXML
    protected void remove() {
        String Bcode__ = Bcode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Book where Bcode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Bcode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            BookData("select * from Book");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Bcode__ = Bcode_select.getText();
        String Bname__ = Bname_select.getText();
        String Author__ = Author_select.getText();
        String Introduction__ = Introduction_select.getText();
        String Stock__ = Stock_select.getText();
        String Shelf__ = Shelf_select.getText();
        String Pnumber__ = Pnumber_select.getText();
        String Sprice__ = Sprice_select.getText();
        String Storenumber__=Storenumber_select.getText();

        list.clear();

        if(Bcode__.equals("")!=true){
            BookData("select *from Book where Bcode='"+Bcode__+"'");
        }else if(Bname__.equals("")!=true){
            BookData("select *from Book where Bname='"+Bname__+"'");
        }else if(Author__.equals("")!=true){
            BookData("select *from Book where Author='"+Author__+"'");
        }else if(Introduction__.equals("")!=true){
            BookData("select *from Book where Introduction='"+Introduction__+"'");
        }else if(Stock__.equals("")!=true){
            BookData("select *from Book where Stock='"+Stock__+"'");
        }else if(Shelf__.equals("")!=true){
            BookData("select *from Book where Shelf='"+Shelf__+"'");
        }else if(Pnumber__.equals("")!=true){
            BookData("select *from Book where Pnumber='"+Pnumber__+"'");
        }else if(Sprice__.equals("")!=true) {
            BookData("select *from Book where Sprice='" + Sprice__ + "'");
        }else if(Storenumber__.equals("")!=true){
            BookData("select *from Book where Storenumber='" + Storenumber__ + "'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        BookData("select * from Book");
    }
}
