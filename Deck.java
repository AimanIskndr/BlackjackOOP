package application;

public class Deck {

	private Card[] cards;
	private int currentCount;

	public Deck() {
		cards = new Card[52];
		currentCount = 0;
		initializeDeck();
		shuffle();
	}

	private void initializeDeck() {
		int index = 0;
		String[] suits = { "Diamond", "Heart", "Club", "Spade" };
		String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

		for (String suit : suits) {
			for (String rank : ranks) {
				cards[index] = new Card(suit, rank);
				index++;
			}
		}
		currentCount = 52;
	}

	public void shuffle() {
		for (int i = 0; i < 52; i++) {
			int j = (int) (Math.random() * 51);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}

	public Card draw() {
		if (currentCount == 0) {
			return null; // Deck is empty
		}
		currentCount--;
		return cards[currentCount];
	}
}
