package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application{

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
    ToggleButton playerCountToggle;
    ToggleButton bgToggle;
    
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

        dealerCount = createCountBox("Dealer: ", dealer.getHandSumStr(), 15, 15);

        playerCount = createCountBox("Player: ", player.getHandSumStr(), 15, 585);

        hitBtn = createButton("Hit", 480, 300, 55, 35);
        hitBtn.setOnAction(new HitHandler());
        
        standBtn = createButton("Stand" , 585, 300, 53, 33);
        standBtn.setOnAction(new StandHandler());
        
        text = new Text();
        text.setFont(Font.font("Lexend", FontWeight.BOLD, 28));
        text.setFill(Color.WHITE);
        text.setTranslateY(310);
        
        Image settingImg = new Image("C:\\Users\\USER2022\\eclipse-workspace\\BlackjackOOP\\PNGAssets\\setting.png");
        ImageView settingImgView = new ImageView(settingImg);
        settingImgView.setFitWidth(25);
        settingImgView.setFitHeight(25);
        
        playerCountToggle = new ToggleButton();
        
        bgToggle = new ToggleButton();
        
        SettingPane setting = new SettingPane();
        setting.setTranslateX(777);
        setting.setTranslateY(30);
        
        Button settingBtn = new Button();
        settingBtn.setTranslateX(1070);
        settingBtn.setTranslateY(30);
        settingBtn.setGraphic(settingImgView);
        settingBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        settingBtn.setPrefWidth(25);
        settingBtn.setPrefHeight(25);
        settingBtn.setOnAction(event -> {
        	if(setting.isVisible()) setting.setVisible(false);
        	else setting.setVisible(true);
        });;
        
        playerCountToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) 
                playerCount.setVisible(false);
            else
                playerCount.setVisible(true);
            setting.setState();
        });
        
        bgToggle.setOnAction(event ->{
        	if (root.getStyle().contains("steelblue"))
        		root.setStyle("-fx-background-color: forestgreen;");
        	else
        		root.setStyle("-fx-background-color: steelblue;");
        	setting.setBackground();
        });;
        
        settingBtn.setTranslateX(1070);
        settingBtn.setTranslateY(15);
        settingBtn.setGraphic(settingImgView);
        settingBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        settingBtn.setPrefWidth(25);
        settingBtn.setPrefHeight(25);
        
        playAgainBtn = createButton("Play Again", 510, 350, 100, 35);
        playAgainBtn.setVisible(false);
        
        playAgainBtn.setOnAction(event -> {
        	setting.setVisible(false);
            initializeGame();
        });
        
        root.getChildren().addAll(dealerCount, playerCount, dealerCardsContainer, playerCardsContainer, hitBtn, standBtn, text, playAgainBtn, settingBtn, setting);
        
        Scene scene = new Scene(root, 1120, 630);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void initializeGame() {
    	//create new instances of deck, player, and dealer
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        //update GUI
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
        if(!playerCountToggle.isSelected()) playerCount.setVisible(false);
    }

    private HBox createCountBox(String labelPrefix, String handSumStr, double x, double y) {

        HBox countBox = new HBox(10);

        Text label = new Text(labelPrefix);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setFill(Color.WHITE);
        
        Text countText = new Text(handSumStr);
        countText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        countText.setFill(Color.WHITE);
        countBox.getChildren().addAll(label, countText);
        
        countBox.setTranslateX(x);
        countBox.setTranslateY(y);
        
        return countBox;
    }
    
    private void updateCountBox(HBox countBox, String labelPrefix, String handSumStr) {
        Text countText = (Text) countBox.getChildren().get(1);
        countText.setText(handSumStr);
    }
    
    private Button createButton(String label, double x, double y, double w, double h) {
    	Button btn = new Button(label);
    	btn.setTranslateX(x);
    	btn.setTranslateY(y);
    	btn.setPrefWidth(w);
    	btn.setPrefHeight(h);
    	btn.setStyle("-fx-background-color: #f3f3f3; -fx-font-weight: bold;");
    	return btn;
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
        playerCount.setVisible(true);

        Task<Void> dealerTurnTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                dealerPlay();
                return null;
            }
        };

        dealerTurnTask.setOnSucceeded(event -> {
            determineWinner();
            playAgainBtn.setVisible(true);
        });

        Thread dealerThread = new Thread(dealerTurnTask);
        dealerThread.start(/*start a new thread*/);
    }

    private void dealerPlay() {
        while (dealer.getHandSum() <= 16) {
        	sleep(1.4);
            dealer.takeCard(deck);
            Platform.runLater(() -> {
                displayCards(dealerCardsContainer, dealer);
                updateCountBox(dealerCount, "Dealer: ", dealer.getHandSumStr());
            });
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
    
    public static void sleep(double s) {
        long ms = (long) s * 1000;
        try{
            Thread.sleep(ms);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
    }
    
    public class SettingPane extends Pane {
        
        private Text title;
        private Text backgroundcolourbar;
        private Text playercountbar; 	
        private Text state;
        private Text background;
        private Rectangle colorindicator = new Rectangle(20, 20);
        
        public SettingPane() {
            setPrefSize(300, 180);
            setStyle("-fx-background-color: #f5f5f5;");
            setVisible(false);
            
            title = new Text("Setting");
            title.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
            title.setX(150 - title.getLayoutBounds().getWidth() / 2);
            title.setY(25);
            
            backgroundcolourbar = new Text("Background Color  :");
            backgroundcolourbar.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 16));
            backgroundcolourbar.setX(42);
            backgroundcolourbar.setY(70);
            
            playercountbar = new Text("Player Hand Count :");
            playercountbar.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, 16));
            playercountbar.setX(42);
            playercountbar.setY(120);

            state = new Text();
            setState();
            state.setX(230);
            state.setY(118);
            state.setFont(Font.font(13));
            
            background= new Text();
            setBackground();
            background.setX(42);
            background.setY(70);
             
            playerCountToggle.setTranslateX(205);
            playerCountToggle.setTranslateY(100);
            
            bgToggle.setTranslateX(205);
            bgToggle.setTranslateY(55);
            
            colorindicator.setX(232);
            colorindicator.setY(57);
            colorindicator.setFill(Color.STEELBLUE);
            colorindicator.setStroke(Color.BLACK);
            
            getChildren().addAll(title, backgroundcolourbar, playercountbar, playerCountToggle, state, bgToggle, colorindicator);
        }
        
        public void setState() {
        	if(playerCountToggle.isSelected()) state.setText("(Off)");
        	else state.setText("(On)");
        }
        
        public void setBackground() {
        	if(bgToggle.isSelected()) {
        		colorindicator.setFill(Color.FORESTGREEN);
        	}
        	else{
        		colorindicator.setFill(Color.STEELBLUE);
        	}
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
