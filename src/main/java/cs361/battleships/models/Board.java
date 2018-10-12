package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private List<Square> shipBoard;
	private List<Result> previousAttacks;
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		ships = new ArrayList<Ship>(); // A list of what ships you've used
		shipBoard = new ArrayList<Square>();
		previousAttacks = new ArrayList<Result>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		for(int i = 0; i < ships.size(); i++) {
			System.out.println(ships.get(i).getKind());
			if(ships.get(i).getKind().equals(ship.getKind())) { // Checks ships ArrayList and compares to current ship, if any are equal return false
				System.out.println("Already used that ship type try again");
				return false;
			}
		}
		int k = 0;
		for(int i = 0; i < 10; i++) { // Changing char to int for column
			if(columns[i] == y)
				k = i;
		}


		int ship_size = ship.getShip_size(); // Get ship size for loops
		//List<Square> shipBoard = new ArrayList<Square>();

		if(isVertical && (x + ship_size) <= 11) {  //boundary control
			for (int i = 0; i < ship_size; i++) { // Placing ship location
				shipBoard.add(new Square(x+i, y)); // Adds ship location to the ship board

			}
			ship.setOccupiedSquares(shipBoard);
            ships.add(ship); // Add new ship to list of ships
			System.out.println(ship.getOccupiedSquares());



            return true;
		}
		else if(!isVertical && (k + ship_size) < 11) { // Boundary Control
			for (int i = 0; i < ship_size; i++) {
				shipBoard.add(new Square(x,(char)(y+i)));
			}

			ship.setOccupiedSquares(shipBoard);
			ships.add(ship);
			System.out.println(ship.getOccupiedSquares());


			return true;
		}
		return false;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		//TODO Implement
		Square attackedTo = new Square(x,y);
		Result result = new Result();
		AtackStatus status;

		if( x < 1 || x > 10 || y < 'A' || y > 'J') { // bounds check
			status = AtackStatus.INVALID;
			result.setResult(status);
			return result;
		}

		// check for if it is shot before
		for(int i = 0; i < previousAttacks.size(); i++) {
			if(previousAttacks.get(i).getLocation().getRow() == attackedTo.getRow() && previousAttacks.get(i).getLocation().getColumn() == attackedTo.getColumn()){
				status = AtackStatus.INVALID;
				result.setResult(status);
				return result;
			}
		}

		for(int i = 0; i < ships.size(); i++) {
			for(int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
				if(ships.get(i).getOccupiedSquares().get(j).getRow() == attackedTo.getRow() && ships.get(i).getOccupiedSquares().get(j).getColumn() == attackedTo.getColumn()) {
					status = AtackStatus.HIT;
					result.setResult(status);
					result.setLocation(attackedTo);
					previousAttacks.add(result);
					// if it is a hit remove the occupied square
					ships.get(i).getOccupiedSquares().remove(ships.get(i).getOccupiedSquares().get(j));
					if(ships.get(i).getOccupiedSquares().size() == 0) {
						status = AtackStatus.SUNK;
						result.setResult(status);
						// if the all occupied squares are removed, remove the ship
						ships.remove(ships.get(i));
						if(ships.size() == 0){
							status = AtackStatus.SURRENDER;
							result.setResult(status);
							return result;
						}
					}
					return result;
				}

			}
		}


		return result;

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
		return previousAttacks;

	}

	public void setAttacks(List<Result> attacks) {
		//TODO implement
		previousAttacks = attacks;
	}
}
