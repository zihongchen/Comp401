package a2;

import java.util.Scanner;

public class HandEvaluator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while(true) {
			int win = 0;
			int loss = 0;
			

			
			Card[] k = new Card[5];
			int numOpponent = scanner.nextInt();
			if (numOpponent == 0) {
				
				break;
				
			}
			int rank0 = scanner.nextInt();
			String suit0 = scanner.next();
			CardImpl card0 = new CardImpl(rank0, trans(suit0));
			k[0] = card0;
			
			int rank1 = scanner.nextInt();
			String suit1 = scanner.next();
			CardImpl card1 = new CardImpl(rank1, trans(suit1));
			k[1] = card1;
			

			int rank2 = scanner.nextInt();
			String suit2 = scanner.next();
			CardImpl card2 = new CardImpl(rank2, trans(suit2));
			k[2] = card2;

			int rank3 = scanner.nextInt();
			String suit3 = scanner.next();
			CardImpl card3 = new CardImpl(rank3, trans(suit3));
			k[3] = card3;

			int rank4 = scanner.nextInt();
			String suit4 = scanner.next();
			CardImpl card4 = new CardImpl(rank4, trans(suit4));
			k[4] = card4;

			PokerHand temp = new PokerHandImpl(k);
			for (int i = 0; i < 10000; i++) {
				int allWin=0;
				DeckImpl cool = new DeckImpl();
				
				for (int l = 0; l < 5; l++) {
					cool.findAndRemove(k[l]);
				}
				for (int l = 0; l < numOpponent; l++) {
					
					PokerHand q = cool.dealHand();
					if (temp.compareTo(q) == 1) {
						 allWin += 1;

					}
					if (allWin == numOpponent) {
						win += 1;
						allWin=0;
					} else
						loss += 1;

				}

			}
			
			System.out.println((int)(win/100 +0.5));
		}
		

	}

	public static Card.Suit trans(String a) {
		if (a.equals("S")) {
			return Card.Suit.SPADES;

		} else if (a.equals("H")) {
			return Card.Suit.HEARTS;

		} else if (a.equals("D")) {
			return Card.Suit.DIAMONDS;

		} else if (a.equals("C")) {
			return Card.Suit.CLUBS;

		}
		return null;
	}
}

