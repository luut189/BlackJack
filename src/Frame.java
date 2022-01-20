import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
    
    // declare the non-static components of the program
    Renderer panel;
    JPanel gamePanel = new JPanel();

    // declare static JTextArea
    public static JTextArea score;

    // declare static JButtons
    public static JButton drawButton, restartButton, confirmButton;

    Frame(String name, int width, int height) {
        // initialize the graphic panel
        panel = new Renderer(width, height);
        
        // initialize all of the JButtons of the game
        drawButton = new JButton("Hit!");
        drawButton.addActionListener(this);
        
        confirmButton = new JButton("Stand!");
        confirmButton.addActionListener(this);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        // initialize the JTextArea component for displaying points and winner
        score = new JTextArea("Dealer points:  \t\t" +
                              "Your points:  \n" +
                              "Status: ");
        score.setEditable(false);


        // setup the game panel and adding component into it
        gamePanel.setPreferredSize(new Dimension(700, 50));
        gamePanel.setLayout(new FlowLayout());
        gamePanel.add(drawButton);
        gamePanel.add(confirmButton);
        gamePanel.add(restartButton);
        gamePanel.add(score);

        // setup the main frame of the program
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(gamePanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object n = e.getSource();
        if(n == restartButton) {
            // remove the player's and the dealer's cards
            Main.playerCard.removeAll(Main.playerCard);
            Main.dealerCard.removeAll(Main.dealerCard);

            // generate and shuffle a new deck of cards
            Main.cards = CardHandler.cardGenerator();
            Main.cardKey = CardHandler.cardShuffle(Main.cards);

            // distribute the card for player and dealer
            CardHandler.getCards(Main.playerCard, Main.dealerCard, Main.cards, Main.cardKey);

            // reset the components
            score.setText("Dealer points:  \t\t" +
                          "Your points:  \n" +
                          "Status: ");
            drawButton.setEnabled(true);
            confirmButton.setEnabled(true);

            // switch to the player turn
            DealerHandler.isStand = false;

            System.out.println("Dealer cards: " + Main.dealerCard);
            System.out.println("Player cards: " + Main.playerCard + "\n");
            repaint();

        } else if(n == drawButton) {
            // draw a card from the deck
            Main.playerCard.add(CardHandler.drawCard(Main.cards, Main.cardKey));

            // print the player cards (debug)
            System.out.println(Main.playerCard);
            repaint();
        } else if(n == confirmButton) {
            // calculate the sum of player cards
            int playerSum = CardHandler.playerSum(Main.playerCard);

            // disable buttons
            drawButton.setEnabled(false);
            confirmButton.setEnabled(false);

            // switch to the dealer turn
            DealerHandler.isStand = true;

            repaint();

            // calculate the sum of dealer cards
            int dealerSum = DealerHandler.dealerMove(Main.cards, Main.cardKey, Main.dealerCard, DealerHandler.dealerSum(Main.dealerCard));

            // set the winner of the game
            CardHandler.setWinner(playerSum, dealerSum);
        }
    }
}
