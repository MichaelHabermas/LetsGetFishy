package fishy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                String fileName = "cards/" + rank + "_" + suit + ".png";

                try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
                    if (is != null) {
                        BufferedImage image = ImageIO.read(is);
                        cards.add(new Card(rank, suit, image));
                    } else {
                        System.err.println("Image not found: " + fileName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return size() <= 0;
    }
}
