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
import org.model.ReturnRecord;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class ReturnRecordControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<ReturnRecord> tableView;

    @FXML
    private TableColumn<ReturnRecord, String> Rcode;
    @FXML
    private TableColumn<ReturnRecord, String> Bcode;
    @FXML
    private TableColumn<ReturnRecord, Integer> Ramount;
    @FXML
    private TableColumn<ReturnRecord,Float> RTprice;
    @FXML
    private TableColumn<ReturnRecord,String> Pdate;
    @FXML
    private TableColumn<ReturnRecord,String> Ecode;
    @FXML
    private TableColumn<ReturnRecord, String> SupplierCode;
    @FXML
    private TableColumn<ReturnRecord, String> Rtype;

    //增
    @FXML
    private TextField Rcode_add;
    @FXML
    private TextField Bcode_add;
    @FXML
    private TextField Ramount_add;
    @FXML
    private TextField RTprice_add;
    @FXML
    private TextField Pdate_add;
    @FXML
    private TextField Ecode_add;
    @FXML
    private TextField SupplierCode_add;
    @FXML
    private TextField Rtype_add;

    //改
    @FXML
    private TextField Rcode_update;
    @FXML
    private TextField Bcode_update;
    @FXML
    private TextField Ramount_update;
    @FXML
    private TextField RTprice_update;
    @FXML
    private TextField Pdate_update;
    @FXML
    private TextField Ecode_update;
    @FXML
    private TextField SupplierCode_update;
    @FXML
    private TextField Rtype_update;

    //删
    @FXML
    private TextField Rcode_remove;

    //查
    @FXML TextField Rcode_select;
    @FXML TextField Bcode_select;
    @FXML TextField Ramount_select;
    @FXML TextField RTprice_select;
    @FXML TextField Pdate_select;
    @FXML TextField Ecode_select;
    @FXML TextField SupplierCode_select;
    @FXML TextField Rtype_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<ReturnRecord> list = FXCollections.observableArrayList();

    public ReturnRecordControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rcode.setCellValueFactory(new PropertyValueFactory<ReturnRecord, String>("Rcode"));
        Bcode.setCellValueFactory(new PropertyValueFactory<ReturnRecord, String>("Bcode"));
        Ramount.setCellValueFactory(new PropertyValueFactory<ReturnRecord, Integer>("Ramount"));
        RTprice.setCellValueFactory(new PropertyValueFactory<ReturnRecord,Float>("RTprice"));
        Pdate.setCellValueFactory(new PropertyValueFactory<ReturnRecord,String>("Pdate"));
        Ecode.setCellValueFactory(new PropertyValueFactory<ReturnRecord,String>("Ecode"));
        SupplierCode.setCellValueFactory(new PropertyValueFactory<ReturnRecord, String>("SupplierCode"));
        Rtype.setCellValueFactory(new PropertyValueFactory<ReturnRecord, String>("Rtype"));

        ReturnRecordData("select * from ReturnRecord");
        tableView.setItems(list);

    }

    public void ReturnRecordData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new ReturnRecord(
                        rs.getString("Rcode"),
                        rs.getString("Bcode"),
                        rs.getInt("Ramount"),
                        rs.getFloat("RTprice"),
                        rs.getDate("Pdate"),
                        rs.getString("Ecode"),
                        rs.getString("SupplierCode"),
                        rs.getString("Rtype")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Rcode__ = Rcode_add.getText();
        String Bcode__ = Bcode_add.getText();
        String Ramount__ = Ramount_add.getText();
        String RTprice__=RTprice_add.getText();
        String Pdate__=Pdate_add.getText();
        String Ecode__=Ecode_add.getText();
        String SupplierCode__ = SupplierCode_add.getText();
        String Rtype__=Rtype_add.getText();

        List<ReturnRecord> addlist = new ArrayList<ReturnRecord>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into ReturnRecord values (?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Rcode__);
        params.add(Bcode__);
        params.add(Ramount__);
        params.add(RTprice__);
        params.add(Pdate__);
        params.add(Ecode__);
        params.add(SupplierCode__);
        params.add(Rtype__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        ReturnRecordData("select * from ReturnRecord");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Rcode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Rcode){
        String sql = "update ReturnRecord set "+column+" = ? where Rcode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Rcode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            ReturnRecordData("select * from ReturnRecord");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Rcode__ = Rcode_update.getText();
        String Bcode__ = Bcode_update.getText();
        String Ramount__ = Ramount_update.getText();
        String RTprice__=RTprice_update.getText();
        String Pdate__=Pdate_update.getText();
        String Ecode__=Ecode_update.getText();
        String SupplierCode__ = SupplierCode_update.getText();
        String Rtype__ = Rtype_update.getText();

        boolean flage=false;

        if(Rcode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Bcode__.equals("")!=true){
                updateOne("Bcode",Bcode__,Rcode__);
                flage=true;
            }
            if(Ramount__.equals("")!=true){
                updateOne("Ramount",Ramount__,Rcode__);
                flage=true;
            }
            if(SupplierCode__.equals("")!=true){
                updateOne("SupplierCode",SupplierCode__,Rcode__);
                flage=true;
            }
            if(RTprice__.equals("")!=true){
                updateOne("RTprice",RTprice__,Rcode__);
            }
            if(Pdate__.equals("")!=true){
                updateOne("Pdate",Pdate__,Rcode__);
            }
            if(Ecode__.equals("")!=true){
                updateOne("Ecode",Ecode__,Rcode__);
            }
            if(Rtype__.equals("")!=true){
                updateOne("Rtype",Rtype__,Rcode__);
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
        String Rcode__ = Rcode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from ReturnRecord where Rcode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Rcode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            ReturnRecordData("select * from ReturnRecord");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Rcode__ = Rcode_select.getText();
        String Bcode__ = Bcode_select.getText();
        String Ramount__ = Ramount_select.getText();
        String RTprice__ = RTprice_select.getText();
        String Pdate__ = Pdate_select.getText();
        String Ecode__ = Ecode_select.getText();
        String SupplierCode__ = SupplierCode_select.getText();
        String Rtype__ = Rtype_select.getText();


        list.clear();

        if(Rcode__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where Rcode='"+Rcode__+"'");
        }else if(Bcode__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where Bcode='"+Bcode__+"'");
        }else if(Ramount__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where Ramount='"+Ramount__+"'");
        }else if(RTprice__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where RTprice='"+RTprice__+"'");
        }else if(Pdate__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where Pdate='"+Pdate__+"'");
        }else if(Ecode__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where Ecode='"+Ecode__+"'");
        }else if(SupplierCode__.equals("")!=true){
            ReturnRecordData("select *from ReturnRecord where SupplierCode='"+SupplierCode__+"'");
        }else if(Rtype__.equals("")!=true) {
            ReturnRecordData("select *from ReturnRecord where Rtype='" + Rtype__ + "'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        ReturnRecordData("select * from ReturnRecord");
    }
}
