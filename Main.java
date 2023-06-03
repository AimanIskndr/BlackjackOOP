package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
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
    Text text;
    Button playAgainBtn;

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
        hitBtn.setTranslateX(480);
        hitBtn.setTranslateY(300);
        hitBtn.setPrefWidth(55);
        hitBtn.setPrefHeight(35);
        hitBtn.setStyle("-fx-background-color: #f3f3f3; -fx-font-weight: bold;");

        standBtn = new Button("Stand");
        standBtn.setTranslateX(585);
        standBtn.setTranslateY(300);
        standBtn.setPrefWidth(53);
        standBtn.setPrefHeight(33);
        standBtn.setStyle("-fx-background-color: #f3f3f3; -fx-font-weight: bold;");
       
        hitBtn.setOnAction(new HitHandler());
        standBtn.setOnAction(new StandHandler());
        
        text = new Text();
        text.setFont(Font.font("Lexend", FontWeight.BOLD, 28));
        text.setFill(Color.WHITE);
        text.setTranslateY(310);
        
        playAgainBtn = new Button("Play Again");
        playAgainBtn.setTranslateX(510);
        playAgainBtn.setTranslateY(350);
        playAgainBtn.setPrefWidth(100);
        playAgainBtn.setPrefHeight(35);
        playAgainBtn.setStyle("-fx-background-color: #f3f3f3; -fx-font-weight: bold;");
        playAgainBtn.setVisible(false);
        
        playAgainBtn.setOnAction(event -> {
            initializeGame();
        });
        
        Image settingImg = new Image("C:\\Users\\USER2022\\eclipse-workspace\\BlackjackOOP\\misc\\setting.png");
        ImageView settingImgView = new ImageView(settingImg);
        settingImgView.setFitWidth(25);
        settingImgView.setFitHeight(25);
        
        Button bgBtn = new Button();
        bgBtn.setTranslateX(1070);
        bgBtn.setTranslateY(15);
        bgBtn.setGraphic(settingImgView);
        bgBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        bgBtn.setPrefWidth(25);
        bgBtn.setPrefHeight(25);
        
        bgBtn.setOnAction(event -> {
        	if (root.getStyle().contains("steelblue")) 
                root.setStyle("-fx-background-color: forestgreen;");
        	else
                root.setStyle("-fx-background-color: steelblue;");
        });;
        
        ToggleButton playerCountToggle = new ToggleButton();
        playerCountToggle.setSelected(true);
        playerCountToggle.setTranslateX(1083.5);
        playerCountToggle.setTranslateY(55);

        playerCountToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) 
                playerCount.setVisible(true);
            else
                playerCount.setVisible(false);
        });

        playerCount.setVisible(playerCountToggle.isSelected());

        
        root.getChildren().addAll(dealerCount, playerCount, dealerCardsContainer, playerCardsContainer, hitBtn, standBtn, text, playAgainBtn, bgBtn, playerCountToggle);
        
        Scene scene = new Scene(root, 1120, 630);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initializeGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        dealerCardsContainer.getChildren().clear();
        playerCardsContainer.getChildren().clear();
        dealInitialCards();
        displayCards(playerCardsContainer, player);
        displayCards(dealerCardsContainer, dealer);
        updateCountBox(playerCount, "Player: ", player.getHandSumStr());
        updateCountBox(dealerCount, "Dealer: ", dealer.getHandSumStr());
        hitBtn.setVisible(true);
        standBtn.setVisible(true);
        playAgainBtn.setVisible(false);
        text.setText("");
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
        playerCount.setVisible(true);
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
        
        text.setX(560 - text.getLayoutBounds().getWidth() / 2);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
