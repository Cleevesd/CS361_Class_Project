package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private List<Ship> shipsplace;
	private List<Square> shipBoard;
	private List<Square> capBoard;
	private List<Result> previousAttacks;
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		ships = new ArrayList<Ship>(); // A list of what ships you've used
		ships = new ArrayList<Ship>();
		shipBoard = new ArrayList<Square>();
		capBoard = new ArrayList<Square>();
		previousAttacks = new ArrayList<Result>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		for(int i = 0; i < ships.size(); i++) {
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
				if (i == ship_size-2) {
					capBoard.add(new Square(x+i, y));
				}

			}
			ship.setOccupiedSquares(shipBoard);
			ship.setCaptainQuarters(capBoard);
            ships.add(ship); // Add new ship to list of ships
			shipsplace = ships;

			for(int i = 0; i < ships.size(); i++) {
				//for (int j = 0; j < ships.get(i).getCaptainQuarters().size(); j++) {
					System.out.println("Captain Quarters");
					System.out.println(ships.get(i).getCaptainQuarters().get(0).getRow());
					System.out.println(ships.get(i).getCaptainQuarters().get(0).getColumn());
				//}
			}
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
				if (i == ship_size-2) {
					capBoard.add(new Square(x,(char)(y+i)));
				}
			}

			ship.setOccupiedSquares(shipBoard);
			ship.setCaptainQuarters(capBoard);
			ships.add(ship);
			shipsplace = ships;
			//for(int i = 0; i < ships.size(); i++) {
						//System.out.println("Captain Quarters");
						//System.out.println(ships.get(i).getCaptainQuarters().get(0).getRow());
						//System.out.println(ships.get(i).getCaptainQuarters().get(0).getColumn());
			//}

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
					// Check if attack is on the Captains Quarters
					if(ships.get(i).getCaptainQuarters().get(0).getRow() == attackedTo.getRow() && ships.get(i).getCaptainQuarters().get(0).getColumn() == attackedTo.getColumn()) {
						if(ships.get(i).getKind().equals("MINESWEEPER")) { // if Ship is minesweeper sink
							status = AtackStatus.SUNK;
							result.setResult(status);
							result.setLocation(attackedTo);
							previousAttacks.add(result);
							ships.remove(ships.get(i));
						}
						else { // If ship is Destroyer or Battleship & health is 0 sink
							if(ships.get(i).getHealth() == 0) {
								status = AtackStatus.SUNK;
								result.setResult(status);
								result.setLocation(attackedTo);
								previousAttacks.add(result);
								ships.remove(ships.get(i));
							}
							else { // If ship is Destroyer or Battleship reduce health to 0
								status = AtackStatus.MISS;
								result.setResult(status);
								ships.get(i).setHealth(0);
							}

						}
						System.out.println("Hit Caps Quarters");
						if(ships.size() == 0){ //
							status = AtackStatus.SURRENDER;
							result.setResult(status);
							return result;
						}
						return result;
					}
					else {
						System.out.println(ships.get(i).getHealth());
						status = AtackStatus.HIT;
						result.setResult(status);
						result.setLocation(attackedTo);
						previousAttacks.add(result);
						ships.get(i).getOccupiedSquares().remove(ships.get(i).getOccupiedSquares().get(j));
					}
					// if it is a hit remove the occupied square

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
						return result;
					}
				return result;
				}

			}
		}
		status = AtackStatus.MISS;
		result.setResult(status);
		result.setLocation(attackedTo);
		previousAttacks.add(result);
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
