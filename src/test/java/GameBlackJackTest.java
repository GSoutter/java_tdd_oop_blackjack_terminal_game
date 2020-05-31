import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameBlackJackTest {

    GameBlackJack game;
    GameBlackJack gameWithPlayers;
    Player player1;
    Player player2;
    Player player3;
    Card aceHearts;
    Card kingHearts;
    Card threeHearts;
    Card nineHearts;
    private Deck deck;


    @Before
    public void before() {
        player1 = new Player("Dave");
        player2 = new Player("Dom");
        player3 = new Player("Darlene");

        game = new GameBlackJack();
        gameWithPlayers = new GameBlackJack();

        gameWithPlayers.addPlayer(player1);
        gameWithPlayers.addPlayer(player2);
        gameWithPlayers.addPlayer(player3);

        deck = new Deck();
        deck.populateDeck();
//        deck.shuffleDeck();

        aceHearts = new Card(SuitType.HEARTS, RankType.ACE);
        kingHearts = new Card(SuitType.HEARTS, RankType.KING);
        threeHearts = new Card(SuitType.HEARTS, RankType.THREE);
        nineHearts = new Card(SuitType.HEARTS, RankType.NINE);


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
    public void hasDealer(){
        assertEquals("Dealer", game.getDealer().getName());
    }



    @Test
    public void canStartNewGame(){
        // given a game with players
        // when i call the method to start a new game
        // then players and dealer are dealt two new cards.
        gameWithPlayers.newGameDeal(deck);

        assertEquals(2, gameWithPlayers.getPlayers().get(0).getHand().size());
        assertEquals(2, gameWithPlayers.getDealer().getHand().size());
    }

    @Test
    public void canGetScore(){
        // given a player object deal two cards from unshuffled deck. Giving ace and 2.
        // when calcScore method is run
        // a score is returned. which will be 13
        player1.addCard(deck.dealCard());
        player1.addCard(deck.dealCard());

        assertEquals(13, game.getScore(player1));
    }

    @Test
    public void canGetScoreAdjustingAce(){
        // given a player object deal two cards from unshuffled deck. Giving ace and 2.
        // when calcScore method is run
        // a score is returned. which will be 13
        player1.addCard(deck.dealCard());
        player1.addCard(deck.dealCard());
        player1.addCard(deck.dealCard());
        player1.addCard(deck.dealCard());
        player1.addCard(deck.dealCard());

        assertEquals(15, game.getScore(player1));
    }

    @Test
    public void canGetScoreMultipleAces(){
        // given a player object deal two cards from unshuffled deck. Giving ace and 2.
        // when calcScore method is run
        // a score is returned. which will be 13
        player1.addCard(aceHearts);
        player1.addCard(aceHearts);
        player1.addCard(aceHearts);

        assertEquals(13, game.getScore(player1));
    }

    @Test
    public void canGetWinnerPlayerWins(){
        // given a game with a player and dealer with player1 with winning hand.
        game.addPlayer(player1);
        Player dealer = game.getDealer();

        player1.addCard(aceHearts);
        player1.addCard(nineHearts);
        dealer.addCard(nineHearts);
        dealer.addCard(threeHearts);
        // when checkWin is run with player
        // true is return saying player wins.

        assertEquals(true, game.checkWin(player1));
    }

    @Test
    public void canGetWinnerDealerWins(){
        // given a game with a player and dealer with dealer with winning hand.
        game.addPlayer(player1);
        Player dealer = game.getDealer();


        dealer.addCard(aceHearts);
        dealer.addCard(nineHearts);
        player1.addCard(nineHearts);
        player1.addCard(threeHearts);
        // when checkWin is run with player
        // false is return saying player loses.

        assertEquals(false, game.checkWin(player1));
    }
    @Test
    public void canGetWinnerEqualHands21(){
        game.addPlayer(player1);
        Player dealer = game.getDealer();


        dealer.addCard(nineHearts);
        dealer.addCard(nineHearts);
        dealer.addCard(threeHearts);
        player1.addCard(nineHearts);
        player1.addCard(nineHearts);
        player1.addCard(threeHearts);

        assertEquals(false, game.checkWin(player1));
    }
    @Test
    public void canGetWinnerPlayerBlackJack(){
        game.addPlayer(player1);
        Player dealer = game.getDealer();

        dealer.addCard(nineHearts);
        dealer.addCard(nineHearts);
        dealer.addCard(threeHearts);
        player1.addCard(aceHearts);
        player1.addCard(kingHearts);

        assertEquals(true, game.checkWin(player1));
    }

    @Test
    public void canGetWinnerDealerBlackJack(){
        game.addPlayer(player1);
        Player dealer = game.getDealer();

        player1.addCard(nineHearts);
        player1.addCard(nineHearts);
        player1.addCard(threeHearts);
        dealer.addCard(aceHearts);
        dealer.addCard(kingHearts);

        assertEquals(false, game.checkWin(player1));
    }

    @Test
    public void canDisplayPlayers(){

        assertEquals("Players: 1=Dave 2=Dom 3=Darlene", gameWithPlayers.displayPlayers());
    }

    @Test
    public void canDealerPlayerAutomatically(){
        Player dealer = game.getDealer();
        dealer.addCard(nineHearts);
        dealer.addCard(threeHearts);
        game.dealerPlay(deck);

        assertEquals(18, game.getScore(dealer));
    }




}
