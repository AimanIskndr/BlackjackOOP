package application;

/**
 * The Deck class represents a deck of playing cards.
 */
public class Deck {

    private Card[] cards;
    private int currentCount;

    /**
     * Constructs a new Deck object and initializes it with a standard deck of 52 playing cards.
     */
    public Deck() {
        cards = new Card[52];
        initializeDeck();
        shuffle();
    }

    /**
     * Initializes the deck with a standard set of 52 playing cards.
     */
    private void initializeDeck() {
        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        currentCount = 0;

        for (String suit : suits) {
            for (String rank : ranks) {
                cards[currentCount] = new Card(suit, rank);
                currentCount++;
            }
        }
    }

    /**
     * Shuffles the cards in the deck using the monkey sort algorithm.
     */
    public void shuffle() {
        Card temp;
        for (int i = 0; i < 52; i++) {
            int j = (int) (Math.random() * 52);
            temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /**
     * Draws a card from the deck.
     * @return the Card object drawn from the deck, or null if the deck is empty
     */
    public Card draw() {
        if (currentCount == 0) {
            return null;
        }
        currentCount--;
        return cards[currentCount];
    }
}
