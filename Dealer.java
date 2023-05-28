package application;

public class Dealer extends Player {
	
	boolean hide = true;
	
    Dealer() {
        super();
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
