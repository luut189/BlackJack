import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    
    Renderer panel;
    JPanel gamePanel = new JPanel();
    JTextArea score;

    public static JButton drawButton, restartButton, confirmButton;

    Frame(String name, int width, int height) {
        panel = new Renderer(width, height);
        
        drawButton = new JButton("Hit!");
        drawButton.addActionListener(this);
        
        confirmButton = new JButton("Stand!");
        confirmButton.addActionListener(this);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        score = new JTextArea("Your points: \n" +
                              "Dealer points: ");
        score.setEditable(false);

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

            score.setText("Your points: \n" +
                          "Dealer points: ");
            drawButton.setEnabled(true);
            DealerHandler.isStand = false;

            System.out.println("Dealer cards: " + Main.dealerCard);
            System.out.println("Player cards: " + Main.playerCard + "\n");
            repaint();
        } else if(n == drawButton) {
            Main.playerCard.add(CardHandler.drawCard(Main.cards, Main.cardKey));
            System.out.println(Main.playerCard);
            repaint();
        } else if(n == confirmButton) {
            int playerSum = CardHandler.playerSum(Main.playerCard);
            drawButton.setEnabled(false);
            DealerHandler.isStand = true;
            repaint();
            int dealerSum = DealerHandler.dealerMove(Main.cards, Main.cardKey, Main.dealerCard, DealerHandler.dealerSum(Main.dealerCard));
            if(playerSum > 21 && dealerSum > 21) {
                if(playerSum < dealerSum) {
                    score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                                  "Your points: " + String.valueOf(playerSum) + "\n" +
                                  "Player wins!");
                } else {
                    score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                                  "Your points: " + String.valueOf(playerSum) + "\n" +
                                  "Dealer wins!");
                }
            } else if (playerSum <= 21 && dealerSum <= 21) {
                if(playerSum > dealerSum) {
                    score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                                  "Your points: " + String.valueOf(playerSum) + "\n" +
                                  "Player wins!");
                } else {
                    score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                                  "Your points: " + String.valueOf(playerSum) + "\n" +
                                  "Dealer wins!");
                }
            } else if(playerSum == dealerSum) {
                score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                              "Your points: " + String.valueOf(playerSum) + "\n" +
                              "Draw!");
            } else if(dealerSum > 21 && playerSum <= 21) {
                score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                              "Your points: " + String.valueOf(playerSum) + "\n" +
                              "Player wins!");
            } else if(playerSum > 21 && dealerSum <= 21) {
                score.setText("Dealer points: " + String.valueOf(dealerSum) + "\n" +
                              "Your points: " + String.valueOf(playerSum) + "\n" +
                              "Dealer wins!");
            }
        }
    }
}
