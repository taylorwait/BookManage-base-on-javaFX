package org.main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.control.StageController;

/**
 * Created by 泰扁扁 on 2017/5/13.
 */
public class Main extends Application{
    //登录
    public static String loginViewID="loginView";
    public static String loginViewRes="/org/login/loginView.fxml";
    //主界面
    public static String homeViewID="homeView";
    public static String homeViewRes="/org/home/homeView.fxml";

    //customerManage
    public static String customerMViewID="customerMView";
    public static String customerMRes="/org/manage/customerManage.fxml";
    //Customer表
    public static String customerViewID="customerView";
    public static String customerRes="/org/customer/customerView.fxml";
    //Vip表
    public static String vipViewID="vipView";
    public static String vipRes="/org/vip/vipView.fxml";
    //Employee表
    public static String employeeViewID="employeeView";
    public static String employeeRes="/org/employee/employeeView.fxml";
    //SoldManage
    public static String soldMViewID="soldMView";
    public static String soldMRes="/org/manage/soldManage.fxml";
    //SoldNote表
    public static String soldNoteViewID="soldNoteView";
    public static String soldNoteRes="/org/soldNote/soldNoteView.fxml";
    //Orderdetail表
    public static String orderDetailViewID="orderDetailView";
    public static String orderDetailRes="/org/orderDetail/orderDetail.fxml";
    //paymentmethod表
    public static String PaymentMethodViewID="PaymentMethodView";
    public static String PaymentMethodlRes="/org/paymentMethod/PaymentMethodView.fxml";
    //Bank表
    public static String bankViewID="bankView";
    public static String bankRes="/org/bank/bankView.fxml";
    //Aftersalerecord表
    public static String afterSaleRecordViewID="afterSaleRecordView";
    public static String afterSaleRecordRes="/org/afterSaleRecord/afterSaleRecord.fxml";
    //PurchaseManage
    public static String purchaseMViewID="purchaseMView";
    public static String purchaseMRes="/org/manage/purchaseManage.fxml";
    //purchaseRecord表
    public static String purchaseRecordViewID="purchaseRecordView";
    public static String purchaseRecordRes="/org/purchaseRecord/purchaseRecordView.fxml";
    //supplier表
    public static String supplierViewID="supplierView";
    public static String supplierRes="/org/supplier/supplierView.fxml";
    //ReturnRecord表
    public static String returnRecordViewID="returnRecordView";
    public static String returnRecordRes="/org/returnRecord/returnRecord.fxml";
    //store表
    public static String storeViewID="storeView";
    public static String storeRes="/org/store/storeView.fxml";
    //promotionDetail表
    public static String promotionDetailViewID="promotionDetailView";
    public static String promotionDetailRes="/org/promotionDetail/promotionDetailView.fxml";
    //book表
    public static String bookViewID="bookView";
    public static String bookRes="/org/book/bookView.fxml";




    public static StageController stageController;


    @Override
    public void start(Stage primaryStage){
        //新建一个StageController
        stageController=new StageController();

        stageController.setPrimaryStage("primaryStage",primaryStage);

        //加载stage
        stageController.loadStage(loginViewID,loginViewRes);
        stageController.loadStage(homeViewID,homeViewRes);
        stageController.loadStage(customerViewID,customerRes);
        stageController.loadStage(customerMViewID,customerMRes);
        stageController.loadStage(vipViewID,vipRes);
        stageController.loadStage(employeeViewID,employeeRes);
        stageController.loadStage(soldMViewID,soldMRes);
        stageController.loadStage(soldNoteViewID,soldNoteRes);
        stageController.loadStage(orderDetailViewID,orderDetailRes);
        stageController.loadStage(PaymentMethodViewID,PaymentMethodlRes);
        stageController.loadStage(bankViewID,bankRes);
        stageController.loadStage(afterSaleRecordViewID,afterSaleRecordRes);
        stageController.loadStage(purchaseMViewID,purchaseMRes);
        stageController.loadStage(purchaseRecordViewID,purchaseRecordRes);
        stageController.loadStage(supplierViewID,supplierRes);
        stageController.loadStage(returnRecordViewID,returnRecordRes);
        stageController.loadStage(storeViewID,storeRes);
        stageController.loadStage(promotionDetailViewID,promotionDetailRes);
        stageController.loadStage(bookViewID,bookRes);



        //显示login界面
        //stageController.setStage(loginViewID);
        stageController.setStage(homeViewID);



    }
    public static void main(String[] args){
        launch(args);
    }
}
