package a2;

public class PokerHandImpl implements PokerHand {
	 private Card[] handCard;
	private int handRank;

	public PokerHandImpl(Card[] cards) {
		
		
		for (int i=0; i<cards.length; i++) {
			  for (int j=i+1; j<cards.length; j++) {
			    if (cards[i].getRank() > cards[j].getRank()) {
			      Card tmp = cards[i];
			      cards[i] = cards[j];
			      cards[j] = tmp;
			    }
			  }
			}
		handCard=cards.clone();
	}//   I coped this from Piazza from answer of instructors

	
	
	
	
	
	/*public Card[] sort(Card[] j) {
		Card[] l = j.clone();
		for (int i = 0; i < 5; i++) {
			int min = 15;
			int index=0;
			for (int k = i+1; k < 5; k++) {
				if (l[k].getRank() < min) {
					min = l[k].getRank();
					 index=k;           //Card temp = l[i];
					            //l[i] = l[k];
					            //l[k] = temp;

				}if(min!=l[i].getRank()) {
				Card temp = l[i];
				l[i] = l[index];
				l[index] = temp;
			}
			}

		}
		return l;

	}*/

	@Override
	public Card[] getCards() {

		return handCard.clone();
	}

	@Override
	public boolean contains(Card c) {
		for (int i = 0; i < handCard.length; i++) {
			if (handCard[i].equals(c)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean isOnePair() {
		int s = 0;
		for (int i = 0; i < 4; i++) {
			if (handCard[i].getRank() == handCard[i + 1].getRank()) {
				
			s++;
			handRank = handCard[i].getRank();
			}
		}
		if (s == 1) {
			return true;
		}else {
		handRank = 0;
		return false;
		}

	}

	@Override
	public boolean isTwoPair() {
		if (handCard[0].getRank() == handCard[1].getRank() && handCard[2].getRank() == handCard[3].getRank()
				&& handCard[2].getRank() != handCard[1].getRank() && handCard[3].getRank() != handCard[4].getRank()) {

			handRank = handCard[2].getRank();
			return true;

		} else if (handCard[0].getRank() == handCard[1].getRank() && handCard[3].getRank() == handCard[4].getRank()
				&& handCard[2].getRank() != handCard[1].getRank() && handCard[2].getRank() != handCard[3].getRank()) {

			handRank = handCard[3].getRank();
			return true;

		}
		if (handCard[1].getRank() == handCard[2].getRank() && handCard[3].getRank() == handCard[4].getRank()
				&& handCard[1].getRank() != handCard[0].getRank() && handCard[2].getRank() != handCard[3].getRank()) {

			handRank = handCard[3].getRank();
			return true;

		}
		return false;
	}

	@Override
	public boolean isThreeOfAKind() {
		if (handCard[0].getRank() == handCard[2].getRank() && handCard[3].getRank() != handCard[4].getRank()
				&& handCard[2].getRank() != handCard[3].getRank()) {
			handRank = handCard[2].getRank();
			return true;

		} else if (handCard[1].getRank() == handCard[3].getRank() && handCard[3].getRank() != handCard[4].getRank()&& handCard[0].getRank() != handCard[1].getRank()) {
			handRank = handCard[2].getRank();
			return true;

		} else if (handCard[2].getRank() == handCard[4].getRank() && handCard[0].getRank() != handCard[1].getRank()
				&& handCard[1].getRank() != handCard[2].getRank()) {
			handRank = handCard[2].getRank();
			return true;

		}
		return false;

	}

	@Override
	public boolean isStraight() {
		int h = 0;
		for (int i = 0; i < 4; i++) {
			if (handCard[i].getRank() - handCard[i + 1].getRank() == -1) {
				h += 1;
			}
		}
		if (h == 4) {
			handRank = handCard[4].getRank();
			return true;
		} else if (handCard[0].getRank() == 2 && handCard[1].getRank() == 3 && handCard[2].getRank() == 4
				&& handCard[3].getRank() == 5 && handCard[4].getRank() == 14) {
			handRank = handCard[3].getRank();
			return true;
		}
		return false;
	}

	@Override
	public boolean isFlush() {
		if (handCard[0].getSuit() == handCard[1].getSuit() && handCard[0].getSuit() == handCard[2].getSuit()
				&& handCard[0].getSuit() == handCard[3].getSuit() && handCard[0].getSuit() == handCard[4].getSuit()) {
			if (handCard[0].getRank() == 2 && handCard[1].getRank() == 3 && handCard[2].getRank() == 4
					&& handCard[3].getRank() == 5 && handCard[4].getRank() == 14) {
				handRank = handCard[3].getRank();
				return true;
			}
			handRank = handCard[4].getRank();
			return true;
		}
		return false;

	}

	@Override
	public boolean isFullHouse() {
		if (handCard[0].getRank() == handCard[2].getRank() && handCard[3].getRank() == handCard[4].getRank()
				&& handCard[2].getRank() != handCard[3].getRank()) {
			handRank = handCard[2].getRank();
			return true;
		} else if (handCard[2].getRank() == handCard[4].getRank() && handCard[0].getRank() == handCard[1].getRank()
				&& handCard[1].getRank() != handCard[2].getRank()) {
			handRank = handCard[2].getRank();
			return true;
		}
		return false;
	}

	@Override
	public boolean isFourOfAKind() {
	
			if (handCard[0].getRank() == handCard[3].getRank() && handCard[3].getRank() != handCard[4].getRank()) {
				handRank = handCard[2].getRank();
				return true;
			} else if (handCard[1].getRank() == handCard[4].getRank()
					&& handCard[0].getRank() != handCard[1].getRank()) {
				handRank = handCard[2].getRank();
				return true;
			}

		

		return false;
	}

	@Override
	public boolean isStraightFlush() {

		if (isFlush() && isStraight()) {

			return true;

		}
		return false;
	}

	@Override
	public int getHandRank() {
		if (isOnePair()) {
			return handRank;

		} else if (isTwoPair()) {
			return handRank;

		} else if (isThreeOfAKind()) {
			return handRank;
		} else if (isStraight()) {
			return handRank;
		} else if (isFlush()) {
			return handRank;
		} else if (isFullHouse()) {
			return handRank;
		} else if (isFourOfAKind()) {
			return handRank;
		} else if (isStraightFlush()) {
			return handRank;
		} else
			return handCard[4].getRank();

	}

	@Override
	public int getHandTypeValue() {
		if (isStraightFlush()) {
			return 9;
		}else if (isFourOfAKind()) {
			return 8;
		} else if (isFullHouse()) {
			return 7;
		}else if (isFlush()) {
			return 6;
		}else if (isStraight()) {
			return 5;
		} else if (isThreeOfAKind()) {
			return 4;
		} else if (isTwoPair()) {
			return 3;

		} else if (isOnePair()) {
			return 2;

		}   else return 1;

	}

	@Override
	public int compareTo(PokerHand other) {
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
		} else if (getHandTypeValue() == other.getHandTypeValue()) {
			if (getHandRank() < other.getHandRank()) {
				return -1;
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
			}else if (getHandRank() ==other.getHandRank()) {
				return 0;
			}

		}
		return -100000;

	}

}



