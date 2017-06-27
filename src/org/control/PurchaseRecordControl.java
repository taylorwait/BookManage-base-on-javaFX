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
import org.model.PurchaseRecord;

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
public class PurchaseRecordControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<PurchaseRecord> tableView;

    @FXML
    private TableColumn<PurchaseRecord, String> Purchasecode;
    @FXML
    private TableColumn<PurchaseRecord, String> Bcode;
    @FXML
    private TableColumn<PurchaseRecord, Integer> Pamount;
    @FXML
    private TableColumn<PurchaseRecord,Float> PTprice;
    @FXML
    private TableColumn<PurchaseRecord,String> Pdate;
    @FXML
    private TableColumn<PurchaseRecord,String> Ecode;
    @FXML
    private TableColumn<PurchaseRecord, String> SupplierCode;

    //增
    @FXML
    private TextField Purchasecode_add;
    @FXML
    private TextField Bcode_add;
    @FXML
    private TextField Pamount_add;
    @FXML
    private TextField PTprice_add;
    @FXML
    private TextField Pdate_add;
    @FXML
    private TextField Ecode_add;
    @FXML
    private TextField SupplierCode_add;

    //改
    @FXML
    private TextField Purchasecode_update;
    @FXML
    private TextField Bcode_update;
    @FXML
    private TextField Pamount_update;
    @FXML
    private TextField PTprice_update;
    @FXML
    private TextField Pdate_update;
    @FXML
    private TextField Ecode_update;
    @FXML
    private TextField SupplierCode_update;

    //删
    @FXML
    private TextField Purchasecode_remove;

    //查
    @FXML TextField Purchasecode_select;
    @FXML TextField Bcode_select;
    @FXML TextField Pamount_select;
    @FXML TextField PTprice_select;
    @FXML TextField Pdate_select;
    @FXML TextField Ecode_select;
    @FXML TextField SupplierCode_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<PurchaseRecord> list = FXCollections.observableArrayList();

    public PurchaseRecordControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Purchasecode.setCellValueFactory(new PropertyValueFactory<PurchaseRecord, String>("Purchasecode"));
        Bcode.setCellValueFactory(new PropertyValueFactory<PurchaseRecord, String>("Bcode"));
        Pamount.setCellValueFactory(new PropertyValueFactory<PurchaseRecord, Integer>("Pamount"));
        PTprice.setCellValueFactory(new PropertyValueFactory<PurchaseRecord,Float>("PTprice"));
        Pdate.setCellValueFactory(new PropertyValueFactory<PurchaseRecord,String>("Pdate"));
        Ecode.setCellValueFactory(new PropertyValueFactory<PurchaseRecord,String>("Ecode"));
        SupplierCode.setCellValueFactory(new PropertyValueFactory<PurchaseRecord, String>("SupplierCode"));

        PurchaseRecordData("select * from PurchaseRecord");
        tableView.setItems(list);

    }

    public void PurchaseRecordData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new PurchaseRecord(
                        rs.getString("Purchasecode"),
                        rs.getString("Bcode"),
                        rs.getInt("Pamount"),
                        rs.getFloat("PTprice"),
                        rs.getDate("Pdate"),
                        rs.getString("Ecode"),
                        rs.getString("SupplierCode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Purchasecode__ = Purchasecode_add.getText();
        String Bcode__ = Bcode_add.getText();
        String Pamount__ = Pamount_add.getText();
        String PTprice__=PTprice_add.getText();
        String Pdate__=Pdate_add.getText();
        String Ecode__=Ecode_add.getText();
        String SupplierCode__ = SupplierCode_add.getText();

        List<PurchaseRecord> addlist = new ArrayList<PurchaseRecord>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into PurchaseRecord values (?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Purchasecode__);
        params.add(Bcode__);
        params.add(Pamount__);
        params.add(PTprice__);
        params.add(Pdate__);
        params.add(Ecode__);
        params.add(SupplierCode__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        PurchaseRecordData("select * from PurchaseRecord");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Purchasecode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Purchasecode){
        String sql = "update PurchaseRecord set "+column+" = ? where Purchasecode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Purchasecode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            PurchaseRecordData("select * from PurchaseRecord");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Purchasecode__ = Purchasecode_update.getText();
        String Bcode__ = Bcode_update.getText();
        String Pamount__ = Pamount_update.getText();
        String PTprice__=PTprice_update.getText();
        String Pdate__=Pdate_update.getText();
        String Ecode__=Ecode_update.getText();
        String SupplierCode__ = SupplierCode_update.getText();

        boolean flage=false;

        if(Purchasecode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Bcode__.equals("")!=true){
                updateOne("Bcode",Bcode__,Purchasecode__);
                flage=true;
            }
            if(Pamount__.equals("")!=true){
                updateOne("Pamount",Pamount__,Purchasecode__);
                flage=true;
            }
            if(SupplierCode__.equals("")!=true){
                updateOne("SupplierCode",SupplierCode__,Purchasecode__);
                flage=true;
            }
            if(PTprice__.equals("")!=true){
                updateOne("PTprice",PTprice__,Purchasecode__);
            }
            if(Pdate__.equals("")!=true){
                updateOne("Pdate",Pdate__,Purchasecode__);
            }
            if(Ecode__.equals("")!=true){
                updateOne("Ecode",Ecode__,Purchasecode__);
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
        String Purchasecode__ = Purchasecode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from PurchaseRecord where Purchasecode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Purchasecode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            PurchaseRecordData("select * from PurchaseRecord");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Purchasecode__ = Purchasecode_select.getText();
        String Bcode__ = Bcode_select.getText();
        String Pamount__ = Pamount_select.getText();
        String PTprice__ = PTprice_select.getText();
        String Pdate__ = Pdate_select.getText();
        String Ecode__ = Ecode_select.getText();
        String SupplierCode__ = SupplierCode_select.getText();


        list.clear();

        if(Purchasecode__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where Purchasecode='"+Purchasecode__+"'");
        }else if(Bcode__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where Bcode='"+Bcode__+"'");
        }else if(Pamount__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where Pamount='"+Pamount__+"'");
        }else if(PTprice__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where PTprice='"+PTprice__+"'");
        }else if(Pdate__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where Pdate='"+Pdate__+"'");
        }else if(Ecode__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where Ecode='"+Ecode__+"'");
        }else if(SupplierCode__.equals("")!=true){
            PurchaseRecordData("select *from PurchaseRecord where SupplierCode='"+SupplierCode__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML
    protected void back(){
        list.clear();
        PurchaseRecordData("select * from PurchaseRecord");
    }
}
