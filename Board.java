package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private List<Square> shipBoard;
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		ships = new ArrayList<Ship>(); // A list of what ships you've used
		shipBoard = new ArrayList<Square>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		for(int i = 0; i < ships.size(); i++) {
			System.out.println(ships.get(i).getKind());
			if(ships.get(i).getKind().equals(ship.getKind())) { // Checks ships ArrayList and compares to current ship, if any are equal return false
				return false;
			}
		}

		int k = 0;
		for(int i = 0; i < 10; i++) { // Changing char to int for column
			if(columns[i] == y)
				k = i;
		}


		int ship_size = ship.getShip_size(); // Get ship size for loops

		if(isVertical && (x + ship_size) <= 11) {  //boundary control


			for(int i = 0; i < ships.size(); i++) {
				for(int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
					for(int l = 0; l < ship_size; l++) {
						if(ships.get(i).getOccupiedSquares().get(j).getRow() == x+l && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						}
						else if(ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						}
					}

				}
			}

			for (int i = 0; i < ship_size; i++) { // Placing ship location
				shipBoard.add(new Square(x+i, y)); // Adds ship location to the ship board

			}
			ship.setOccupiedSquares(shipBoard);
            ships.add(ship); // Add new ship to list of ships
			System.out.println(ship.getOccupiedSquares());



            return true;
		}
		else if(!isVertical && (k + ship_size) < 11) { // Boundary Control
			for(int i = 0; i < ships.size(); i++) {
				for(int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
					for(int l = 0; l < ship_size; l++) {
						if(ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y+l) {
							return false;
						}
						else if(ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						}
					}

				}
			}
			for (int i = 0; i < ship_size; i++) {
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
