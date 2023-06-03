package application;

public class Deck {
	
    private Card[] cards;
    private int currentCount;

    public Deck() {
        cards = new Card[52];
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = {"Diamond", "Heart", "Club", "Spade"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        
        currentCount = 0;
        
        for (String suit : suits) {
            for (String rank : ranks) {
                cards[currentCount] = new Card(suit, rank);
                currentCount++;
            }
        }
    }

    public void shuffle(/*Monkey sort*/) {
    	Card temp;
    	for (int i = 0; i < 52; i++) {
            int j = (int) (Math.random() * 51);
            temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card draw() {
        if (currentCount == 0) {
            return null;
        }
        currentCount--;
        return cards[currentCount];
    }
}
