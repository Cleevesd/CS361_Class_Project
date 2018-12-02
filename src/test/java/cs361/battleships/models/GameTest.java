package cs361.battleships.models;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertSame;

public class GameTest {

    @Test
    public void testPlaceShip () {
        Game game = new Game();
        Board board = new Board();
        assertTrue(game.placeShip(new Ship("Minesweeper"), 1, 'A', false, false));
    }

    @Test
    public void testAttack () {
        Game game = new Game();
        Board board = new Board();
        assertTrue(game.placeShip(new Ship("Minesweeper"), 1, 'A', false, false));

        Result result1 = board.attack(1, 'B');
        assertTrue(game.attack(1, 'B'));
    }

    @Test
    public void testSonar () {
        Game game = new Game();
        Board board = new Board();
        assertTrue(game.sonarPulseAttack(1, 'A'));
    }

}
