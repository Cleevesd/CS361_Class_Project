package cs361.battleships.models;

import org.junit.Test;
import static org.junit.Assert.assertFalse;

public class GameTest {

    @Test
    public void testRandNum() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }
}