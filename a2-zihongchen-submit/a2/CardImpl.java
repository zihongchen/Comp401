package a2;

public class CardImpl implements Card {
	private int Rank;
	private Card.Suit suit;

	public CardImpl(int rank, Card.Suit suite) {
		if(rank>14 || rank<2 ) {
			throw new RuntimeException("out of range");
			
		}
		this.Rank = rank;
		this.suit = suite;

	}

	
	
	public Card.Suit getSuit() {
		return suit;

		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Card other) {
		if (getRank() == other.getRank() && getSuit() == other.getSuit()) {
			return true;
		} else
			return false;

	}

	@Override
	public int getRank() {
		// TODO Auto-generated method stub
		return Rank;
	}
	public String toSTring() {
		String rank=null;
		 switch(getRank()) {
		case 2: rank= "Two";
		case 3: rank= "Three";
		case 4: rank= "Four";
		case 5: rank= "Five";
		case 6: rank= "Six";
		case 7: rank= "Seven";
		case 8: rank= "Eight";
		case 9: rank= "Nine";
		case 10: rank= "Ten";
		case 11: rank= "Jack";
		case 12: rank= "Queen";
		case 13: rank= "King";
		case 14: rank= "Ace";
		
		}
		rank+=" of ";
		rank+= Card.suitToString(suit);
		return rank;
		
	}

}
