import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Frame("Blackjack", 700, 700);
        HashMap<String, Integer> cards = CardHandler.cardGenerator();
        System.out.println(cards);

        List<String> cardKey = CardHandler.cardShuffle(cards);
        
        System.out.println("\n" + cardKey);
        System.out.println(CardHandler.drawCard(cards, cardKey));
    }
}
