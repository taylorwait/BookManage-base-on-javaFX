package org.control;

import javafx.fxml.FXML;

import static org.main.Main.*;

/**
 * Created by 泰扁扁 on 2017/5/31.
 */
public class PurchaseManageControl implements ControlledStage{
    StageController myController;
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }
    @FXML protected void openPurchaseRecord(){
        stageController.setStage(purchaseRecordViewID);
    }
    @FXML protected void openSupplier(){
        stageController.setStage(supplierViewID);
    }
    @FXML protected void openReturnRecord(){
        stageController.setStage(returnRecordViewID);
    }
    @FXML protected void openStore(){
        stageController.setStage(storeViewID);
    }
}
