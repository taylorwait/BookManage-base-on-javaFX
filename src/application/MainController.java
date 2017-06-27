package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{
    @FXML private TableView<info> table;
    @FXML private TableColumn<info,Integer> id;
    @FXML private TableColumn<info,String> url;
    @FXML private TableColumn<info,Integer> code;
    @FXML private TableColumn<info,String> server;
    @FXML private TableColumn<info,String> title;


    public ObservableList<info> List = FXCollections.observableArrayList(

            new info(1, "http://www.baidu.com", 200,"nginx", "baidu")


    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        id.setCellValueFactory(new PropertyValueFactory<info,Integer>("id"));
        url.setCellValueFactory(new PropertyValueFactory<info,String>("url"));
        code.setCellValueFactory(new PropertyValueFactory<info,Integer>("code"));
        server.setCellValueFactory(new PropertyValueFactory<info,String>("server"));
        title.setCellValueFactory(new PropertyValueFactory<info,String>("title"));


        table.setItems(List);

    }


}