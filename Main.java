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

    public void start(Stage primaryStage) {

        Pane root = new Pane();
        root.setPadding(new Insets(10));

        HBox dealerCardsContainer = new HBox(10);
        dealerCardsContainer.setTranslateX(15);
        dealerCardsContainer.setTranslateY(70);

        HBox playerCardsContainer = new HBox(10);
        playerCardsContainer.setTranslateX(15);
        playerCardsContainer.setTranslateY(420);

        dealInitialCards();
        displayPlayerCards(playerCardsContainer);
        displayDealerCards(dealerCardsContainer);

        HBox dealerCount = createCountBox("Dealer: ", dealer.getHandSumStr());
        dealerCount.setAlignment(Pos.TOP_LEFT);
        dealerCount.setTranslateX(15);
        dealerCount.setTranslateY(15);

        HBox playerCount = createCountBox("Player: ", player.getHandSumStr());
        playerCount.setAlignment(Pos.BOTTOM_LEFT);
        playerCount.setTranslateX(15);
        playerCount.setTranslateY(585);

        root.getChildren().addAll(dealerCount, playerCount, dealerCardsContainer, playerCardsContainer);

        Scene scene = new Scene(root, 1120, 630, Color.STEELBLUE);

        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class HitHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            player.takeCard(deck);
            createCountBox("Player: ", player.getHandSumStr());
        }
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

    private void dealInitialCards() {

        player.takeCard(deck);
        dealer.takeCard(deck);
        player.takeCard(deck);
        dealer.takeCard(deck);
        dealer.hand[1].flip();
    }

    private void displayPlayerCards(HBox cardsContainer) {

        for (int i = 0; i < player.getNumOfCards(); i++) {
            Card card = player.hand[i];
            ImageView imageView = new ImageView(card.getCardImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(140);
            cardsContainer.getChildren().add(imageView);
        }
    }

    private void displayDealerCards(HBox cardsContainer) {

        for (int i = 0; i < dealer.getNumOfCards(); i++) {
            Card card = dealer.hand[i];
            ImageView imageView = new ImageView(card.getCardImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(140);
            cardsContainer.getChildren().add(imageView);
        }
    }
}
