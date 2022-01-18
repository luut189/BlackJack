import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel {

    static final int unitSize = 100;
    private int width, height;

    Panel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.width = width;
        this.height = height;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for(int i = 0; i < width/unitSize; i++) {
            g.drawLine(0, i*unitSize, width, i*unitSize);
        }
        for(int i = 0; i < height/unitSize; i++) {
            g.drawLine(i*unitSize, 0, i*unitSize, height);
        }
    }
}
