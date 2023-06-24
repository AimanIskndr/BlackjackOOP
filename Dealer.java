package application;

/**
 * The Dealer class represents a dealer in a card game.
 */
public class Dealer extends Player {

    boolean hide = true; // hidden = face down

    /**
     * Constructs a new Dealer object.
     */
    public Dealer() {
        super();
    }

    /**
     * Returns a string representation of the sum of the dealer's hand value.
     * If the dealer's second card is faced down, it returns the value of the first card appended with "+ ?".
     * Otherwise, it returns the sum of the dealer's hand value.
     * @return a string representation of the sum of the dealer's hand value
     */
    @Override
    public String getHandSumStr() {
        if (hide) {
            return getHiddenHandSum();
        }
        return String.valueOf(handSum) + (softHand ? "*" : "");
    }

    /**
     * @return a string representation the value of the first card appended with "+ ?".
     */
    public String getHiddenHandSum() {
        return hand[0].getValue() + (hand[0].isAce() ? "*" : "") + " + ?";
    }
}
