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
import org.model.SoldNote;
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
public class SoldNoteControl implements ControlledStage,Initializable {
    StageController myController;

    @FXML
    private TableView<SoldNote> tableView;

    @FXML
    private TableColumn<SoldNote, String> Soldcode;
    @FXML
    private TableColumn<SoldNote, String> Bcode;
    @FXML
    private TableColumn<SoldNote, String> Ccode;
    @FXML
    private TableColumn<SoldNote,Integer> Samount;
    @FXML
    private TableColumn<SoldNote,Float> Sprice;
    @FXML
    private TableColumn<SoldNote,Float> STprice;
    @FXML
    private TableColumn<SoldNote, String> Sdate;
    @FXML
    private TableColumn<SoldNote, String> Ecode;

    //增
    @FXML
    private TextField Soldcode_add;
    @FXML
    private TextField Bcode_add;
    @FXML
    private TextField Ccode_add;
    @FXML
    private TextField Samount_add;
    @FXML
    private TextField Sprice_add;
    @FXML
    private TextField STprice_add;
    @FXML
    private TextField Sdate_add;
    @FXML
    private TextField Ecode_add;

    //改
    @FXML
    private TextField Soldcode_update;
    @FXML
    private TextField Bcode_update;
    @FXML
    private TextField Ccode_update;
    @FXML
    private TextField Samount_update;
    @FXML
    private TextField Sprice_update;
    @FXML
    private TextField STprice_update;
    @FXML
    private TextField Sdate_update;
    @FXML
    private TextField Ecode_update;

    //删
    @FXML
    private TextField Soldcode_remove;

    //查
    @FXML TextField Soldcode_select;
    @FXML TextField Bcode_select;
    @FXML TextField Ccode_select;
    @FXML TextField Samount_select;
    @FXML TextField Sprice_select;
    @FXML TextField STprice_select;
    @FXML TextField Sdate_select;
    @FXML TextField Ecode_select;

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;

    ObservableList<SoldNote> list = FXCollections.observableArrayList();

    public SoldNoteControl() {
    }

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Soldcode.setCellValueFactory(new PropertyValueFactory<SoldNote, String>("Soldcode"));
        Bcode.setCellValueFactory(new PropertyValueFactory<SoldNote, String>("Bcode"));
        Ccode.setCellValueFactory(new PropertyValueFactory<SoldNote, String>("Ccode"));
        Samount.setCellValueFactory(new PropertyValueFactory<SoldNote,Integer>("Samount"));
        Sprice.setCellValueFactory(new PropertyValueFactory<SoldNote,Float>("Sprice"));
        STprice.setCellValueFactory(new PropertyValueFactory<SoldNote,Float>("STprice"));
        Sdate.setCellValueFactory(new PropertyValueFactory<SoldNote, String>("Sdate"));
        Ecode.setCellValueFactory(new PropertyValueFactory<SoldNote, String>("Ecode"));

        SoldNoteData("select * from SoldNote");
        tableView.setItems(list);

    }

    public void SoldNoteData(String sql) {

        try {
            JdbcUtils jdbcUtils = new JdbcUtils();
            conn = jdbcUtils.getConnection();
            rs = jdbcUtils.findSimpleResult(sql);

            while (rs.next()) {
                list.add(new SoldNote(
                        rs.getString("Soldcode"),
                        rs.getString("Bcode"),
                        rs.getString("Ccode"),
                        rs.getInt("Samount"),
                        rs.getFloat("Sprice"),
                        rs.getFloat("STprice"),
                        rs.getDate("Sdate"),
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
        String Soldcode__ = Soldcode_add.getText();
        String Bcode__ = Bcode_add.getText();
        String Ccode__ = Ccode_add.getText();
        String Samount__=Samount_add.getText();
        String Sprice__=Sprice_add.getText();
        String STprice__=STprice_add.getText();
        String Sdate__ = Sdate_add.getText();
        String Ecode__=Ecode_add.getText();

        List<SoldNote> addlist = new ArrayList<SoldNote>();


        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        String sql = "insert into SoldNote values (?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(Soldcode__);
        params.add(Bcode__);
        params.add(Ccode__);
        params.add(Samount__);
        params.add(Sprice__);
        params.add(STprice__);
        params.add(Sdate__);
        params.add(Ecode__);
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        list.clear();
        SoldNoteData("select * from SoldNote");

        jdbcUtils.releaseConn();


    }

    //update
    /*
    column 需要修改的列
    param 修改的值
    Soldcode 需要修改的记录的主键
     */
    private void updateOne(String column,String param,String Soldcode){
        String sql = "update SoldNote set "+column+" = ? where Soldcode = ? ";
        List<Object> params = new ArrayList<Object>();
        params.add(param);
        params.add(Soldcode);

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();

        try{
            jdbcUtils.updateByPreparedStatement(sql, params);
            //重新展现
            list.clear();
            SoldNoteData("select * from SoldNote");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void update() {
        String Soldcode__ = Soldcode_update.getText();
        String Bcode__ = Bcode_update.getText();
        String Ccode__ = Ccode_update.getText();
        String Samount__=Samount_update.getText();
        String Sprice__=Sprice_update.getText();
        String STprice__=STprice_update.getText();
        String Sdate__ = Sdate_update.getText();
        String Ecode__ = Ecode_update.getText();

        boolean flage=false;

        if(Soldcode__.equals("")==true){
            Alert warning = new Alert(Alert.AlertType.WARNING, "请指定顾客编号！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }else{
            if(Bcode__.equals("")!=true){
                updateOne("Bcode",Bcode__,Soldcode__);
                flage=true;
            }
            if(Ccode__.equals("")!=true){
                updateOne("Ccode",Ccode__,Soldcode__);
                flage=true;
            }
            if(Sdate__.equals("")!=true){
                updateOne("Sdate",Sdate__,Soldcode__);
                flage=true;
            }
            if(Samount__.equals("")!=true){
                updateOne("Samount",Samount__,Soldcode__);
            }
            if(Sprice__.equals("")!=true){
                updateOne("Sprice",Sprice__,Soldcode__);
            }
            if(STprice__.equals("")!=true){
                updateOne("STprice",STprice__,Soldcode__);
            }
            if(Ecode__.equals("")!=true){
                updateOne("Ecode",Ecode__,Soldcode__);
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
        String Soldcode__ = Soldcode_remove.getText();

        JdbcUtils jdbcUtils = new JdbcUtils();
        conn = jdbcUtils.getConnection();
        String sql = "delete from SoldNote where Soldcode = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(Soldcode__);
        try {
            if(jdbcUtils.updateByPreparedStatement(sql, params)==false){
                Alert warning = new Alert(Alert.AlertType.WARNING, "查无本该记录，无法删除！");
                warning.setTitle("警告信息！");

                warning.showAndWait();
            }
            list.clear();
            SoldNoteData("select * from SoldNote");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtils.releaseConn();
    }


    //select
    @FXML protected void select(){
        String Soldcode__ = Soldcode_select.getText();
        String Bcode__ = Bcode_select.getText();
        String Ccode__ = Ccode_select.getText();
        String Samount__ = Samount_select.getText();
        String Sprice__ = Sprice_select.getText();
        String STprice__ = STprice_select.getText();
        String Sdate__ = Sdate_select.getText();
        String Ecode__ = Ecode_select.getText();


        list.clear();

        if(Soldcode__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Soldcode='"+Soldcode__+"'");
        }else if(Bcode__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Bcode='"+Bcode__+"'");
        }else if(Ccode__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Ccode='"+Ccode__+"'");
        }else if(Samount__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Samount='"+Samount__+"'");
        }else if(Sprice__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Sprice='"+Sprice__+"'");
        }else if(STprice__.equals("")!=true){
            SoldNoteData("select *from SoldNote where STprice='"+STprice__+"'");
        }else if(Sdate__.equals("")!=true){
            SoldNoteData("select *from SoldNote where Sdate='"+Sdate__+"'");
        }else if(Ecode__.equals("")!=true) {
            SoldNoteData("select *from SoldNote where Ecode='" + Ecode__ + "'");
        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING, "请至少输入一个待查数据！");
            warning.setTitle("警告信息！");
            warning.showAndWait();
        }

    }
    @FXML protected void back(){
        list.clear();
        SoldNoteData("select * from SoldNote");
    }
}
