import java.util.ArrayList;

public class GameBlackJack {


    private ArrayList<Player> players;
    private Player dealer;

    public GameBlackJack() {
        this.players = new ArrayList<Player>();
        this.dealer = new Player("Dealer");
    }


    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getDealer() {
        return this.dealer;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void newGameDeal(Deck deck) {
        deck.populateDeck();
        deck.shuffleDeck();

        for (Player player : this.players) {
            player.clearHand();
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }
        this.dealer.clearHand();
        this.dealer.addCard(deck.dealCard());
        this.dealer.addCard(deck.dealCard());
    }

    public int getScore(Player player) {
        int score = 0;
        int aces = 0;
        for (Card card : player.getHand()){
            score += card.getValueFromEnum();
            if (card.getRank() == RankType.ACE) {
                aces += 1;
            }
        };
        if (score > 21 && aces > 0) {
            while (score > 21 && aces >0) {
                score -= 10;
                aces -=1;
            }
        }
        return score;
    }


    public boolean checkWin(Player player) {
        int playerScore = getScore(player);
        int dealerScore = getScore(this.dealer);
        if (playerScore > 21) {
            return false;
        }
        else if (playerScore > dealerScore) {
            return true;
        } else if (playerScore < dealerScore) {
            return false;
        } else if (this.dealer.getHand().size() == 2 && dealerScore == 21){
            // hand size of 2 and score of 21 means dealer has blackjack so wins
            return false;
        } else if (player.getHand().size() == 2 && playerScore == 21) {
            // hand size of 2 and score of 21 means player has blackjack and dealer does not
            return true;
        } else {
            // means hands are equal no player has black jack so player wins.
            return false;
        }
    }


    public String displayPlayers() {
        String string = "Players:";
        int counter = 1;

        for (Player player : getPlayers()) {
            string +=  " " + counter + " = " + player.getName();
            counter++;
        }
        return string;
    }

    public void dealerPlay(Deck deck) {
        int score = getScore(this.dealer);
        System.out.println(this.dealer.getName()+ " has " + score +": "+ this.dealer.displayHand());
        while (score < 16 && deck.getCards().size() > 0) {
            this.dealer.addCard(deck.dealCard());
            score = getScore(this.dealer);
            System.out.println(this.dealer.getName()+ " has " + score +": "+ this.dealer.displayHand());
            if (score > 21) {
                System.out.println("Dealer is bust");
            }
        }
    }
}
