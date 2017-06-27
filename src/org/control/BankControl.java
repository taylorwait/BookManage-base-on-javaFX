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
import org.model.Bank;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class BankControl  implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Bank> tableView;

    @FXML
    private TableColumn<Bank, String> BankCode;
    @FXML
    private TableColumn<Bank, String> BankName;



    //增
    @FXML
    private TextField BankCode_add;
    @FXML
    private TextField BankName_add;

    //改
    @FXML
    private TextField BankCode_update;
    @FXML
    private TextField BankName_update;


    //查
    @FXML TextField BankCode_select;
    @FXML TextField BankName_select;

    //删
    @FXML
    private TextField BankCode_remove;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Bank> list = FXCollections.observableArrayList();

    public BankControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BankCode.setCellValueFactory(new PropertyValueFactory<Bank, String>("BankCode"));
        BankName.setCellValueFactory(new PropertyValueFactory<Bank, String>("BankName"));
        BankData("select * from Bank");
        tableView.setItems(list);

    }

    public void BankData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Bank(
                        rs.getString("BankCode"),
                        rs.getString("BankName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String BankCode__ = BankCode_add.getText();
        String BankName__ = BankName_add.getText();

        List<Bank> addlist = new ArrayList<Bank>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Bank values (?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(BankCode__);
        params.add(BankName__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        BankData("select * from Bank");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    BankCode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String BankCode){
        String sql = "update Bank set "+column+" = ? where BankCode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(BankCode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            BankData("select * from Bank");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String BankCode__ = BankCode_update.getText();
        String BankName__ = BankName_update.getText();

        boolean flage=false;

        if(BankCode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(BankName__.equals("")!=true){
                updateOne("BankName",BankName__,BankCode__);
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
        String BankCode__ = BankCode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Bank where BankCode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(BankCode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            BankData("select * from Bank");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String BankCode__ = BankCode_select.getText();
        String BankName__ = BankName_select.getText();

        list.clear();

        if(BankCode__.equals("")!=true){
            BankData("select *from Bank where BankCode='"+BankCode__+"'");
        }else if(BankName__.equals("")!=true){
            BankData("select *from Bank where BankName='"+BankName__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML
    protected void back(){
        list.clear();
        BankData("select * from Bank");
    }
}
