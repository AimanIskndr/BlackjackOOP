package application;

/**
 * The Player class represents a player in a card game.
 */
public class Player {

    protected int handSum = 0; // refers to the sum of cards in the player's hand.
    protected int flexibleAceCount = 0; // ace card that can counted as 1
    protected int numOfCards = 0;
    protected boolean softHand = false; // soft hand = have flexible ace
    Card[] hand = new Card[11]; // Mathematically, the maximum card that player or dealer can have is 11

    /**
     * Constructs a new Player object.
     */
    public Player() {
    }

    /**
     * Takes a card from the deck and adds it to the player's hand.
     * @param deck the deck of cards to draw from
     */
    public void takeCard(Deck deck) {
        Card drawnCard = deck.draw();

        if (drawnCard != null) {
            hand[numOfCards] = drawnCard;
            numOfCards++;

            if (drawnCard.isAce()) {
                flexibleAceCount++;
                softHand = true;
            }
            updateHandSum(drawnCard);
        }
    }

    /**
     * Updates the sum of the player's hand value based on the added card.
     * @param card the card added to the player's hand
     */
    protected void updateHandSum(Card card) {
        handSum += card.getValue();

        if (handSum > 21 && softHand)
            updateHandSum();
    }

    /**
     * Updates the sum of the player's hand value when an Ace card needs to be counted as 1 instead of 11.
     */
    protected void updateHandSum() {
        handSum -= 10;
        flexibleAceCount--;

        if (flexibleAceCount == 0) {
            softHand = false;
        }
    }

    /**
     * Returns the sum of the player's hand value.
     * @return the sum of the player's hand value
     */
    public int getHandSum() {
        return handSum;
    }

    /**
     * Returns a string representation of the sum of the player's hand value.
     * If the player has a soft hand (an Ace counted as 11), it appends an asterisk (*) to the value.
     * @return a string representation of the sum of the player's hand value
     */
    public String getHandSumStr() {
        return String.valueOf(handSum) + (softHand ? "*" : "");
    }

    /**
     * Returns the number of cards in the player's hand.
     * @return the number of cards in the player's hand
     */
    public int getNumOfCards() {
        return numOfCards;
    }

}
