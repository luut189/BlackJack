import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    
    Panel panel;
    JPanel gamePanel = new JPanel();
    JTextField score;

    public static JButton drawButton, restartButton;

    Frame(String name, int width, int height) {
        panel = new Panel(width, height);
        
        drawButton = new JButton("Draw!");
        drawButton.addActionListener(this);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        score = new JTextField("HELLLOOOOOOOOO");
        score.setEditable(false);

        gamePanel.setPreferredSize(new Dimension(200, 700));
        gamePanel.add(drawButton);
        gamePanel.add(restartButton);
        gamePanel.add(score, BorderLayout.SOUTH);

        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel, BorderLayout.CENTER);
        this.add(gamePanel, BorderLayout.EAST);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Frame.restartButton) {
            Main.playerCard.removeAll(Main.playerCard);
            Main.dealerCard.removeAll(Main.dealerCard);

            Main.cards = CardHandler.cardGenerator();

            Main.cardKey = CardHandler.cardShuffle(Main.cards);

            CardHandler.getCards(Main.playerCard, Main.dealerCard, Main.cards, Main.cardKey);

            System.out.println("Player cards: " + Main.playerCard);
            System.out.println("Dealer cards: " + Main.dealerCard + "\n");
        } else if(e.getSource() == Frame.drawButton) {
            Main.playerCard.add(CardHandler.drawCard(Main.cards, Main.cardKey));
        }
    }
}
