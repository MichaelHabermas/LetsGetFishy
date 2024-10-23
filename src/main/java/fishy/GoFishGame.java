package fishy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class GoFishGame extends JPanel {
    private Deck deck;
    private final Player player;
    private final Player computer;
    private Card selectedCard;
    private boolean playerTurn;

    public GoFishGame() {
        deck = new Deck();
        player = new Player("Player");
        computer = new Player("Computer");
        initializeGame();

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLUE);

        playerTurn = true;

        addMouseListener(new CardClickListener());
    }

    private void initializeGame() {
        for (int i = 0; i < 5; i++) {
            player.addCard(deck.drawCard());
            computer.addCard(deck.drawCard());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCards(g, player.getHand(), 50, 400, false);
        drawCards(g, computer.getHand(), 50, 50 ,true);

        g.setColor(Color.WHITE);
        g.drawString("Player Score: " + player.getScore(), 50, 550);
        g.drawString("Computer Score: " + computer.getScore(), 50, 30);
    }

    private void drawCards(Graphics g, List<Card> hand, int x, int y, Boolean isOpponent) {
        for (Card card : hand) {
            g.drawImage(isOpponent ? card.getBack() : card.getCardFront(), x, y, null);
            x += 50;
        }
    }

    private class CardClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!playerTurn) return;

            int mouseX = e.getX();
            int mouseY = e.getY();


            List<Card> playerHand = player.getHand();
            int x = 50;
            int y = 400;

            for (int i = 0; i < playerHand.size(); i++) {
                if (mouseX >= x && mouseX <= x + 32 && mouseY >= y && mouseY <= y + 48) {
                    selectedCard = playerHand.get(i);
                    System.out.println("Player selected: " + selectedCard.getRank() + " of " + selectedCard.getSuit());

                    playerAsksForCard(selectedCard.getRank());

                    break;
                }
                x += 50;
            }

            repaint();
        }
    }


    private void playerAsksForCard(String rank) {
        boolean foundCard = false;

        for (Card card : computer.getHand()) {
            if (card.getRank().equals(rank)) {
                foundCard = true;
                System.out.println("Computer gives " + card.getRank() + " of " + card.getSuit() + " to player.");
                player.addCard(card);
            }
        }

        computer.removeCardsOfRank(rank);

        if (!foundCard) {
            System.out.println("Computer says: Go Fish!");
            goFish(player);
        }

        player.checkAndLayDownSet(rank);

        checkForWinCondition();


        playerTurn = false;
        computerTurn();
    }


    private void computerTurn() {
        if (computer.getHand().isEmpty()) {
            checkForWinCondition();
            return;
        }


        Random random = new Random();
        Card randomCard = computer.getHand().get(random.nextInt(computer.getHand().size()));
        String rank = randomCard.getRank();
        System.out.println("Computer asks for: " + rank);


        boolean foundCard = false;
        for (Card card : player.getHand()) {
            if (card.getRank().equals(rank)) {
                foundCard = true;
                System.out.println("Player gives " + card.getRank() + " of " + card.getSuit() + " to computer.");
                computer.addCard(card);
            }
        }

        player.removeCardsOfRank(rank);

        if (!foundCard) {
            System.out.println("Player says: Go Fish!");
            goFish(computer);
        }

        computer.checkAndLayDownSet(rank);

        checkForWinCondition();

        playerTurn = true;
    }

    private void goFish(Player player) {
        if (!deck.isEmpty()) {
            Card newCard = deck.drawCard();
            System.out.println(player.getName() + " draws " + newCard.getRank() + " of " + newCard.getSuit());
            player.addCard(newCard);
        }
    }

    private void checkForWinCondition() {
        if (deck.isEmpty() && (player.getHand().isEmpty() || computer.getHand().isEmpty())) {
            String winner;
            if (player.getScore() > computer.getScore()) {
                winner = "Player wins!";
            } else if (computer.getScore() > player.getScore()) {
                winner = "Computer wins!";
            } else {
                winner = "It's a tie!";
            }
            System.out.println(winner);
            JOptionPane.showMessageDialog(this, winner);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Go Fish Game");
        GoFishGame game = new GoFishGame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
