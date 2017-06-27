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
import org.model.Employee;

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
public class EmployeeControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> Ecode;
    @FXML
    private TableColumn<Employee, String> Ename;
    @FXML
    private TableColumn<Employee, String> Etel;
    @FXML
    private TableColumn<Employee, String> Esex;
    @FXML
    private TableColumn<Employee, String> Eaddress;
    @FXML
    private TableColumn<Employee, String> Eduty;

    //增
    @FXML
    private TextField Ecode_add;
    @FXML
    private TextField Ename_add;
    @FXML
    private TextField Etel_add;
    @FXML
    private TextField Esex_add;
    @FXML
    private TextField Eaddress_add;
    @FXML
    private TextField Eduty_add;

    //改
    @FXML
    private TextField Ecode_update;
    @FXML
    private TextField Ename_update;
    @FXML
    private TextField Etel_update;
    @FXML
    private TextField Esex_update;
    @FXML
    private TextField Eaddress_update;
    @FXML
    private TextField Eduty_update;

    //删
    @FXML
    private TextField Ecode_remove;

    //查
    @FXML TextField Ecode_select;
    @FXML TextField Ename_select;
    @FXML TextField Etel_select;
    @FXML TextField Esex_select;
    @FXML TextField Eaddress_select;
    @FXML TextField Eduty_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<Employee> list = FXCollections.observableArrayList();

    public EmployeeControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Ecode.setCellValueFactory(new PropertyValueFactory<Employee, String>("Ecode"));
        Ename.setCellValueFactory(new PropertyValueFactory<Employee, String>("Ename"));
        Etel.setCellValueFactory(new PropertyValueFactory<Employee, String>("Etel"));
        Esex.setCellValueFactory(new PropertyValueFactory<Employee, String>("Esex"));
        Eaddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("Eaddress"));
        Eduty.setCellValueFactory(new PropertyValueFactory<Employee, String>("Eduty"));
        EmployeeData("select * from Employee");
        tableView.setItems(list);

    }

    public void EmployeeData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new Employee(
                        rs.getString("Ecode"),
                        rs.getString("Ename"),
                        rs.getString("Etel"),
                        rs.getString("Esex"),
                        rs.getString("Eaddress"),
                        rs.getString("Eduty")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //add
    @FXML
    protected void add(ActionEvent event) throws SQLException {
        String Ecode__ = Ecode_add.getText();
        String Ename__ = Ename_add.getText();
        String Etel__ = Etel_add.getText();
        String Esex__ = Esex_add.getText();
        String Eaddress__ = Eaddress_add.getText();
        String Eduty__ = Eduty_add.getText();

        List<Employee> addlist = new ArrayList<Employee>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into Employee values (?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Ecode__);
        params.add(Ename__);
        params.add(Etel__);
        params.add(Esex__);
        params.add(Eaddress__);
        params.add(Eduty__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        EmployeeData("select * from Employee");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Ecode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Ecode){
        String sql = "update Employee set "+column+" = ? where Ecode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Ecode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            EmployeeData("select * from Employee");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Ecode__ = Ecode_update.getText();
        String Ename__ = Ename_update.getText();
        String Etel__ = Etel_update.getText();
        String Esex__ = Esex_update.getText();
        String Eaddress__ = Eaddress_update.getText();
        String Eduty__ = Eduty_update.getText();

        boolean flage=false;

        if(Ecode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Ename__.equals("")!=true){
                updateOne("Ename",Ename__,Ecode__);
                flage=true;
            }
            if(Etel__.equals("")!=true){
                updateOne("Etel",Etel__,Ecode__);
                flage=true;
            }
            if(Esex__.equals("")!=true){
                updateOne("Esex",Esex__,Ecode__);
                flage=true;
            }
            if(Eaddress__.equals("")!=true){
                updateOne("Eaddress",Eaddress__,Ecode__);
                flage=true;
            }
            if(Eduty__.equals("")!=true){
                updateOne("Eduty",Eduty__,Ecode__);
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
        String Ecode__ = Ecode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from Employee where Ecode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Ecode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            EmployeeData("select * from Employee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Ecode__ = Ecode_select.getText();
        String Ename__ = Ename_select.getText();
        String Etel__ = Etel_select.getText();
        String Esex__ = Esex_select.getText();
        String Eaddress__ = Eaddress_select.getText();
        String Eduty__ = Eduty_select.getText();

        list.clear();

        if(Ecode__.equals("")!=true){
            EmployeeData("select *from Employee where Ecode='"+Ecode__+"'");
        }else if(Ename__.equals("")!=true){
            EmployeeData("select *from Employee where Ename='"+Ename__+"'");
        }else if(Etel__.equals("")!=true){
            EmployeeData("select *from Employee where Etel='"+Etel__+"'");
        }else if(Esex__.equals("")!=true){
            EmployeeData("select *from Employee where Esex='"+Esex__+"'");
        }else if(Eaddress__.equals("")!=true){
            EmployeeData("select *from Employee where Eaddress='"+Eaddress__+"'");
        }else if(Eduty__.equals("")!=true){
            EmployeeData("select *from Employee where Eduty='"+Eduty__+"'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        EmployeeData("select * from Employee");
    }
}

