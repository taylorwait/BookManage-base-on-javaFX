package org.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import static org.main.Main.*;

/**
 * Created by 泰扁扁 on 2017/5/14.
 */
public class HomeControl implements ControlledStage {
    StageController myController;
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }
    @FXML
    protected void logout(ActionEvent event){
        stageController.setStage(loginViewID,homeViewID);
    }
    @FXML
    protected void openCustomerM(ActionEvent event){
        stageController.setStage(customerMViewID);
    }

    @FXML protected void openEmployee(ActionEvent event){
        stageController.setStage(employeeViewID);
    }
    @FXML protected void openSoldM(ActionEvent event){
        stageController.setStage(soldMViewID);
    }
    @FXML protected void openPurchaseM(){
        stageController.setStage(purchaseMViewID);
    }
    @FXML protected void openPromotionDetail(){
        stageController.setStage(promotionDetailViewID);
    }
    @FXML protected void openBook(){
        stageController.setStage(bookViewID);
    }
}
