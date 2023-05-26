package application;

public class Dealer extends Player {
	
	boolean hide = true;
	
    Dealer() {
        super();
    }

    @Override
    public void takeCard(Deck deck) {
        takeCard(deck, true);
    }

    public void takeCard(Deck deck, boolean auto) {
        if (auto) {
            super.takeCard(deck);
        } 
        
        else {
            do {
                Card drawnCard = deck.draw();

                if (drawnCard != null) {
                    hand[numOfCards] = drawnCard;
                    numOfCards++;
                    if (drawnCard.isAce()) {
                        aceCount++;
                    }
                    updateHandSum(drawnCard);
                }
            } while (handSum < 17);
        }
    }
	
    @Override
    public String getHandSumStr() {
        if (hide) {
            return getHiddenHandSum();
        }
        return String.valueOf(handSum);
    }
    
    public String getHiddenHandSum() {
    	return hand[0].getValue() + " + ?";
    }
}
