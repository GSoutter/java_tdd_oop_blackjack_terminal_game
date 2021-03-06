import java.util.ArrayList;

public class Player {

    private final String name;
    private final ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }


    public void clearHand() {
        this.hand.clear();
    }

    public String displayHand() {
        String handString = "";
        for (Card card : this.hand){
            String suit = card.getSuit().toString();
            String rank = card.getRank().toString();
            handString += rank + " of " + suit + ", ";
        }
        return handString;
    }
}
