package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    Deck deck = new Deck();
    Player player = new Player();
    Dealer dealer = new Dealer();
    HBox dealerCardsContainer = new HBox(10);
    HBox playerCardsContainer = new HBox(10);
    HBox playerCount;
    HBox dealerCount;
    Button hitBtn;
    Button standBtn; 

    public void start(Stage primaryStage) {
    	
        Pane root = new Pane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: steelblue;");

        dealerCardsContainer.setTranslateX(15);
        dealerCardsContainer.setTranslateY(70);

        playerCardsContainer.setTranslateX(15);
        playerCardsContainer.setTranslateY(420);

        dealInitialCards();
        displayCards(playerCardsContainer, player);
        displayCards(dealerCardsContainer, dealer);

        dealerCount = createCountBox("Dealer: ", dealer.getHandSumStr());
        dealerCount.setTranslateX(15);
        dealerCount.setTranslateY(15);

        playerCount = createCountBox("Player: ", player.getHandSumStr());
        playerCount.setTranslateX(15);
        playerCount.setTranslateY(585);

        hitBtn = new Button("Hit");
        hitBtn.setTranslateX(500);
        hitBtn.setTranslateY(300);
        hitBtn.setPrefWidth(55);
        hitBtn.setPrefHeight(35);

        standBtn = new Button("Stand");
        standBtn.setTranslateX(600);
        standBtn.setTranslateY(300);
        standBtn.setPrefWidth(55);
        standBtn.setPrefHeight(35);
       
        hitBtn.setOnAction(new HitHandler());
        standBtn.setOnAction(new StandHandler());
       
        root.getChildren().addAll(dealerCount, playerCount, dealerCardsContainer, playerCardsContainer, hitBtn, standBtn);

        Scene scene = new Scene(root, 1120, 630);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createCountBox(String labelPrefix, String handSumStr) {

        HBox countBox = new HBox(10);

        Text label = new Text(labelPrefix);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setFill(Color.WHITE);
        
        Text countText = new Text(handSumStr);
        countText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        countText.setFill(Color.WHITE);
        countBox.getChildren().addAll(label, countText);

        return countBox;
    }
    
    private void updateCountBox(HBox countBox, String labelPrefix, String handSumStr) {
        Text countText = (Text) countBox.getChildren().get(1);
        countText.setText(handSumStr);
    }

    private void dealInitialCards() {

        player.takeCard(deck);
        dealer.takeCard(deck);
        player.takeCard(deck);
        dealer.takeCard(deck);
        dealer.hand[1].flip();
    }
    
    class HitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            handlePlayerHit();
        }
    }
    
    private void handlePlayerHit() {
        player.takeCard(deck);
        displayCards(playerCardsContainer, player);
        updateCountBox(playerCount, "Player: ", player.getHandSumStr());

        if (player.getHandSum() >= 21) {
            handlePlayerStand();
        }
    }
    
    class StandHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            handlePlayerStand();
        }
    }
    
    private void handlePlayerStand() {
        hitBtn.setVisible(false);
        standBtn.setVisible(false);
        dealer.hand[1].flip();
        dealer.hide = false;
        displayCards(dealerCardsContainer, dealer);
        updateCountBox(dealerCount, "Dealer: ", dealer.getHandSumStr());
        dealerPlay();
        // determineWinner();
    }
    
    private void dealerPlay() {
    	
    	while(dealer.getHandSum() <= 16) {
    		dealer.takeCard(deck);
    		displayCards(dealerCardsContainer, dealer);
            updateCountBox(dealerCount, "Dealer: ", dealer.getHandSumStr());
    	}
    }

    private void displayCards(HBox cardsContainer, Player hand) {
   
    	if (!cardsContainer.getChildren().isEmpty()) {
    		cardsContainer.getChildren().clear();
    	}
    	
        for (int i = 0; i < hand.getNumOfCards(); i++) {
            Card card = hand.hand[i];
            ImageView imageView = new ImageView(card.getCardImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(140);
            cardsContainer.getChildren().add(imageView);
        }
    }
}
