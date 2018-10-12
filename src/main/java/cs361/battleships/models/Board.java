package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private ArrayList<Square> board; // Saw this implementation of the array and decided to try it
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		ships = new ArrayList<Ship>(); // A list of what ships you've used
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		int k = 0;
		for(int i = 0; i < 10; i++) { // Changing char to int for column
			if(columns[i] == y)
				k = i;
		}

		Square tmp = new Square(); // Temp square to hold ship object
		int ship_size = ship.getShip_size(); // Get ship size for loops
		List<Square> shipBoard = new ArrayList<Square>();

		if(isVertical && (x + ship_size) <= 11) {  //boundary control
			for (int i = 0; i < ship_size; i++) { // Placing ship location
				tmp.setRow(x);
				tmp.setColumn(y);// Add location to board
				shipBoard.add(new Square(x+i, y)); // Adds ship location to the ship board

			}
			ship.setOccupiedSquares(shipBoard); // Sets occupied squares for the ship
            ships.add(ship); // Add new ship to list of ships
            return true;
		}
		else if(!isVertical && (k + ship_size) < 11) { // Boundary Control
			for (int i = 0; i < ship_size; i++) {
				tmp.setRow(x);
				tmp.setColumn(y);
				shipBoard.add(new Square(x,(char)(y+i)));
			}

			ship.setOccupiedSquares(shipBoard);
			ships.add(ship);
			return true;
		}
		return false;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		//TODO Implement
		return null;
	}

	public List<Ship> getShips() {
		//TODO implement
		return this.ships;
	}

	public void setShips(List<Ship> ships) {
	    this.ships = ships;
		//TODO implement
	}

	public List<Result> getAttacks() {
		//TODO implement
		return null;
	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
	}
}
