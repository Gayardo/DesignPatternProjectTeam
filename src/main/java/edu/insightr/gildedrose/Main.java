package edu.insightr.gildedrose;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    public Stage primaryStage2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        primaryStage.setTitle("List of Items");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
        primaryStage2=primaryStage;
    }

    public static void main(String[] args) {

        launch(args);

    }



}