package application;

import javafx.scene.image.Image;

public class Card {
	
    private String suit;
    private String rank;
    private int value;
    private Image frontImage;
    private Image backImage = new Image("file:C:\\Users\\USER2022\\eclipse-workspace\\BlackjackOOP\\cards\\back.png");

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = calculateValue(rank);
        this.frontImage = new Image("C:\\Users\\USER2022\\eclipse-workspace\\BlackjackOOP\\cards\\" + suit.charAt(0) + rank.charAt(0) + ".png");
    }

    private int calculateValue(String rank) {
        
    	switch (rank.charAt(0)){
        	case 'A': return 11;
        	case '1':
        	case 'J':
        	case 'Q':
        	case 'K': return 10;
        	default: return (int) rank.charAt(0) - '0';
    	}
    }

	public int getValue() {
		return value;
	}

	public Image getFrontImage() {
		return frontImage;
	}

	public Image getBackimage() {
		return backImage;
  }
}
