import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player;
    String name;
    Card card;


    @Before
    public void before(){
        name = "Dave";
        player = new Player(name);
        card = new Card(SuitType.HEARTS, RankType.ACE);
    }

    @Test
    public void hasName(){
        assertEquals("Dave", player.getName());
    }

    @Test
    public void hasEmptyHand(){
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void canTakeCard(){
        player.addCard(card);
        assertEquals(1, player.getHand().size());
    }

    @Test
    public void canClearHand(){
        player.addCard(card);
        player.clearHand();
        assertEquals(0, player.getHand().size());
    }

}
