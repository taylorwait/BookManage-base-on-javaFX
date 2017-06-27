package org.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.jdbc.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.main.Main.*;

/**
 * Created by 泰扁扁 on 2017/5/12.
 */
public class LoginControl implements ControlledStage{
    StageController myController;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @FXML
    private Text actiontarget;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException,SQLException{
        if(isLogin()){
            stageController.setStage(homeViewID,loginViewID);
        }
        else{
            actiontarget.setText("  鸟人！用户名不存在或密码错误！");
        }

    }



    //验证用户名密码
    public boolean isLogin() throws SQLException{
        String passWord= passwordField.getText();
        String userName=usernameField.getText();

        //建立数据库查询
        JdbcUtils jdbcUtils=new JdbcUtils();
        conn= jdbcUtils.getConnection();
        String sql="select * from Login";
        rs=jdbcUtils.findSimpleResult(sql);

        while(rs.next()){
            String userName_=rs.getString("Ecode").trim();
            String passWord_=rs.getString("Pword").trim();

            if(userName.equals(userName_)&&passWord.equals(passWord_)){
                return true;
            }
        }
        return false;
    }
}
