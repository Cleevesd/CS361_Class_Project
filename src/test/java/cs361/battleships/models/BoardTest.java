package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testValidPlacement() {
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
        assertTrue(result1.getResult() == AtackStatus.INVALID);

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
      //  board.placeShip(new Ship("BATTLESHIP"),2,'D',true);
        result = board.attack(4,'B');
        assertTrue(result.getResult() == AtackStatus.HIT);
        result = board.attack(5,'B');

        //this test is not working although our function outputs SUNK.
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

    @Test
    public void testSonarAttackIsValid(){
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("MINESWEEPER"),4,'D',true);
        result = board.sonarPulseAttack(4,'D');
        assertTrue(result.getResult() == AtackStatus.SONARATTACK);
    }

    @Test
    public void testSonarAttackOccupiedSquares() {
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("BATTLESHIP"),2,'D',true);
        result = board.sonarPulseAttack(4,'D');

        assertTrue(board.sonarPulseOccupiedSquares.get(0).getRow() == 3 );
        assertTrue(board.sonarPulseOccupiedSquares.get(0).getColumn() == 'D');
        assertTrue(board.sonarPulseOccupiedSquares.get(1).getRow() == 4 );
        assertTrue(board.sonarPulseOccupiedSquares.get(1).getColumn() == 'D' );
        assertTrue(board.sonarPulseOccupiedSquares.get(2).getRow() == 5 );
        assertTrue(board.sonarPulseOccupiedSquares.get(2).getColumn() == 'D' );
        assertTrue(board.sonarPulseOccupiedSquares.get(3).getRow() == 2 );
        assertTrue(board.sonarPulseOccupiedSquares.get(3).getColumn() == 'D');
    }

    @Test
    public void testSonarAttackEmptySquares(){
        Board board = new Board();
        Result result = new Result();
        board.placeShip(new Ship("BATTLESHIP"),2,'D',true);
        result = board.sonarPulseAttack(4,'D');

        assertTrue(board.sonarPulseEmptySquares.get(0).getRow() == 3);
        assertTrue(board.sonarPulseEmptySquares.get(0).getColumn() == 'C');
        assertTrue(board.sonarPulseEmptySquares.get(1).getRow() == 3);
        assertTrue(board.sonarPulseEmptySquares.get(1).getColumn() == 'E');
        assertTrue(board.sonarPulseEmptySquares.get(2).getRow() == 4);
        assertTrue(board.sonarPulseEmptySquares.get(2).getColumn() == 'C');
        assertTrue(board.sonarPulseEmptySquares.get(3).getRow() == 4);
        assertTrue(board.sonarPulseEmptySquares.get(3).getColumn() == 'E');
        assertTrue(board.sonarPulseEmptySquares.get(4).getRow() == 5);
        assertTrue(board.sonarPulseEmptySquares.get(4).getColumn() == 'C');
        assertTrue(board.sonarPulseEmptySquares.get(5).getRow() == 5);
        assertTrue(board.sonarPulseEmptySquares.get(5).getColumn() == 'E');
        assertTrue(board.sonarPulseEmptySquares.get(6).getRow() == 4);
        assertTrue(board.sonarPulseEmptySquares.get(6).getColumn() == 'B');
        assertTrue(board.sonarPulseEmptySquares.get(7).getRow() == 6);
        assertTrue(board.sonarPulseEmptySquares.get(7).getColumn() == 'D');
        assertTrue(board.sonarPulseEmptySquares.get(8).getRow() == 4);
        assertTrue(board.sonarPulseEmptySquares.get(8).getColumn() == 'F');


    }
}


