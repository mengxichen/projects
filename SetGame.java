/*Mengxi Chen, mengxi.chen.2006@gmail.com
 * Set Game s a card game where a group of players try to identify a "set" of 
 * cards from those placed face-up on the table. 
 * Three cards has an image on it with 4 orthogonal attributes. Three cards are 
 * a part of a set if, for each property, the values are all the same or all different.
 * Assumptions I have made:
 * 1. Each round at most one set can be withdrawn from the table set. 
 * 2. The given deck of cards cannot always be used up since it can have numbers of cards 
 * that is not divisible by 3
 */
import java.util.*;


public class SetGame {
	private static ArrayList<Card> deck = new ArrayList<Card>(); 
	//store values for each attribute
	private  Set<String >colorSet = new HashSet<String>();
	private  Set<String >shapeSet = new HashSet<String>();
	private  Set<String >shadingSet = new HashSet<String>();
	private  Set<String >numberSet = new HashSet<String>();
	
		
	  public class Card {
		
		//Color is one of "red","green", or "purple" in colorSet
		public final String color;
		
		//Shape is one of "diamond","squiggle", or "oval" in shapeSet
		public final String shape;
		
		//Shading is one of "solid","empty",or "striped" in shadingSet
		public final String shading;
		
		//Number is one of "one", "two", or "three" in numberSet
		public final String number;
		
	    /**
	     * Initializes a card 
	     * @throws IllegalArgumentException if color, shape, shading or number are not valid
	     */
		
		public Card(String color, String shape, String shading, String number){
			if(! colorSet.contains(color)){
				throw new IndexOutOfBoundsException("invalid color");
			}
			if(!shapeSet.contains(shape)){
				throw new IndexOutOfBoundsException("invalid shape");			
			}
			if(!shadingSet.contains(shading)){
				throw new IndexOutOfBoundsException("invalid shading");
			}
			if(!numberSet.contains(number)){
				throw new IndexOutOfBoundsException("invalid number");
			}
			this.color = color;
			this.shape = shape;
			this.shading = shading;
			this.number = number;
		}

		
	}
		
	
	// GIVEN: two cards 
	// RETURN: a predicted card with new attributes based on the given two cards
	// PURPOSE: if the attribute of two cards are the same,
	//          return the attribute, otherwise get the third attribute 
	//          which is different from the old ones
	private Card fromTwoToThird(Card card1,Card card2){
		String color = thirdValue(card1.color, card2.color,colorSet);
		String shape = thirdValue(card1.shape, card2.shape,shapeSet);;
		String shading = thirdValue(card1.shading, card2.shading, shadingSet);;
		String number = thirdValue(card1.number, card2.number,numberSet);;
		
		return new Card(color,shape,shading,number);
	}
	
	
	// GIVEN: two values of the same attribute and a set of values in this attribute
	// RETURN: the third attribute 
	// PURPOSE:if the attribute of two cards are the same,
	//          return the attribute, otherwise get the third attribute 
	//          which is different from the old ones
	private String thirdValue(String att1,String att2,Set<String> set){
		if(att1.equals(att2)){
			return att1;
		}
		for(String s : set){
			if(!s.equals(att1) && !s.equals(att2)){
				return s;
			}
		}
		return "";
		
	}
	
	
	// GIVEN: three cards
	// RETURN: true if they can form a set, false otherwise
	public boolean isSet(Card card1,Card card2,Card card3){
		return (isSame(card3, fromTwoToThird(card1,card2)));
		
	}
	
	
	// GIVEN: two cards
	// RETURN: true if all the attributes are the same, false otherwise
	private boolean isSame(Card card1,Card card2){
		return (card1.color.equals(card2.color) &&
				card1.shape.equals(card2.shape) &&
				card1.shading.equals(card2.shading) &&
				card1.number.equals(card2.number));
	}
	
	
	// GIVEN: a set of cards
	// RETURN: if there is a set, return it, otherwise return an empty set
	public ArrayList<Card> getSet(ArrayList<Card> set){
		ArrayList<Card> result = new ArrayList<Card> ();
		if(set.size() < 3){ return result;}
		for(int i = 0; i < set.size()-1; i++){
			for(int k = i+1; k < set.size();k++){
				for(int g = 0; g < set.size(); g++){
					if(g != i && g != k){ //ignore the selected two cards
						if(isSet(set.get(i),set.get(k),set.get(g))){
							result.add(set.get(i));
							result.add(set.get(k));
							result.add(set.get(g));
							return result;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	
	
	
	// GIVEN: two lists of cards, from and to, and a number num
	// RETURN: move num amount of cards between two lists
	public ArrayList<Card> cardMove(ArrayList<Card> from, ArrayList<Card> to, int num){

		for(int i = 0; i < num ; i++){
			if(from.isEmpty()) break;
			Card temp = from.remove(0);
			to.add(temp);
		}
		
		return to;
	}
	
	
	// GIVEN: two lists of cards toBeRemoved and from
	// RETURN: return from without elements in toBeRemoved
	public void removeCards(ArrayList<Card> toBeRemoved, ArrayList<Card> from){
		System.out.println("from list size: " + from.size());
        for (Card card : toBeRemoved) {
        	System.out.print("card needs to be removed ");
        	System.out.println(card.color + " "+ card.shape +" " + card.shading + " " + card.number);
            System.out.println("remove?" + from.remove(card));
         }
		
	}
	
	
	
	// GIVEN: a board of cards
	// RETURN: null
	// PURPOSE: play the game, each time either find a set or cannot find a set. If the players 
	// cannot find a set, then add three more cards from the deck to the table. If the players can
	// find one set, then add the set to the valid Set list, and remove the set from the table, 
	// then add three new cards from the deck if it has.  The game ends when 
	// there are no more cards in the deck and table . 
	public void play(ArrayList<Card> deck){
		int round = 0; //round count for one game
		Collections.shuffle(deck);
		ArrayList<Card> cardsOnTable = new ArrayList<Card>();
		ArrayList<ArrayList<Card>> validSets = new ArrayList<ArrayList<Card>> ();	
		
		//initialize 12 cards on the table
		cardsOnTable = cardMove(deck, cardsOnTable, 12 );
		
		//terminate when there is no card in deck or we cannot find any set among cards
		// on the table
		while(!deck.isEmpty() || (getSet(cardsOnTable).size() != 0) ){
			round++;
			ArrayList<Card> newSet = getSet(cardsOnTable);
			if(newSet.size() == 0){
				System.out.println("cannot find a set");
			}else{
				validSets.add(newSet);
				removeCards(newSet,cardsOnTable);
				
			}
			Collections.shuffle(deck);
			cardsOnTable=cardMove(deck,cardsOnTable,3);
		}
		
		System.out.println("The game has " + round + " rounds.");
		System.out.println("all valid sets: ");
		for(ArrayList<Card> arr : validSets){
			Card c1 = arr.get(0);
			Card c2 = arr.get(1);
			Card c3 = arr.get(2);
			System.out.println("a valid set: ");
			System.out.println(c1.color + " "+ c1.shape +" " + c1.shading + " " + c1.number);
			System.out.println(c2.color + " "+ c2.shape +" " + c2.shading + " " + c2.number);
			System.out.println(c3.color + " "+ c3.shape +" " + c3.shading + " " + c3.number);
		}
	}
	
	
	// GIVEN: no arguments
	// RETURN: create a sample deck for testing purpose
	public void createDeck(){
		
		deck.add(new Card("red","diamond","solid","one"));
		deck.add(new Card("red","squiggle","solid","two"));
		deck.add(new Card("red","oval","solid","three"));
		
		deck.add(new Card("green","diamond","solid","one"));
		deck.add(new Card("red","diamond","empty","two"));
		deck.add(new Card("purple","diamond","striped","three"));
		
		deck.add(new Card("green","squiggle","solid","one"));
		deck.add(new Card("red","diamond","empty","one"));
		deck.add(new Card("purple","oval","striped","one"));
		
		deck.add(new Card("green","diamond","solid","one"));
		deck.add(new Card("red","diamond","solid","two"));
		deck.add(new Card("purple","diamond","solid","three"));
		
		deck.add(new Card("purple","diamond","solid","one"));
		deck.add(new Card("purple","diamond","solid","two"));
		deck.add(new Card("purple","diamond","solid","three"));
	}
	
	

	public static void main(String args[]){
		SetGame newGame = new SetGame();
		
		//initialize card's property
		newGame.colorSet.add("red");
		newGame.colorSet.add("green");
		newGame.colorSet.add("purple");
		newGame.shapeSet.add("diamond");
		newGame.shapeSet.add("squiggle");
		newGame.shapeSet.add("oval");
		newGame.shadingSet.add("solid");
		newGame.shadingSet.add("empty");
		newGame.shadingSet.add("striped");
		newGame.numberSet.add("one");
		newGame.numberSet.add("two");
		newGame.numberSet.add("three");
		
		//initialize the world , add cards to deck set
		newGame.createDeck();
		System.out.println(deck.size());
		
		
		//play game
		newGame.play(deck);
		

	}
	
	
}
