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
    	
    	if(handSum > 21 && aceCount > 0)
    		updateHandSum();
    }
    
    protected void updateHandSum() {
    	handSum -= 10;
		aceCount--;
    }

    public int getHandSum() {
		return handSum;
	}
    
    public String getHandSumStr() {
        return String.valueOf(handSum);
    }

    public int getNumOfCards() {
		return numOfCards;
	}
}
