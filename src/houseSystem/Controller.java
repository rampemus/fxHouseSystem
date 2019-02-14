package houseSystem;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private void backToHome(ActionEvent event){
        ((Stage) ((Node)event.getSource()).getScene().getWindow()).setScene(Main.getHomeStage());
    }
}
