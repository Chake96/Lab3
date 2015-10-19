package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private static final int CardSubstitue = 0;
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand; //gives you the best cards in the hand

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private ArrayList<Card> Kickers = new ArrayList<Card>();

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dnonJoker = new Deck();

	public Hand()
	{
		//does nothing
	}
	public void  AddCardToHand(Card c) //this may need to be refactored
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}

	public Card  GetCardFromHand(int location) //may not be needed 
	{
		return CardsInHand.get(location);
	}

	public Hand(Deck d) { //builds the hand from the deck
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}

	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public ArrayList<Card> getKicker() {
		return Kickers;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {

		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}
	private static ArrayList<Hand> substituteHand(ArrayList<Hand> subHand, int CardSub){
		
		ArrayList<Hand> hands= subHand;
		
		for(Hand h: hands){
			ArrayList<Card> cards = h.getCards();
			
			for(Card CardstoCheck: cards){
				if (CardstoCheck.getWild() == true || CardstoCheck.getRank() == eRank.JOKER){
					for (Card wildSub: dnonJoker.getCards()){
						ArrayList<Card> subCards = new ArrayList<Card>();
						for(int a =0; a< h.getCards().size(); a++){
							if (a == CardSub){
								
							}
						}
					}
				}
			}
		}
		
		if () //????
		
	}
	
	private static ArrayList<Hand> explodeHand(Hand h){
		ArrayList<Hand> HandstoReturn = new ArrayList<Hand>();
		HandstoReturn.add(h);
		for(int a = 0; a < h.CardsInHand.size();a++){
			HandstoReturn = substituteHand(HandstoReturn, a);
		}
		return HandstoReturn;
	}
	
	private static Hand EvalHand(Hand h){
		ArrayList<Hand> EvalHands = explodeHand(h);
		
		return //????
	}


	public void EvalHands() {
		
		ArrayList<Hand> evalhand = explodeHand(this);
		for () //???
		
		
		ArrayList<Card> remainingCards = new ArrayList<Card>();

		// Sort the cards! 
		Collections.sort(CardsInHand, Card.CardRank);

				
		// Ace Evaluation 
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

			// Flush Evaluation
			if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getSuit()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getSuit()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getSuit()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
				Flush = true;
			} else {
				Flush = false;
			}

			// Straight Evaluation
			if (Ace) {
				// Looks for Ace, King, Queen, Jack, 10
				if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
						&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
						&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
						.getRank() == eRank.JACK
						&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
					Straight = true;
					// Looks for Ace, 2, 3, 4, 5
				} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
						&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
						.getRank() == eRank.THREE
						&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
						&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
						.getRank() == eRank.FIVE) {
					Straight = true;
				} else {
					Straight = false;
				}
				// Looks for straight without Ace
			} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
					.getRank().getRank() + 1
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank()
					.getRank() + 3
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
				Straight = true;
			} else {
				Straight = false;
			}

			//Royal Flush
			if (Straight == true
					&& Flush == true //&& means that if this part of the if is false don't evaluate the rest of the statement and return false
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
					&& Ace) {
				ScoreHand(eHandStrength.RoyalFlush, 0, 0, null);
			}

			// Straight Flush
			else if (Straight == true && Flush == true) {
				remainingCards = null;
				ScoreHand(eHandStrength.StraightFlush,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
								.getRank(), 0, remainingCards);
			}

			// Four of a Kind

			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
				ScoreHand(eHandStrength.FourOfAKind,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}

			else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				ScoreHand(eHandStrength.FourOfAKind,
						CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}

			// Full House
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank()) {
				remainingCards = null;
				ScoreHand(eHandStrength.FullHouse,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(),
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank(), remainingCards);
			}

			else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank()
					&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()) {
				remainingCards = null;
				ScoreHand(eHandStrength.FullHouse,
						CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank(),
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), remainingCards);
			}

			// Flush
			else if (Flush) {
				remainingCards = null;
				ScoreHand(eHandStrength.Flush,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0, remainingCards);
			}

			// Straight
			else if (Straight) {
				remainingCards = null;
				ScoreHand(eHandStrength.Straight,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0, remainingCards);
			}

			// Three of a Kind
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));			
				ScoreHand(eHandStrength.ThreeOfAKind,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}

			else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank()) {
				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));			

				ScoreHand(eHandStrength.ThreeOfAKind,
						CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank()) {
				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));				
				ScoreHand(eHandStrength.ThreeOfAKind,
						CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}

			// Two Pair
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()
					&& (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank())) {

				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

				ScoreHand(eHandStrength.TwoPair,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(),
						CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank(),
						remainingCards);
			} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()
					&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank())) {

				remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));

				ScoreHand(eHandStrength.TwoPair,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(),
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank(),
						remainingCards);
			} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()
					&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank())) {

				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				ScoreHand(eHandStrength.TwoPair,
						CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(),
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank(),
						remainingCards);
			}

			// Pair
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.SecondCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FourthCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			} else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
					.get(eCardNo.FifthCard.getCardNo()).getRank()) {

				remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));

				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}

			else {
				remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

				ScoreHand(eHandStrength.HighCard,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0,
						remainingCards);
			}
		
	}


	
	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, ArrayList<Card> kickers) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kickers = kickers;
		this.bScored = true;}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0; 

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}


			if (h2.getKicker().get(eCardNo.FirstCard.getCardNo()) != null)
			{
				if (h1.getKicker().get(eCardNo.FirstCard.getCardNo()) != null)
				{
					result = h2.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank();
				}
				if (result != 0)
				{
					return result;
				}
			}

			if (h2.getKicker().get(eCardNo.SecondCard.getCardNo()) != null)
			{
				if (h1.getKicker().get(eCardNo.SecondCard.getCardNo()) != null)
				{
					result = h2.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank();
				}
				if (result != 0)
				{
					return result;
				}
			}
			if (h2.getKicker().get(eCardNo.ThirdCard.getCardNo()) != null)
			{
				if (h1.getKicker().get(eCardNo.ThirdCard.getCardNo()) != null)
				{
					result = h2.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank();
				}
				if (result != 0)
				{
					return result;
				}
			}

			if (h2.getKicker().get(eCardNo.FourthCard.getCardNo()) != null)
			{
				if (h1.getKicker().get(eCardNo.FourthCard.getCardNo()) != null)
				{
					result = h2.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank();
				}
				if (result != 0)
				{
					return result;
				}
			}			
			return 0;
		}
	};

}

