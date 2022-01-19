import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    
    Renderer panel;
    JPanel gamePanel = new JPanel();
    JTextField score;

    public static JButton drawButton, restartButton, confirmButton;

    Frame(String name, int width, int height) {
        panel = new Renderer(width, height);
        
        drawButton = new JButton("Hit!");
        drawButton.addActionListener(this);
        
        confirmButton = new JButton("Stand!");
        confirmButton.addActionListener(this);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        score = new JTextField("Score: ");
        score.setEditable(false);
        score.setHorizontalAlignment(SwingConstants.CENTER);

        gamePanel.setPreferredSize(new Dimension(200, 700));
        gamePanel.setLayout(new GridLayout(2, 2));
        gamePanel.add(drawButton);
        gamePanel.add(confirmButton);
        gamePanel.add(restartButton);
        gamePanel.add(score);

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
        Object n = e.getSource();
        if(n == restartButton) {
            Main.playerCard.removeAll(Main.playerCard);
            Main.dealerCard.removeAll(Main.dealerCard);

            Main.cards = CardHandler.cardGenerator();
            Main.cardKey = CardHandler.cardShuffle(Main.cards);
            CardHandler.getCards(Main.playerCard, Main.dealerCard, Main.cards, Main.cardKey);

            score.setText("Score:");

            System.out.println("Player cards: " + Main.playerCard);
            System.out.println("Dealer cards: " + Main.dealerCard + "\n");
            repaint();
        } else if(n == drawButton) {
            Main.playerCard.add(CardHandler.drawCard(Main.cards, Main.cardKey));
            System.out.println(Main.playerCard);
            repaint();
        } else if(n == confirmButton) {
            int sum = 0;
            for(int i = 0; i < Main.playerCard.size(); i++) {
                sum += Main.playerCard.get(i);
            }
            score.setText("Score: " + String.valueOf(sum));
        }
    }
}
