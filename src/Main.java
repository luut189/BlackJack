import java.util.*;

public class Main {

    // declare the deck of cards as a HashMap and the list of cards' keys as a List
    public static HashMap<String, Integer> cards;
    public static List<String> cardKey;

    // declare the player's and dealer's cards deck
    public static ArrayList<String> playerCard, dealerCard;

    // declare and initialize the base cards deck to check value
    public static final HashMap<String, Integer> baseCards = CardHandler.cardGenerator();

    public static void main(String[] args) {
        // create a new frame to start the program
        new Frame("Blackjack", 700, 700);

        // initialize the player's and dealer's cards deck
        playerCard = new ArrayList<String>();
        dealerCard = new ArrayList<String>();

        // generate the cards deck and and cards' keys
        cards = CardHandler.cardGenerator();
        cardKey = CardHandler.cardShuffle(cards);

        // distribute and print out the cards (debug)
        CardHandler.getCards(playerCard, dealerCard, cards, cardKey);
        System.out.println("Dealer cards: " + dealerCard);
        System.out.println("Player cards: " + playerCard + "\n");
    }
}
