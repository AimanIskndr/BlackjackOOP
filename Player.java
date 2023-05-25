package application;

public class Player {

    protected int handSum = 0;
    protected int aceCount = 0;
    protected int numOfCards = 0;
    Card[] hand = new Card[11];
    
    Player() {}

    public void takeCard(Deck deck) {
    	
        Card drawnCard = deck.draw();
        
        if (drawnCard != null) {
            hand[numOfCards] = drawnCard;
            numOfCards++;
            if(drawnCard.isAce()) aceCount++;
            updateHandSum(drawnCard);
        }
    }

    protected void updateHandSum(Card card) {
        
    	handSum += card.getValue();
    	
    	if(handSum > 21 && aceCount > 0) {
    		handSum -= 10;
    		aceCount--;
    	}
    }

	public int getAceCount() {
		return aceCount;
	}

	public Card[] getHand() {
		return hand;
	}
}