import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Renderer extends JPanel {

    static final int unitSize = 25;
    private int width, height;

    public HashMap<String, Image> cardImg;

    Renderer(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(78, 169, 101));
        this.width = width;
        this.height = height;

        cardImg = imageRenderer();
    }

    public HashMap<String, Image> imageRenderer() {
        HashMap<String, Image> cards = new HashMap<>();
        char[] symbol = {'H', 'D', 'C', 'S'};
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 13; j++) {
                if(j <= 10) {
                    cards.put(symbol[i] + String.valueOf(j), new ImageIcon("../assets/" + symbol[i] + String.valueOf(j) + ".png").getImage());
                } else {
                    if(j == 11) cards.put(symbol[i] + "J", new ImageIcon("../assets/" + symbol[i] + "J" + ".png").getImage());
                    else if(j == 12) cards.put(symbol[i] + "Q", new ImageIcon("../assets/" + symbol[i] + "Q" + ".png").getImage());
                    else if(j == 13) cards.put(symbol[i] + "K", new ImageIcon("../assets/" + symbol[i] + "K" + ".png").getImage());
                }
            }
        }
        cards.put("Backside", new ImageIcon("../assets/Backside.png").getImage());
        return cards;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(67, 143, 71));
        for(int i = 0; i < width/unitSize; i++) {
            g.drawLine(0, i*unitSize, width, i*unitSize);
        }
        for(int i = 0; i < height/unitSize; i++) {
            g.drawLine(i*unitSize, 0, i*unitSize, height);
        }
        
        for(int i = 0; i < Main.cardKey.size(); i++) {
            g.drawImage(cardImg.get("Backside"), i*10+60, height/2-(5*unitSize), 4*unitSize, 5*unitSize, null);
        }
    }
}