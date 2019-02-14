package houseSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//SETUP:
//https://openjfx.io/openjfx-docs/#IDE-Intellij

public class Main extends Application {

    public static Scene home;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent addOffer = FXMLLoader.load(getClass().getResource("addOffer.fxml"));
        Scene addOfferScene = new Scene(addOffer);

        Button formApplication = new Button("Asuntolomake");
        formApplication.setId("applButton");
        formApplication.setOnAction(actionEvent -> {
            primaryStage.setScene(addOfferScene);
        });




        Parent search = FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene searchScene = new Scene(search);
        Button searchApplication = new Button("Asuntohaku");
        searchApplication.setId("searchButton");
        searchApplication.setOnAction(actionEvent -> {
            primaryStage.setScene(searchScene);
        });

        VBox root = new VBox(10,formApplication,searchApplication);
        root.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Asunto järjestelmä");
        home = new Scene(root, 600, 400);
        primaryStage.setScene(home);
        primaryStage.show();

    }

    public static Scene getHomeStage() {return home;}

    public static void main(String[] args) {
        launch(args);
    }
}
