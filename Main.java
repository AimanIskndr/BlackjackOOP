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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
    Button playAgainBtn;
    Text text;

    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: steelblue;");

        Pane pane = new Pane();

        dealerCardsContainer.setTranslateX(15);
        dealerCardsContainer.setTranslateY(70);

        playerCardsContainer.setTranslateX(15);
        playerCardsContainer.setTranslateY(350);

        dealInitialCards();
        displayCards(playerCardsContainer, player);
        displayCards(dealerCardsContainer, dealer);

        dealerCount = createCountBox("Dealer: ", dealer.getHandSumStr());
        dealerCount.setTranslateX(15);
        dealerCount.setTranslateY(15);

        playerCount = createCountBox("Player: ", player.getHandSumStr());
        playerCount.setTranslateX(15);
        playerCount.setTranslateY(535);

        pane.getChildren().addAll(dealerCount, dealerCardsContainer, playerCount, playerCardsContainer);

        hitBtn = new Button("Hit");
        hitBtn.setPrefWidth(55);
        hitBtn.setPrefHeight(35);

        standBtn = new Button("Stand");
        standBtn.setPrefWidth(55);
        standBtn.setPrefHeight(35);

        hitBtn.setOnAction(new HitHandler());
        standBtn.setOnAction(new StandHandler());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(hitBtn, standBtn);
        buttonBox.setAlignment(Pos.CENTER);

        playAgainBtn = new Button("Play Again");
        playAgainBtn.setTranslateX(525);
        playAgainBtn.setTranslateY(350);
        playAgainBtn.setPrefWidth(100);
        playAgainBtn.setPrefHeight(35);
        playAgainBtn.setVisible(false);

        text = new Text();
        text.setFont(Font.font("Lexend", FontWeight.BOLD, 28));
        text.setFill(Color.WHITE);

        StackPane centerContainer = new StackPane();
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.getChildren().addAll(buttonBox, text, playAgainBtn);

        root.setCenter(centerContainer);
        root.setLeft(pane);
        BorderPane.setAlignment(centerContainer, Pos.CENTER);
        BorderPane.setAlignment(playAgainBtn, Pos.CENTER);

        Scene scene = new Scene(root, 1120, 630);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();

        playAgainBtn.setOnAction(event -> {
            Main newGame = new Main();
            Stage newStage = new Stage();
            newGame.start(newStage);
            newStage.show();

            ((Stage) playAgainBtn.getScene().getWindow()).close();
        });
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
        determineWinner();
        playAgainBtn.setVisible(true);
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
    
    private void determineWinner() {

        if(player.getHandSum() == 21 && dealer.getHandSum() != 21 && player.getNumOfCards() == 2){
            text.setText("Blackjack!");
        }

        else if(dealer.getHandSum() > 21 && player.getHandSum() > 21){
        	text.setText("You both bust.");
        }

        else if(dealer.getHandSum() > 21 && player.getHandSum() <= 21){
            text.setText("You win, the dealer bust.");
        }

        else if(dealer.getHandSum() <= 21 && player.getHandSum() > 21){
        	text.setText("You bust");
        }

        else if(dealer.getHandSum() == player.getHandSum()){
        	text.setText("Game ended with a tie");
        }

        else if(((player.getHandSum() > dealer.getHandSum()) && player.getHandSum() <= 21) || player.getHandSum() == 21 && dealer.getHandSum() != 21){
        	text.setText("You win!");
        }

        else if((player.getHandSum() < dealer.getHandSum()) && dealer.getHandSum() <= 21){
        	text.setText("You lose.");
        }
    }
}
