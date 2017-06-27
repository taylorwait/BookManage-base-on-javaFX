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
import org.model.PromotionDetail;

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
public class PromotionDetailControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<PromotionDetail> tableView;

    @FXML
    private TableColumn<PromotionDetail, String> PromotionCode;
    @FXML
    private TableColumn<PromotionDetail, String> Bcode;
    @FXML
    private TableColumn<PromotionDetail, String> Pdate;
    @FXML
    private TableColumn<PromotionDetail, String> Ptype;
    @FXML
    private TableColumn<PromotionDetail, String> isCoexist;


    //增
    @FXML
    private TextField PromotionCode_add;
    @FXML
    private TextField Bcode_add;
    @FXML
    private TextField Pdate_add;
    @FXML
    private TextField Ptype_add;
    @FXML
    private TextField isCoexist_add;


    //改
    @FXML
    private TextField PromotionCode_update;
    @FXML
    private TextField Bcode_update;
    @FXML
    private TextField Pdate_update;
    @FXML
    private TextField Ptype_update;
    @FXML
    private TextField isCoexist_update;


    //删
    @FXML
    private TextField PromotionCode_remove;

    //查
    @FXML TextField PromotionCode_select;
    @FXML TextField Bcode_select;
    @FXML TextField Pdate_select;
    @FXML TextField Ptype_select;
    @FXML TextField isCoexist_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<PromotionDetail> list = FXCollections.observableArrayList();

    public PromotionDetailControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PromotionCode.setCellValueFactory(new PropertyValueFactory<PromotionDetail, String>("PromotionCode"));
        Bcode.setCellValueFactory(new PropertyValueFactory<PromotionDetail, String>("Bcode"));
        Pdate.setCellValueFactory(new PropertyValueFactory<PromotionDetail, String>("Pdate"));
        Ptype.setCellValueFactory(new PropertyValueFactory<PromotionDetail, String>("Ptype"));
        isCoexist.setCellValueFactory(new PropertyValueFactory<PromotionDetail, String>("isCoexist"));
        PromotionDetailData("select * from PromotionDetail");
        tableView.setItems(list);

    }

    public void PromotionDetailData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new PromotionDetail(
                        rs.getString("PromotionCode"),
                        rs.getString("Bcode"),
                        rs.getDate("Pdate"),
                        rs.getString("Ptype"),
                        rs.getString("isCoexist")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String PromotionCode__ = PromotionCode_add.getText();
        String Bcode__ = Bcode_add.getText();
        String Pdate__ = Pdate_add.getText();
        String Ptype__ = Ptype_add.getText();
        String isCoexist__ = isCoexist_add.getText();

        List<PromotionDetail> addlist = new ArrayList<PromotionDetail>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into PromotionDetail values (?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(PromotionCode__);
        params.add(Bcode__);
        params.add(Pdate__);
        params.add(Ptype__);
        params.add(isCoexist__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        PromotionDetailData("select * from PromotionDetail");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    PromotionCode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String PromotionCode){
        String sql = "update PromotionDetail set "+column+" = ? where PromotionCode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(PromotionCode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            PromotionDetailData("select * from PromotionDetail");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String PromotionCode__ = PromotionCode_update.getText();
        String Bcode__ = Bcode_update.getText();
        String Pdate__ = Pdate_update.getText();
        String Ptype__ = Ptype_update.getText();
        String isCoexist__ = isCoexist_update.getText();

        boolean flage=false;

        if(PromotionCode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Bcode__.equals("")!=true){
                updateOne("Bcode",Bcode__,PromotionCode__);
                flage=true;
            }
            if(Pdate__.equals("")!=true){
                updateOne("Pdate",Pdate__,PromotionCode__);
                flage=true;
            }
            if(Ptype__.equals("")!=true){
                updateOne("Ptype",Ptype__,PromotionCode__);
                flage=true;
            }
            if(isCoexist__.equals("")!=true){
                updateOne("isCoexist",isCoexist__,PromotionCode__);
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
        String PromotionCode__ = PromotionCode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from PromotionDetail where PromotionCode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(PromotionCode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            PromotionDetailData("select * from PromotionDetail");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String PromotionCode__ = PromotionCode_select.getText();
        String Bcode__ = Bcode_select.getText();
        String Pdate__ = Pdate_select.getText();
        String Ptype__ = Ptype_select.getText();
        String isCoexist__ = isCoexist_select.getText();

        list.clear();

        if(PromotionCode__.equals("")!=true){
            PromotionDetailData("select *from PromotionDetail where PromotionCode='"+PromotionCode__+"'");
        }else if(Bcode__.equals("")!=true){
            PromotionDetailData("select *from PromotionDetail where Bcode='"+Bcode__+"'");
        }else if(Pdate__.equals("")!=true){
            PromotionDetailData("select *from PromotionDetail where Pdate='"+Pdate__+"'");
        }else if(Ptype__.equals("")!=true){
            PromotionDetailData("select *from PromotionDetail where Ptype='"+Ptype__+"'");
        }else if(isCoexist__.equals("")!=true){
            PromotionDetailData("select *from PromotionDetail where isCoexist='"+isCoexist__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML
    protected void back(){
        list.clear();
        PromotionDetailData("select * from PromotionDetail");
    }
}
