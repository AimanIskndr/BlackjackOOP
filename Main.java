package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
    	
        Card cardS1 = new Card("Clover", "Jack");
        Card cardH2 = new Card("Spade", "Ace");
        Card unknown = new Card("Diamond", "8");

        ImageView civ1 = new ImageView(cardS1.getFrontImage());
        ImageView civ2 = new ImageView(cardH2.getFrontImage());
        ImageView civ3 = new ImageView(unknown.getBackimage());

        civ1.setFitWidth(100);
        civ1.setFitHeight(140); 

        civ2.setFitWidth(100);
        civ2.setFitHeight(140); 
        
        civ3.setFitWidth(100);
        civ3.setFitHeight(140); 

        HBox cardsContainer = new HBox(10); 
        cardsContainer.getChildren().addAll(civ1, civ2, civ3);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10)); 
        root.setTop(cardsContainer);

        Scene scene = new Scene(root, 1280, 653, Color.STEELBLUE);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
