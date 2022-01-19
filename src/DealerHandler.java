import java.util.*;

public class DealerHandler {
    
    public static boolean isStand = false;

    public static int dealerMove(HashMap<String, Integer> cards, List<String> cardKey, ArrayList<String> dealerCard, int sum) {
        if(isStand) {
            while(sum < 17) {
                dealerCard.add(CardHandler.drawCard(cards, cardKey));
                sum = dealerSum(dealerCard);
                if(sum > 16) break;
            }
        }
        sum = dealerSum(dealerCard);
        return sum;
    }

    public static int dealerSum(ArrayList<String> dealerCard) {
        int sum = 0;
        boolean hadAce = false;
        
        for(int i = 0; i < dealerCard.size(); i++) {
            if(dealerCard.get(i).length() != 3 && dealerCard.get(i).charAt(1) == '1' && !hadAce) {
                sum += 11;
                hadAce = true;
            } else {
                sum += Main.baseCards.get(dealerCard.get(i));
            }
        }
        return sum;
    }

}