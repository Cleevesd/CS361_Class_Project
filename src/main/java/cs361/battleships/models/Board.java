package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private List<Square> shipBoard;
	private List<Result> previousAttacks;
	public List<Square> sonarPulseEmptySquares;
	public List<Square> sonarPulseOccupiedSquares;
	/*
    DO NOT change the signature of this method. It is used by the grading scripts.
     */
	public Board() {
		// TODO Implement
		ships = new ArrayList<Ship>(); // A list of what ships you've used
		shipBoard = new ArrayList<Square>();
		previousAttacks = new ArrayList<Result>();
		sonarPulseEmptySquares = new ArrayList<Square>();
		sonarPulseOccupiedSquares = new ArrayList<Square>();
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

	// Returns null if the given square is empty or has already been hit, otherwise it returns the square.
	public Square isOccupiedSquare(Square square) {
		for(int i = 0; i < ships.size(); i++) {
//			System.out.println(" ");
//			System.out.println("\tCurrent Ship Model: " + ships.get(i).getKind());
			for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
//				System.out.println("\ti: '"+i+"'");
//				System.out.println("\tj: '"+j+"'");
//				System.out.println("\t\tships.get(i).getOccupiedSquares().get(j).getRow(): '"+ships.get(i).getOccupiedSquares().get(j).getRow()+"'");
//				System.out.println("\t\tships.get(i).getOccupiedSquares().get(j).getColumn(): '"+ships.get(i).getOccupiedSquares().get(j).getColumn()+"'");
//				System.out.println("\t\tsquare.getRow(): '"+square.getRow()+"'");
//				System.out.println("\t\tsquare.getColumn(): '"+square.getColumn()+"'");
				if(ships.get(i).getOccupiedSquares().get(j).getRow() == square.getRow() && ships.get(i).getOccupiedSquares().get(j).getColumn() == square.getColumn()){
					return ships.get(i).getOccupiedSquares().get(j);
				}
			}
		}
		return null;
	}

	// Returns -1 if column not found, otherwise returns index from "columns."
	private int getIndexFromColumn(char column) {
		for (int i = 0; i < columns.length; i++) {
			if (columns[i] == (column)) {
				return i;
			}
		}
		return -1;
	}

	// Takes x or y as a value for the axis, and the index to be checked.
	private boolean isValidIndex(char axis, int index) {
		if (axis == 'x') {
			if (index >= 1 && index <=10) {
				return true;
			}
		} else if (axis == 'y') {
			if (index >= 0 && index <=9) {
				return true;
			}
		}

		return false;
	}

	public Result sonarPulseAttack(int x, char y) {
		Result result = new Result();
		result.setResult(AtackStatus.HIT);

		int yIndex = getIndexFromColumn(y);
		System.out.println("Attempting Sonar Pulse about ["+x+","+columns[yIndex]+"]...");
		for(int i = x-1; i <= x+1; i++){
			for(int j = yIndex-1; j <= yIndex+1; j++){
				if ( isValidIndex('x', i) && isValidIndex('y', j) ) {
					Square sonarTo = new Square(i, columns[j]);
					Square shipDetected = isOccupiedSquare(sonarTo);
					if(shipDetected == null) {
						// Add square to sonarPulseEmptySquares for game.js to use.
						System.out.println("["+i+","+columns[j]+"] is empty or has already been attacked.");
						sonarPulseEmptySquares.add( new Square(i, columns[j]) );
					} else {
						// Add square to sonarPulseOccupiedSquares for game.js to use.
						System.out.println("["+i+","+columns[j]+"] has a ship!");
						sonarPulseOccupiedSquares.add( new Square(i, columns[j]) );
					}
				}
			}
		}
		for (int i = -2; i <= 2; i=i+4) {
			if ( isValidIndex('x', x+i) ) {
				System.out.println("\txSquare: [" + (x + i) + "," + columns[yIndex] + "]");
				Square xSquare = new Square(x + i, columns[yIndex]);

				Square xDetected = isOccupiedSquare(xSquare);
				if (xDetected == null) {
					// Add square to sonarPulseEmptySquares for game.js to use.
					System.out.println("[" + (x + i) + "," + columns[yIndex] + "] is empty or has already been attacked.");
					sonarPulseEmptySquares.add(new Square(x + i, columns[yIndex]));
				} else {
					// Add square to sonarPulseOccupiedSquares for game.js to use.
					System.out.println("[" + (x + i) + "," + columns[yIndex] + "] has a ship!");
					sonarPulseOccupiedSquares.add(new Square(x + i, columns[yIndex]));
				}
			}
			if ( isValidIndex('y', yIndex+i) ) {
				System.out.println("\tySquare: ["+(x)+","+columns[yIndex+i]+"]");
				Square ySquare = new Square(x, columns[yIndex+i]);

				Square yDetected = isOccupiedSquare(ySquare);
				if(yDetected == null) {
					// Add square to sonarPulseEmptySquares for game.js to use.
					System.out.println("["+x+","+columns[yIndex+i]+"] is empty or has already been attacked.");
					sonarPulseEmptySquares.add( new Square(x, columns[yIndex+i]) );
				} else {
					// Add square to sonarPulseOccupiedSquares for game.js to use.
					System.out.println("["+x+","+columns[yIndex+i]+"] has a ship!");
					sonarPulseOccupiedSquares.add( new Square(x, columns[yIndex+i]) );
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
