package fishy;

import java.awt.image.BufferedImage;

public class Card {
    private String rank;
    private String suit;
    private BufferedImage image;

    public Card(String rank, String suit, BufferedImage image) {
        this.rank = rank;
        this.suit = suit;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
}
