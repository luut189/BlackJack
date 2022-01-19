import java.util.*;

public class Main {

    public static HashMap<String, Integer> cards;
    public static List<String> cardKey;

    public static ArrayList<String> playerCard, dealerCard;

    public static final HashMap<String, Integer> baseCards = CardHandler.cardGenerator();

    public static void main(String[] args) {
        new Frame("Blackjack", 700, 700);
        playerCard = new ArrayList<String>();
        dealerCard = new ArrayList<String>();
        cards = CardHandler.cardGenerator();
        cardKey = CardHandler.cardShuffle(cards);
        CardHandler.getCards(playerCard, dealerCard, cards, cardKey);
        System.out.println("Dealer cards: " + dealerCard);
        System.out.println("Player cards: " + playerCard + "\n");
    }
}
