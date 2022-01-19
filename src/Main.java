import java.util.*;

public class Main {

    public static HashMap<String, Integer> cards;
    public static List<String> cardKey;

    public static ArrayList<Integer> playerCard, dealerCard; 

    public static void main(String[] args) {
        new Frame("Blackjack", 700, 700);
        playerCard = new ArrayList<Integer>();
        dealerCard = new ArrayList<Integer>();
        cards = CardHandler.cardGenerator();
        cardKey = CardHandler.cardShuffle(cards);
        CardHandler.getCards(playerCard, dealerCard, cards, cardKey);
        System.out.println("Player cards: " + playerCard);
        System.out.println("Dealer cards: " + dealerCard + "\n");
    }
}
