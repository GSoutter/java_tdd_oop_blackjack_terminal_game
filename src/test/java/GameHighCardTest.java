import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameHighCardTest {

    GameHighCard game;
    Player player1;
    Player player2;
    Player player3;
    private Deck deck;


    @Before
    public void before() {
        game = new GameHighCard();
        player1 = new Player("Dave");
        player2 = new Player("Dom");
        player3 = new Player("Darlene");
        deck = new Deck();
        deck.populateDeck();
//        deck.shuffleDeck();

    }

    @Test
    public void hasNoPlayers(){
        assertEquals(0, game.getPlayers().size());
    }

    @Test
    public void canAddPlayers(){
        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void canDealCards(){
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.dealCards(deck);
        assertEquals(1, game.getPlayers().get(0).getHand().size());
    }

    @Test
    public void canGetWinner(){
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.dealCards(deck);
        ArrayList<Player> winners = game.getWinner();
        assertEquals(1, winners.size());
    }

}
