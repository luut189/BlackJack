import java.util.*;

public class CardHandler {

    public static HashMap<String, Integer> cardGenerator() {
        HashMap<String, Integer> cards = new HashMap<String, Integer>();
        char[] symbol = {'H', 'D', 'C', 'S'};
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 13; j++) {
                if(j <= 10) {
                    cards.put(symbol[i] + String.valueOf(j), j);
                } else {
                    if(j == 11) cards.put(symbol[i] + "J", 10);
                    else if(j == 12) cards.put(symbol[i] + "Q", 10);
                    else if(j == 13) cards.put(symbol[i] + "K", 10);
                }
            }
        }
        return cards;
    }

    public static List<String> cardShuffle(HashMap<String, Integer> cards) {
        List<String> cardKey = new ArrayList<String>(cards.keySet());
        Collections.shuffle(cardKey);
        return cardKey;
    }

    public static int getCard(HashMap<String, Integer> cards, List<String> cardKey) {
        int card = cards.get(cardKey.get(0));
        cards.remove(cardKey.get(0));
        cardKey.remove(0);
        return card;
    }

    public static int drawCard(HashMap<String, Integer> cards, List<String> cardKey) {
        int card = cards.get(cardKey.get(51));
        cards.remove(cardKey.get(51));
        cardKey.remove(51);
        return card;
    }
}
