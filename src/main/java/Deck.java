import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }


    public ArrayList<Card> getCards() {
        return this.cards;
    }


    public void populateDeck() {
        this.cards = new ArrayList<Card>();
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                this.cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }


    public Card dealCard() {
        return this.cards.remove(0);
    }
}
