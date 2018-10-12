package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testValidPLacement() {
        Board board = new Board();
        assertTrue(board.placeShip(new Ship("BATTLESHIP"), 5, 'E', false));
    }

    @Test
    public void testOverlapPlacement(){
        Board board = new Board();
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 5, 'A', false));
        assertFalse(board.placeShip(new Ship("DESTROYER"), 5, 'A', false));
    }

    @Test
    public void testMultipleKind() {
        Board board = new Board();
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 1, 'A', false));
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 5, 'D', false));
    }

    @Test
    public  void  testInValidAttack() {
        Board board = new Board();
        Result result = new Result();
        Result result1 = new Result();
        board.placeShip(new Ship("MINESWEEPER"),7,'G',false);
        result = board.attack(4,'F');
        result1 = board.attack(4,'F');
     //   result = board.attack(2,'Z');
        assertTrue(result1.getResult() == AtackStatus.INVALID);
       // assertTrue(result.getResult() == AtackStatus.INVALID);
    }


    @Test
    public void testMissedAndHitAttacks() {
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("MINESWEEPER"),2,'A',false);
        result = board.attack(2,'C');
        assertTrue(result.getResult() == AtackStatus.MISS);
        result = board.attack(2,'A');
        assertTrue(result.getResult() == AtackStatus.HIT);
    }

    @Test
    public void testSunkAttack(){
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("MINESWEEPER"),4,'B',true);
        board.placeShip(new Ship("MINESWEEPER"),6,'D',true);
        result = board.attack(4,'B');
        assertTrue(result.getResult() == AtackStatus.HIT);
        result = board.attack(5,'B');
        assertTrue(result.getResult() == AtackStatus.SUNK);
    }

    @Test
    public void testSurrenderAttack(){
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("MINESWEEPER"),3,'H',true);
        result = board.attack(3,'H');
        result = board.attack(4,'H');
        assertTrue(result.getResult() == AtackStatus.SURRENDER);
    }
}


