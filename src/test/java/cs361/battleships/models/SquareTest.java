package cs361.battleships.models;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {

    @Test
    public void testSquare(){
        Board board = new Board();
        Square square = new Square();
    }

    @Test
    public void testSetRowColumn() {
        Board board = new Board();
        Square square = new Square();
        square.setColumn('A');
        square.setRow(1);

        assertSame(square.getColumn(), 'A');
    }

}
