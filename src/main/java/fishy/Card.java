package fishy;

import java.awt.image.BufferedImage;

public class Card {
    private final String rank;
    private final String suit;
    private final BufferedImage cardFront;
    private final BufferedImage cardBack;

    public Card(String rank, String suit, BufferedImage cardFront, BufferedImage cardBack) {
        this.rank = rank;
        this.suit = suit;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
    }

    public BufferedImage getCardFront() {
        return cardFront;
    }

    public BufferedImage getBack() { return cardBack; }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
