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
import org.model.AfterSaleRecord;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by 泰扁扁 on 2017/5/28.
 */
public class AftersalerecordControl  implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<AfterSaleRecord> tableView;

    @FXML
    private TableColumn<AfterSaleRecord, String> AfterSaleRecord;
    @FXML
    private TableColumn<AfterSaleRecord, String> Ccode;
    @FXML
    private TableColumn<AfterSaleRecord, String> AfterSaleReason;
    @FXML
    private TableColumn<AfterSaleRecord, String> AfterSaleDeal;
    @FXML
    private TableColumn<AfterSaleRecord, String> AfterSaleDate;
    @FXML
    private TableColumn<AfterSaleRecord, String> Ecode;

    //增
    @FXML
    private TextField AfterSaleRecord_add;
    @FXML
    private TextField Ccode_add;
    @FXML
    private TextField AfterSaleReason_add;
    @FXML
    private TextField AfterSaleDeal_add;
    @FXML
    private TextField AfterSaleDate_add;
    @FXML
    private TextField Ecode_add;

    //改
    @FXML
    private TextField AfterSaleRecord_update;
    @FXML
    private TextField Ccode_update;
    @FXML
    private TextField AfterSaleReason_update;
    @FXML
    private TextField AfterSaleDeal_update;
    @FXML
    private TextField AfterSaleDate_update;
    @FXML
    private TextField Ecode_update;

    //删
    @FXML
    private TextField AfterSaleRecord_remove;

    //查
    @FXML TextField AfterSaleRecord_select;
    @FXML TextField Ccode_select;
    @FXML TextField AfterSaleReason_select;
    @FXML TextField AfterSaleDeal_select;
    @FXML TextField AfterSaleDate_select;
    @FXML TextField Ecode_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<AfterSaleRecord> list = FXCollections.observableArrayList();

    public AftersalerecordControl(){

    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AfterSaleRecord.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("AfterSaleRecord"));
        Ccode.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("Ccode"));
        AfterSaleReason.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("AfterSaleReason"));
        AfterSaleDeal.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("AfterSaleDeal"));
        AfterSaleDate.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("AfterSaleDate"));
        Ecode.setCellValueFactory(new PropertyValueFactory<AfterSaleRecord, String>("Ecode"));
        AfterSaleRecordData("select * from AfterSaleRecord");
        tableView.setItems(list);

    }

    public void AfterSaleRecordData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new AfterSaleRecord(
                        rs.getString("AfterSaleRecord"),
                        rs.getString("Ccode"),
                        rs.getString("AfterSaleReason"),
                        rs.getString("AfterSaleDeal"),
                        rs.getDate("AfterSaleDate"),
                        rs.getString("Ecode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String AfterSaleRecord__ = AfterSaleRecord_add.getText();
        String Ccode__ = Ccode_add.getText();
        String AfterSaleReason__ = AfterSaleReason_add.getText();
        String AfterSaleDeal__ = AfterSaleDeal_add.getText();
        String AfterSaleDate__ = AfterSaleDate_add.getText();
        String Ecode__ = Ecode_add.getText();

        List<AfterSaleRecord> addlist = new ArrayList<AfterSaleRecord>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into AfterSaleRecord values (?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(AfterSaleRecord__);
        params.add(Ccode__);
        params.add(AfterSaleReason__);
        params.add(AfterSaleDeal__);
        params.add(AfterSaleDate__);
        params.add(Ecode__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        AfterSaleRecordData("select * from AfterSaleRecord");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    AfterSaleRecord 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String AfterSaleRecord){
        String sql = "update AfterSaleRecord set "+column+" = ? where AfterSaleRecord = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(AfterSaleRecord);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            AfterSaleRecordData("select * from AfterSaleRecord");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String AfterSaleRecord__ = AfterSaleRecord_update.getText();
        String Ccode__ = Ccode_update.getText();
        String AfterSaleReason__ = AfterSaleReason_update.getText();
        String AfterSaleDeal__ = AfterSaleDeal_update.getText();
        String AfterSaleDate__ = AfterSaleDate_update.getText();
        String Ecode__ = Ecode_update.getText();

        boolean flage=false;

        if(AfterSaleRecord__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Ccode__.equals("")!=true){
                updateOne("Ccode",Ccode__,AfterSaleRecord__);
                flage=true;
            }
            if(AfterSaleReason__.equals("")!=true){
                updateOne("AfterSaleReason",AfterSaleReason__,AfterSaleRecord__);
                flage=true;
            }
            if(AfterSaleDeal__.equals("")!=true){
                updateOne("AfterSaleDeal",AfterSaleDeal__,AfterSaleRecord__);
                flage=true;
            }
            if(AfterSaleDate__.equals("")!=true){
                updateOne("AfterSaleDate",AfterSaleDate__,AfterSaleRecord__);
                flage=true;
            }
            if(Ecode__.equals("")!=true){
                updateOne("Ecode",Ecode__,AfterSaleRecord__);
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
        String AfterSaleRecord__ = AfterSaleRecord_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from AfterSaleRecord where AfterSaleRecord = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(AfterSaleRecord__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            AfterSaleRecordData("select * from AfterSaleRecord");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String AfterSaleRecord__ = AfterSaleRecord_select.getText();
        String Ccode__ = Ccode_select.getText();
        String AfterSaleReason__ = AfterSaleReason_select.getText();
        String AfterSaleDeal__ = AfterSaleDeal_select.getText();
        String AfterSaleDate__ = AfterSaleDate_select.getText();
        String Ecode__ = Ecode_select.getText();

        list.clear();

        if(AfterSaleRecord__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where AfterSaleRecord='"+AfterSaleRecord__+"'");
        }else if(Ccode__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where Ccode='"+Ccode__+"'");
        }else if(AfterSaleReason__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where AfterSaleReason='"+AfterSaleReason__+"'");
        }else if(AfterSaleDeal__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where AfterSaleDeal='"+AfterSaleDeal__+"'");
        }else if(AfterSaleDate__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where AfterSaleDate='"+AfterSaleDate__+"'");
        }else if(Ecode__.equals("")!=true){
            AfterSaleRecordData("select *from AfterSaleRecord where Ecode='"+Ecode__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        AfterSaleRecordData("select * from AfterSaleRecord");
    }
}
