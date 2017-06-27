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
import org.model.Supplier;

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
public class SupplierControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Supplier> tableView;

    @FXML
    private TableColumn<Supplier, String> SupplierCode;
    @FXML
    private TableColumn<Supplier, String> Sname;
    @FXML
    private TableColumn<Supplier, String> Stel;
    @FXML
    private TableColumn<Supplier, String> Saddress;

    //增
    @FXML
    private TextField SupplierCode_add;
    @FXML
    private TextField Sname_add;
    @FXML
    private TextField Stel_add;
    @FXML
    private TextField Saddress_add;

    //改
    @FXML
    private TextField SupplierCode_update;
    @FXML
    private TextField Sname_update;
    @FXML
    private TextField Stel_update;
    @FXML
    private TextField Saddress_update;

    //删
    @FXML
    private TextField SupplierCode_remove;

    //查
    @FXML TextField SupplierCode_select;
    @FXML TextField Sname_select;
    @FXML TextField Stel_select;
    @FXML TextField Saddress_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Supplier> list = FXCollections.observableArrayList();

    public SupplierControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SupplierCode.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SupplierCode"));
        Sname.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Sname"));
        Stel.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Stel"));
        Saddress.setCellValueFactory(new PropertyValueFactory<Supplier, String>("Saddress"));
        SupplierData("select * from Supplier");
        tableView.setItems(list);

    }

    public void SupplierData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Supplier(
                        rs.getString("SupplierCode"),
                        rs.getString("Sname"),
                        rs.getString("Stel"),
                        rs.getString("Saddress")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String SupplierCode__ = SupplierCode_add.getText();
        String Sname__ = Sname_add.getText();
        String Stel__ = Stel_add.getText();
        String Saddress__ = Saddress_add.getText();

        List<Supplier> addlist = new ArrayList<Supplier>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Supplier values (?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(SupplierCode__);
        params.add(Sname__);
        params.add(Stel__);
        params.add(Saddress__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        SupplierData("select * from Supplier");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    SupplierCode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String SupplierCode){
        String sql = "update Supplier set "+column+" = ? where SupplierCode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(SupplierCode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            SupplierData("select * from Supplier");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String SupplierCode__ = SupplierCode_update.getText();
        String Sname__ = Sname_update.getText();
        String Stel__ = Stel_update.getText();
        String Saddress__ = Saddress_update.getText();

        boolean flage=false;

        if(SupplierCode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Sname__.equals("")!=true){
                updateOne("Sname",Sname__,SupplierCode__);
                flage=true;
            }
            if(Stel__.equals("")!=true){
                updateOne("Stel",Stel__,SupplierCode__);
                flage=true;
            }
            if(Saddress__.equals("")!=true){
                updateOne("Saddress",Saddress__,SupplierCode__);
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
        String SupplierCode__ = SupplierCode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Supplier where SupplierCode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(SupplierCode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            SupplierData("select * from Supplier");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String SupplierCode__ = SupplierCode_select.getText();
        String Sname__ = Sname_select.getText();
        String Stel__ = Stel_select.getText();
        String Saddress__ = Saddress_select.getText();

        list.clear();

        if(SupplierCode__.equals("")!=true){
            SupplierData("select *from Supplier where SupplierCode='"+SupplierCode__+"'");
        }else if(Sname__.equals("")!=true){
            SupplierData("select *from Supplier where Sname='"+Sname__+"'");
        }else if(Stel__.equals("")!=true){
            SupplierData("select *from Supplier where Stel='"+Stel__+"'");
        }else if(Saddress__.equals("")!=true){
            SupplierData("select *from Supplier where Saddress='"+Saddress__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML
    protected void back(){
        list.clear();
        SupplierData("select * from Supplier");
    }
}
