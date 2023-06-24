package application;

import javafx.scene.image.Image;

/**
 * The Card class represents a playing card in a card game.
 */
public class Card {

    private String suit; // (e.g., "Hearts", "Spades", "Diamonds", "Clubs")
    private String rank; // (e.g., "Ace", "2", "3", ..., "10", "Jack", "Queen", "King")
    private int value; // the default value the card holds based on their rank (e.g. 2-11)
    private static String path; //local path to PNGAssets\cards
    private Image frontImage;
    private Image backImage = new Image(path + "\\back.png");
    private boolean faceUp = true;

    /**
     * Constructs a Card object with the specified suit and rank.
     * @param suit = the suit of the card 
     * @param rank = the rank of the card 
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = calculateValue(rank);
        this.frontImage = new Image(path + "\\" + suit.charAt(0) + rank.charAt(0) + ".png");
    }

    /**
     * Calculates the numerical value of the card based on its rank.
     * @param rank = the rank of the card
     * @return the = numerical value of the card
     */
    private int calculateValue(String rank) {
        switch (rank.charAt(0)) {
            case 'A':
                return 11;
            case '1':
            case 'J':
            case 'Q':
            case 'K':
                return 10;
            default:
                return (int) rank.charAt(0) - '0';
        }
    }

    /**
     * Returns the numerical value of the card.
     * @return the numerical value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the image of the card, either the front or the back image based on the card's orientation.
     * @return the image of the card
     */
    public Image getCardImage() {
        if (faceUp)
            return frontImage;
        return backImage;
    }

    /**
     * Sets the path to the directory containing the card images.
     * This path should be a local file path.
     * @param PATH the path to the directory containing the card images
     */
    public static void setPath(String PATH) {
        path = "file:" + PATH + "\\cards";
    }

    /**
     * Flips the card, changing its orientation from face up to face down or vice versa.
     */
    public void flip() {
        faceUp = !faceUp;
    }

    /**
     * Checks if the card is an Ace.
     * @return true if the card is an Ace, false otherwise
     */
    public boolean isAce() {
        return rank.equals("Ace");
    }
}
