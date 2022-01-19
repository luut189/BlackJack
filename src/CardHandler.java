import java.util.*;

public class CardHandler {

    // method to generate a HashMap of cards with their corresponding values.
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

    // method to shuffle the cards' keys and return them as a List
    public static List<String> cardShuffle(HashMap<String, Integer> cards) {
        List<String> cardKey = new ArrayList<String>(cards.keySet());
        Collections.shuffle(cardKey);
        return cardKey;
    }

    // method to get a single card key from the top of the List
    public static String getSingleCard(HashMap<String, Integer> cards, List<String> cardKey) {
        String card = cardKey.get(0);
        cards.remove(cardKey.get(0));
        cardKey.remove(0);
        return card;
    }

    // method to get a single card key from the bottom of the List
    public static String drawCard(HashMap<String, Integer> cards, List<String> cardKey) {
        String card = cardKey.get(cards.size()-1);
        cards.remove(cardKey.get(cards.size()-1));
        cardKey.remove(cards.size());
        return card;
    }

    // method to distribute the cards for the dealer and player in order.
    public static void getCards(ArrayList<String> player, ArrayList<String> dealer, HashMap<String, Integer> cards, List<String> cardKey) {
        player.add(getSingleCard(cards, cardKey));
        dealer.add(getSingleCard(cards, cardKey));
        player.add(getSingleCard(cards, cardKey));
        dealer.add(getSingleCard(cards, cardKey));
    }


    // method to calculate the player's cards' sum
    public static int playerSum(ArrayList<String> playerCard) {
        int sum = 0;
        for(int i = 0; i < playerCard.size(); i++) {
            if(playerCard.get(i).length() != 3 && playerCard.get(i).charAt(1) == '1') {
                sum += 11;
            } else {
                sum += Main.baseCards.get(playerCard.get(i));
            }
        }
        for(int i = 0; i < playerCard.size(); i++) {
            if(sum > 21 && playerCard.get(i).length() != 3 && playerCard.get(i).charAt(1) == '1') {
                sum -= 10;
            }
        }
        return sum;
    }

    // method to set the text to show the winner of the game in the program
    public static void setWinner(int playerSum, int dealerSum) {
        Frame.score.setText(winnnerMessage(playerSum, dealerSum));
    }

    /*
        helper method to check for the winner and set the "winner" variable to a different value.
        after that, this will return a String for the setWinner() method
    */
    public static String winnnerMessage(int playerSum, int dealerSum) {
        String winner;
        int check = playerWin(playerSum, dealerSum);
        if(check == 1) {
            winner = "Player wins!";
        } else if(check == 0) {
            winner = "Dealer wins!";
        } else {
            winner = "Draw!";
        }
        return 
        "Dealer points: " + String.valueOf(dealerSum) + "\n" +
        "Your points: " + String.valueOf(playerSum) + "\n" +
        winner;
    }

    /*
        method to check for the winner and return an int accordingly.
        "1" for player
        "0" for dealer
        "2" for draw
    */
    public static int playerWin(int playerSum, int dealerSum) {
        int check = 3;
        if(playerSum > 21 && dealerSum > 21) {
            if(playerSum < dealerSum) {
                check = 1;
            } else {
                check = 0;
            }
        } else if (playerSum <= 21 && dealerSum <= 21 && playerSum != dealerSum) {
            if(playerSum > dealerSum) {
                check = 1;
            } else {
                check = 0;
            }
        } else if(playerSum == dealerSum) {
            check = 2;
        } else if(dealerSum > 21 && playerSum <= 21) {
            check = 1;
        } else if(playerSum > 21 && dealerSum <= 21) {
            check = 0;
        }
        return check;
    }
}
