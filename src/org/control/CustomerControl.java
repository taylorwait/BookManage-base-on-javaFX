package org.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jdbc.JdbcUtils;
import org.model.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Created by 泰扁扁 on 2017/5/17.
 */
public class CustomerControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> ccode;
    @FXML
    private TableColumn<Customer, String> cname;
    @FXML
    private TableColumn<Customer, String> ctel;
    @FXML
    private TableColumn<Customer, String> csex;
    @FXML
    private TableColumn<Customer, String> caddress;
    @FXML
    private TableColumn<Customer, String> isvip;

    //增
    @FXML
    private TextField ccode_;
    @FXML
    private TextField cname_;
    @FXML
    private TextField ctel_;
    @FXML
    private TextField csex_;
    @FXML
    private TextField caddress_;
    @FXML
    private TextField isvip_;

    //改
    @FXML
    private TextField ccode_update;
    @FXML
    private TextField cname_update;
    @FXML
    private TextField ctel_update;
    @FXML
    private TextField csex_update;
    @FXML
    private TextField caddress_update;
    @FXML
    private TextField isvip_update;

    //删
    @FXML
    private TextField ccode_remove;

    //查
    @FXML TextField ccode_select;
    @FXML TextField cname_select;
    @FXML TextField ctel_select;
    @FXML TextField csex_select;
    @FXML TextField caddress_select;
    @FXML TextField isvip_select;


    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Customer> list = FXCollections.observableArrayList();

    public CustomerControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ccode.setCellValueFactory(new PropertyValueFactory<Customer, String>("ccode"));
        cname.setCellValueFactory(new PropertyValueFactory<Customer, String>("cname"));
        ctel.setCellValueFactory(new PropertyValueFactory<Customer, String>("ctel"));
        csex.setCellValueFactory(new PropertyValueFactory<Customer, String>("csex"));
        caddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("caddress"));
        isvip.setCellValueFactory(new PropertyValueFactory<Customer, String>("isvip"));
        customerData("select * from Customer");
        tableView.setItems(list);

    }

    public void customerData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Customer(
                        rs.getString("Ccode"),
                        rs.getString("Cname"),
                        rs.getString("Ctel"),
                        rs.getString("Csex"),
                        rs.getString("Caddress"),
                        rs.getString("Isvip")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String ccode__ = ccode_.getText();
        String cname__ = cname_.getText();
        String ctel__ = ctel_.getText();
        String csex__ = csex_.getText();
        String caddress__ = caddress_.getText();
        String isvip__ = isvip_.getText();

        List<Customer> addlist = new ArrayList<Customer>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        /*String sql ="insert into customer values(?,?,?,?,?,?)";
        try{
            ps=conn.prepareStatement(sql);
            ps.setString(1,ccode__);
            ps.setString(2,cname__);
            ps.setString(3,ctel__);
            ps.setString(4,csex__);
            ps.setString(5,caddress__);
            ps.setString(6,isvip__);
            ps.executeUpdate();
            customerData();
        }catch (SQLException e){
            e.printStackTrace();
        }*/
        String sql = "insert into customer values (?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(ccode__);
        params.add(cname__);
        params.add(ctel__);
        params.add(csex__);
        params.add(caddress__);
        params.add(isvip__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        customerData("select * from customer");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    ccode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String ccode){
        String sql = "update customer set "+column+" = ? where ccode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(ccode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            customerData("select * from customer");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String ccode__ = ccode_update.getText();
        String cname__ = cname_update.getText();
        String ctel__ = ctel_update.getText();
        String csex__ = csex_update.getText();
        String caddress__ = caddress_update.getText();
        String isvip__ = isvip_update.getText();

        boolean flage=false;

        if(ccode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(cname__.equals("")!=true){
                updateOne("cname",cname__,ccode__);
                flage=true;
            }
            if(ctel__.equals("")!=true){
                updateOne("ctel",ctel__,ccode__);
                flage=true;
            }
            if(csex__.equals("")!=true){
                updateOne("csex",csex__,ccode__);
                flage=true;
            }
            if(caddress__.equals("")!=true){
                updateOne("caddress",caddress__,ccode__);
                flage=true;
            }
            if(isvip__.equals("")!=true){
                updateOne("isvip",isvip__,ccode__);
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
        String ccode__ = ccode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from customer where ccode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(ccode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            customerData("select * from customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String ccode__ = ccode_select.getText();
        String cname__ = cname_select.getText();
        String ctel__ = ctel_select.getText();
        String csex__ = csex_select.getText();
        String caddress__ = caddress_select.getText();
        String isvip__ = isvip_select.getText();

        list.clear();

        if(ccode__.equals("")!=true){
            customerData("select *from customer where ccode='"+ccode__+"'");
        }else if(cname__.equals("")!=true){
            customerData("select *from customer where cname='"+cname__+"'");
        }else if(ctel__.equals("")!=true){
            customerData("select *from customer where ctel='"+ctel__+"'");
        }else if(csex__.equals("")!=true){
            customerData("select *from customer where csex='"+csex__+"'");
        }else if(caddress__.equals("")!=true){
            customerData("select *from customer where caddress='"+caddress__+"'");
        }else if(isvip__.equals("")!=true){
            customerData("select *from customer where isvip='"+isvip__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        customerData("select * from customer");
    }
}

