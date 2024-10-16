package fishy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
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
        Iterator<Card> iterator = hand.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getRank().equals(rank)) {
                iterator.remove();
            }
        }
    }

    public boolean checkAndLayDownSet(String rank) {
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
            return true;
        }

        return false;
    }

    public boolean hasCardsOfRank(String rank) {
        for (Card card : hand) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }
}
