package org.control;

import javafx.fxml.FXML;

import static org.main.Main.customerViewID;
import static org.main.Main.stageController;
import static org.main.Main.vipViewID;

/**
 * Created by 泰扁扁 on 2017/5/28.
 */
public class CustomerManageControl implements ControlledStage {
    StageController myController;
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }
    @FXML protected void openCustomer(){
        stageController.setStage(customerViewID);
    }
    @FXML protected void openVip(){
        stageController.setStage(vipViewID);
    }
}
