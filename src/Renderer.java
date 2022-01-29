import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Renderer extends JPanel {

    // declare and initialize the unit size for easier rendering
    static final int unitSize = 25;

    // declare the width and height
    private int width, height;

    public HashMap<String, Image> cardImg;

    // constructor
    Renderer(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(67, 143, 71));
        this.width = width;
        this.height = height;

        // initialize the HashMap using method
        cardImg = imageRenderer();
    }

    // method to render the image of each card in the deck as well as the backside of the card and store them in a HashMap with its corresponding key.
    public HashMap<String, Image> imageRenderer() {
        HashMap<String, Image> cards = new HashMap<>();
        char[] symbol = {'H', 'D', 'C', 'S'};
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 13; j++) {
                if(j <= 10) {
                    cards.put(symbol[i] + String.valueOf(j), new ImageIcon(getClass().getResource("assets/" + symbol[i] + String.valueOf(j) + ".png")).getImage());
                } else {
                    if(j == 11) cards.put(symbol[i] + "J", new ImageIcon(getClass().getResource("assets/" + symbol[i] + "J" + ".png")).getImage());
                    else if(j == 12) cards.put(symbol[i] + "Q", new ImageIcon(getClass().getResource("assets/" + symbol[i] + "Q" + ".png")).getImage());
                    else if(j == 13) cards.put(symbol[i] + "K", new ImageIcon(getClass().getResource("assets/" + symbol[i] + "K" + ".png")).getImage());
                }
            }
        }
        cards.put("Backside", new ImageIcon(getClass().getResource("assets/Backside.png")).getImage());
        return cards;
    }


    // this will be responsible for drawing all the component onto the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // graphics class' draw() method
    public void draw(Graphics g) {
        // Draw the game board
        g.setColor(new Color(48, 135, 45));
        for(int i = 0; i < width/unitSize; i++) {
            g.drawLine(0, i*unitSize, width, i*unitSize);
        }
        for(int i = 0; i < height/unitSize; i++) {
            g.drawLine(i*unitSize, 0, i*unitSize, height);
        }
        
        // Draw the deck of cards
        for(int i = 0; i < Main.cardKey.size(); i++) {
            g.drawImage(cardImg.get("Backside"), i*12+20, height/2-(5*unitSize)+2*unitSize+12, 4*unitSize, 5*unitSize, null);
        }

        // Draw the player's cards
        for(int i = 0; i < Main.playerCard.size(); i++) {
            g.drawImage(cardImg.get(Main.playerCard.get(i)), i*20+60, height-(5*unitSize)-20, 4*unitSize, 5*unitSize, null);
        }

        // Draw the dealer's cards
        if(!DealerHandler.isStand) {
            for(int i = 0; i < Main.dealerCard.size(); i++) {
                if(i == 0) g.drawImage(cardImg.get(Main.dealerCard.get(i)), i*20+60, 20, 4*unitSize, 5*unitSize, null);
                else g.drawImage(cardImg.get("Backside"), i*20+60, 20, 4*unitSize, 5*unitSize, null);
            }
        } else {
            for(int i = 0; i < Main.dealerCard.size(); i++) {
                g.drawImage(cardImg.get(Main.dealerCard.get(i)), i*20+60, 20, 4*unitSize, 5*unitSize, null);
            }
        }
    }
}
