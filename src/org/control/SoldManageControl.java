package org.control;

import javafx.fxml.FXML;

import static org.main.Main.*;

/**
 * Created by 泰扁扁 on 2017/5/30.
 */
public class SoldManageControl implements ControlledStage {
    StageController myController;
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }
    @FXML protected void openSoldNote(){
        stageController.setStage(soldNoteViewID);
    }
    @FXML protected void openOrderDetail(){
        stageController.setStage(orderDetailViewID);
    }
    @FXML protected void openPaymentMethod(){
        stageController.setStage(PaymentMethodViewID);
    }
    @FXML protected void openBank(){
        stageController.setStage(bankViewID);
    }
    @FXML protected void openAfterSaleRecord(){
        stageController.setStage(afterSaleRecordViewID);
    }
}
