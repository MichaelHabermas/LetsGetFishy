package fishy;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCardsOfRank(String rank) {
        hand.removeIf(card -> card.getRank().equals(rank));
    }

    public void checkAndLayDownSet(String rank) {
        int count = 0;
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                count++;
            }
        }

        if (count == 4) {
            removeCardsOfRank(rank);
            score++;
            System.out.println(name + " has completed a set of " + rank + "!");
        }
    }
}
