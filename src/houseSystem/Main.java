package houseSystem;

import houseSystem.addOfferView.AddOfferController;
import houseSystem.addOfferView.AddOfferScene;
import houseSystem.searchOfferView.SearchOfferScene;
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

    public static Scene homeScene;

    @Override
    public void start(Stage stageMain) throws Exception{

        AddOfferScene addOfferScene = new AddOfferScene(FXMLLoader.load(getClass().getResource("addOfferView/addOffer.fxml")));
        Button formApplication = new Button("Asuntolomake");
//        formApplication.setId("applButton");
        formApplication.setOnAction( e -> {
            stageMain.setScene(addOfferScene);
        });

        SearchOfferScene searchScene = new SearchOfferScene(FXMLLoader.load(getClass().getResource("searchOfferView/search.fxml")));
        Button searchApplication = new Button("Asuntohaku");
//        searchApplication.setId("searchButton");
        searchApplication.setOnAction( e -> {
            stageMain.setScene(searchScene);
        });

        Button exitButton = new Button("Poistu");
        exitButton.setOnAction( e -> System.exit(0));

        VBox root = new VBox(10,formApplication,searchApplication,exitButton);
        root.setAlignment(Pos.CENTER);

        stageMain.setTitle("Asunto järjestelmä");
        homeScene = new Scene(root, 600, 400);
        stageMain.setScene(homeScene);
        stageMain.show();

    }

    public static Scene getHomeScene() {return homeScene;}

    public static void main(String[] args) {
        launch(args);
    }
}
