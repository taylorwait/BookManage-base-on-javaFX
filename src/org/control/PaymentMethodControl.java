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
import org.model.PaymentMethod;

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
public class PaymentMethodControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<PaymentMethod> tableView;

    @FXML
    private TableColumn<PaymentMethod, String> PmethodCode;
    @FXML
    private TableColumn<PaymentMethod, String> PmethodName;
    @FXML
    private TableColumn<PaymentMethod, String> BankCode;


    //增
    @FXML
    private TextField PmethodCode_add;
    @FXML
    private TextField PmethodName_add;
    @FXML
    private TextField BankCode_add;

    //改
    @FXML
    private TextField PmethodCode_update;
    @FXML
    private TextField PmethodName_update;
    @FXML
    private TextField BankCode_update;

    //查
    @FXML TextField PmethodCode_select;
    @FXML TextField PmethodName_select;
    @FXML TextField BankCode_select;

    //删
    @FXML
    private TextField PmethodCode_remove;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<PaymentMethod> list = FXCollections.observableArrayList();

    public PaymentMethodControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PmethodCode.setCellValueFactory(new PropertyValueFactory<PaymentMethod, String>("PmethodCode"));
        PmethodName.setCellValueFactory(new PropertyValueFactory<PaymentMethod, String>("PmethodName"));
        BankCode.setCellValueFactory(new PropertyValueFactory<PaymentMethod, String>("BankCode"));
        PaymentMethodData("select * from PaymentMethod");
        tableView.setItems(list);

    }

    public void PaymentMethodData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new PaymentMethod(
                        rs.getString("PmethodCode"),
                        rs.getString("PmethodName"),
                        rs.getString("BankCode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String PmethodCode__ = PmethodCode_add.getText();
        String PmethodName__ = PmethodName_add.getText();
        String BankCode__ = BankCode_add.getText();

        List<PaymentMethod> addlist = new ArrayList<PaymentMethod>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into PaymentMethod values (?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(PmethodCode__);
        params.add(PmethodName__);
        params.add(BankCode__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        PaymentMethodData("select * from PaymentMethod");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    PmethodCode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String PmethodCode){
        String sql = "update PaymentMethod set "+column+" = ? where PmethodCode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(PmethodCode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            PaymentMethodData("select * from PaymentMethod");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String PmethodCode__ = PmethodCode_update.getText();
        String PmethodName__ = PmethodName_update.getText();
        String BankCode__ = BankCode_update.getText();

        boolean flage=false;

        if(PmethodCode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(PmethodName__.equals("")!=true){
                updateOne("PmethodName",PmethodName__,PmethodCode__);
                flage=true;
            }
            if(BankCode__.equals("")!=true){
                updateOne("BankCode",BankCode__,PmethodCode__);
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
        String PmethodCode__ = PmethodCode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from PaymentMethod where PmethodCode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(PmethodCode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            PaymentMethodData("select * from PaymentMethod");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String PmethodCode__ = PmethodCode_select.getText();
        String PmethodName__ = PmethodName_select.getText();
        String BankCode__ = BankCode_select.getText();

        list.clear();

        if(PmethodCode__.equals("")!=true){
            PaymentMethodData("select *from PaymentMethod where PmethodCode='"+PmethodCode__+"'");
        }else if(PmethodName__.equals("")!=true){
            PaymentMethodData("select *from PaymentMethod where PmethodName='"+PmethodName__+"'");
        }else if(BankCode__.equals("")!=true){
            PaymentMethodData("select *from PaymentMethod where BankCode='"+BankCode__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        PaymentMethodData("select * from PaymentMethod");
    }
}

