package application;

public class Dealer extends Player {

	Dealer () {
		super();
	}
	
	@Override
	public void takeCard(Deck deck) {
		
        while (handSum < 17) {
        	
        	Card drawnCard = deck.draw();
            
            if (drawnCard != null) {
            	
                hand[numOfCards] = drawnCard;
                numOfCards++;
                if(drawnCard.isAce()) aceCount++;
                updateHandSum(drawnCard);
            }
        }
    }
    
}