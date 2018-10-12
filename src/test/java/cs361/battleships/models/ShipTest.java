package cs361.battleships.models;

import org.junit.Assert;
import org.junit.Test;

public class ShipTest {

    @Test
    public void testShipKind() {
        Ship ship = new Ship("MINESWEEPER");
        Assert.assertEquals("MINESWEEPER", ship.getKind());
    }

    @Test
    public void testShipSize() {
        Ship ship = new Ship("DESTROYER");
        Assert.assertEquals(3, ship.getShip_size());
    }

    @Test
    public void testOccupiedSquares() {
        Ship ship = new Ship();
        Assert.assertNull(ship.getOccupiedSquares());
    }
}