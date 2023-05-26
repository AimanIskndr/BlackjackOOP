package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
    private List<Card> cards;
    private int currentCount;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = {"Diamond", "Heart", "Club", "Spade"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        currentCount = 52;
    }

    public void shuffle() {
    	Collections.shuffle(cards);
    }

    public Card draw() {
    	currentCount--;
        Card drawnCard = cards.get(currentCount);
        return drawnCard;
    }
}
