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
import org.model.Vip;
import org.model.Vip;

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
public class VipControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Vip> tableView;

    @FXML
    private TableColumn<Vip, String> Ccode;
    @FXML
    private TableColumn<Vip, String> Clevel;
    @FXML
    private TableColumn<Vip, String> Discount;


    //增
    @FXML
    private TextField Ccode_add;
    @FXML
    private TextField Clevel_add;
    @FXML
    private TextField Discount_add;

    //改
    @FXML
    private TextField Ccode_update;
    @FXML
    private TextField Clevel_update;
    @FXML
    private TextField Discount_update;

    //查
    @FXML TextField Ccode_select;
    @FXML TextField Clevel_select;
    @FXML TextField Discount_select;

    //删
    @FXML
    private TextField Ccode_remove;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Vip> list = FXCollections.observableArrayList();

    public VipControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Ccode.setCellValueFactory(new PropertyValueFactory<Vip, String>("Ccode"));
        Clevel.setCellValueFactory(new PropertyValueFactory<Vip, String>("Clevel"));
        Discount.setCellValueFactory(new PropertyValueFactory<Vip, String>("Discount"));
        VipData("select * from Vip");
        tableView.setItems(list);

    }

    public void VipData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Vip(
                        rs.getString("Ccode"),
                        rs.getString("Clevel"),
                        rs.getString("Discount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Ccode__ = Ccode_add.getText();
        String Clevel__ = Clevel_add.getText();
        String Discount__ = Discount_add.getText();

        List<Vip> addlist = new ArrayList<Vip>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Vip values (?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Ccode__);
        params.add(Clevel__);
        params.add(Discount__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        VipData("select * from Vip");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Ccode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Ccode){
        String sql = "update Vip set "+column+" = ? where Ccode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Ccode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            VipData("select * from Vip");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Ccode__ = Ccode_update.getText();
        String Clevel__ = Clevel_update.getText();
        String Discount__ = Discount_update.getText();

        boolean flage=false;

        if(Ccode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Clevel__.equals("")!=true){
                updateOne("Clevel",Clevel__,Ccode__);
                flage=true;
            }
            if(Discount__.equals("")!=true){
                updateOne("Discount",Discount__,Ccode__);
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
        String Ccode__ = Ccode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Vip where Ccode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Ccode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            VipData("select * from Vip");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Ccode__ = Ccode_select.getText();
        String Clevel__ = Clevel_select.getText();
        String Discount__ = Discount_select.getText();

        list.clear();

        if(Ccode__.equals("")!=true){
            VipData("select *from Vip where Ccode='"+Ccode__+"'");
        }else if(Clevel__.equals("")!=true){
            VipData("select *from Vip where Clevel='"+Clevel__+"'");
        }else if(Discount__.equals("")!=true){
            VipData("select *from Vip where Discount='"+Discount__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        VipData("select * from Vip");
    }
}

