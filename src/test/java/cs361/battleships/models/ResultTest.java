package cs361.battleships.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void testResult () {
        Board board = new Board();
        Result result1 = new Result();
        assertTrue(board.placeShip(new Ship("DESTROYER"), 5, 'A', false, false));
    }
}
