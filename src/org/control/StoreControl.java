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
import org.model.Store;

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
public class StoreControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Store> tableView;

    @FXML
    private TableColumn<Store, String> StoreCode;
    @FXML
    private TableColumn<Store, Integer> Scapacity;
    @FXML
    private TableColumn<Store, Integer> Sremain;
    @FXML
    private TableColumn<Store, String> Ecode;
    @FXML
    private TableColumn<Store, String> Saddress;


    //增
    @FXML
    private TextField StoreCode_add;
    @FXML
    private TextField Scapacity_add;
    @FXML
    private TextField Sremain_add;
    @FXML
    private TextField Ecode_add;
    @FXML
    private TextField Saddress_add;


    //改
    @FXML
    private TextField StoreCode_update;
    @FXML
    private TextField Scapacity_update;
    @FXML
    private TextField Sremain_update;
    @FXML
    private TextField Ecode_update;
    @FXML
    private TextField Saddress_update;


    //删
    @FXML
    private TextField StoreCode_remove;

    //查
    @FXML TextField StoreCode_select;
    @FXML TextField Scapacity_select;
    @FXML TextField Sremain_select;
    @FXML TextField Ecode_select;
    @FXML TextField Saddress_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Store> list = FXCollections.observableArrayList();

    public StoreControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StoreCode.setCellValueFactory(new PropertyValueFactory<Store, String>("StoreCode"));
        Scapacity.setCellValueFactory(new PropertyValueFactory<Store, Integer>("Scapacity"));
        Sremain.setCellValueFactory(new PropertyValueFactory<Store, Integer>("Sremain"));
        Ecode.setCellValueFactory(new PropertyValueFactory<Store, String>("Ecode"));
        Saddress.setCellValueFactory(new PropertyValueFactory<Store, String>("Saddress"));
        StoreData("select * from Store");
        tableView.setItems(list);

    }

    public void StoreData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Store(
                        rs.getString("StoreCode"),
                        rs.getInt("Scapacity"),
                        rs.getInt("Sremain"),
                        rs.getString("Ecode"),
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
        String StoreCode__ = StoreCode_add.getText();
        String Scapacity__ = Scapacity_add.getText();
        String Sremain__ = Sremain_add.getText();
        String Ecode__ = Ecode_add.getText();
        String Saddress__ = Saddress_add.getText();

        List<Store> addlist = new ArrayList<Store>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Store values (?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(StoreCode__);
        params.add(Scapacity__);
        params.add(Sremain__);
        params.add(Ecode__);
        params.add(Saddress__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        StoreData("select * from Store");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    StoreCode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String StoreCode){
        String sql = "update Store set "+column+" = ? where StoreCode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(StoreCode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            StoreData("select * from Store");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String StoreCode__ = StoreCode_update.getText();
        String Scapacity__ = Scapacity_update.getText();
        String Sremain__ = Sremain_update.getText();
        String Ecode__ = Ecode_update.getText();
        String Saddress__ = Saddress_update.getText();

        boolean flage=false;

        if(StoreCode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Scapacity__.equals("")!=true){
                updateOne("Scapacity",Scapacity__,StoreCode__);
                flage=true;
            }
            if(Sremain__.equals("")!=true){
                updateOne("Sremain",Sremain__,StoreCode__);
                flage=true;
            }
            if(Ecode__.equals("")!=true){
                updateOne("Ecode",Ecode__,StoreCode__);
                flage=true;
            }
            if(Saddress__.equals("")!=true){
                updateOne("Saddress",Saddress__,StoreCode__);
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
        String StoreCode__ = StoreCode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Store where StoreCode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(StoreCode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            StoreData("select * from Store");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String StoreCode__ = StoreCode_select.getText();
        String Scapacity__ = Scapacity_select.getText();
        String Sremain__ = Sremain_select.getText();
        String Ecode__ = Ecode_select.getText();
        String Saddress__ = Saddress_select.getText();

        list.clear();

        if(StoreCode__.equals("")!=true){
            StoreData("select *from Store where StoreCode='"+StoreCode__+"'");
        }else if(Scapacity__.equals("")!=true){
            StoreData("select *from Store where Scapacity='"+Scapacity__+"'");
        }else if(Sremain__.equals("")!=true){
            StoreData("select *from Store where Sremain='"+Sremain__+"'");
        }else if(Ecode__.equals("")!=true){
            StoreData("select *from Store where Ecode='"+Ecode__+"'");
        }else if(Saddress__.equals("")!=true){
            StoreData("select *from Store where Saddress='"+Saddress__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML
    protected void back(){
        list.clear();
        StoreData("select * from Store");
    }
}
