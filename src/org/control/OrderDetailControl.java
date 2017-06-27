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
import org.model.OrderDetail;

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
public class OrderDetailControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<OrderDetail> tableView;

    @FXML
    private TableColumn<OrderDetail, String> Ocode;
    @FXML
    private TableColumn<OrderDetail, String> Odate;
    @FXML
    private TableColumn<OrderDetail, String> BankCode;
    @FXML
    private TableColumn<OrderDetail,Float> Tpayment;
    @FXML
    private TableColumn<OrderDetail,String> ExpriyDate;
    @FXML
    private TableColumn<OrderDetail,String> PmethodCode;

    //增
    @FXML
    private TextField Ocode_add;
    @FXML
    private TextField Odate_add;
    @FXML
    private TextField BankCode_add;
    @FXML
    private TextField Tpayment_add;
    @FXML
    private TextField ExpriyDate_add;
    @FXML
    private TextField PmethodCode_add;

    //改
    @FXML
    private TextField Ocode_update;
    @FXML
    private TextField Odate_update;
    @FXML
    private TextField BankCode_update;
    @FXML
    private TextField Tpayment_update;
    @FXML
    private TextField ExpriyDate_update;
    @FXML
    private TextField PmethodCode_update;

    //删
    @FXML
    private TextField Ocode_remove;

    //查
    @FXML TextField Ocode_select;
    @FXML TextField Odate_select;
    @FXML TextField BankCode_select;
    @FXML TextField Tpayment_select;
    @FXML TextField ExpriyDate_select;
    @FXML TextField PmethodCode_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<OrderDetail> list = FXCollections.observableArrayList();

    public OrderDetailControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Ocode.setCellValueFactory(new PropertyValueFactory<OrderDetail, String>("Ocode"));
        Odate.setCellValueFactory(new PropertyValueFactory<OrderDetail, String>("Odate"));
        BankCode.setCellValueFactory(new PropertyValueFactory<OrderDetail, String>("BankCode"));
        Tpayment.setCellValueFactory(new PropertyValueFactory<OrderDetail,Float>("Tpayment"));
        ExpriyDate.setCellValueFactory(new PropertyValueFactory<OrderDetail,String>("ExpriyDate"));
        PmethodCode.setCellValueFactory(new PropertyValueFactory<OrderDetail,String>("PmethodCode"));

        OrderDetailData("select * from OrderDetail");
        tableView.setItems(list);

    }

    public void OrderDetailData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new OrderDetail(
                        rs.getString("Ocode"),
                        rs.getDate("Odate"),
                        rs.getString("BankCode"),
                        rs.getFloat("Tpayment"),
                        rs.getDate("ExpriyDate"),
                        rs.getString("PmethodCode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Ocode__ = Ocode_add.getText();
        String Odate__ = Odate_add.getText();
        String BankCode__ = BankCode_add.getText();
        String Tpayment__=Tpayment_add.getText();
        String ExpriyDate__=ExpriyDate_add.getText();
        String PmethodCode__=PmethodCode_add.getText();

        List<OrderDetail> addlist = new ArrayList<OrderDetail>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into OrderDetail values (?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Ocode__);
        params.add(Odate__);
        params.add(BankCode__);
        params.add(Tpayment__);
        params.add(ExpriyDate__);
        params.add(PmethodCode__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        OrderDetailData("select * from OrderDetail");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Ocode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Ocode){
        String sql = "update OrderDetail set "+column+" = ? where Ocode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Ocode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            OrderDetailData("select * from OrderDetail");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Ocode__ = Ocode_update.getText();
        String Odate__ = Odate_update.getText();
        String BankCode__ = BankCode_update.getText();
        String Tpayment__=Tpayment_update.getText();
        String ExpriyDate__=ExpriyDate_update.getText();
        String PmethodCode__=PmethodCode_update.getText();

        boolean flage=false;

        if(Ocode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Odate__.equals("")!=true){
                updateOne("Odate",Odate__,Ocode__);
                flage=true;
            }
            if(BankCode__.equals("")!=true){
                updateOne("BankCode",BankCode__,Ocode__);
                flage=true;
            }
            if(Tpayment__.equals("")!=true){
                updateOne("Tpayment",Tpayment__,Ocode__);
            }
            if(ExpriyDate__.equals("")!=true){
                updateOne("ExpriyDate",ExpriyDate__,Ocode__);
            }
            if(PmethodCode__.equals("")!=true){
                updateOne("PmethodCode",PmethodCode__,Ocode__);
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
        String Ocode__ = Ocode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from OrderDetail where Ocode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Ocode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            OrderDetailData("select * from OrderDetail");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Ocode__ = Ocode_select.getText();
        String Odate__ = Odate_select.getText();
        String BankCode__ = BankCode_select.getText();
        String Tpayment__ = Tpayment_select.getText();
        String ExpriyDate__ = ExpriyDate_select.getText();
        String PmethodCode__ = PmethodCode_select.getText();


        list.clear();

        if(Ocode__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where Ocode='"+Ocode__+"'");
        }else if(Odate__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where Odate='"+Odate__+"'");
        }else if(BankCode__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where BankCode='"+BankCode__+"'");
        }else if(Tpayment__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where Tpayment='"+Tpayment__+"'");
        }else if(ExpriyDate__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where ExpriyDate='"+ExpriyDate__+"'");
        }else if(PmethodCode__.equals("")!=true){
            OrderDetailData("select *from OrderDetail where PmethodCode='"+PmethodCode__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        OrderDetailData("select * from OrderDetail");
    }
}
