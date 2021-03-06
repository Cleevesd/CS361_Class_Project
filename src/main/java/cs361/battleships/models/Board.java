package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private List<Square> shipBoard;
	private List<Square> shipBoardSub;
	private List<Square> capBoard;
	private List<Square> capBoardSub;
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
		shipBoardSub = new ArrayList<Square>();
		capBoard = new ArrayList<Square>();
		capBoardSub = new ArrayList<Square>();
		previousAttacks = new ArrayList<Result>();
		sonarPulseEmptySquares = new ArrayList<Square>();
		sonarPulseOccupiedSquares = new ArrayList<Square>();
	}


	public boolean placeBoundaryControl(int ship_size, int x, char y, boolean isVertical) {
		for (int i = 0; i < ships.size(); i++) {
			for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
				for (int l = 0; l < ship_size; l++) {
					if(isVertical) {
						if (ships.get(i).getOccupiedSquares().get(j).getRow() == x + l && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						} else if (ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						}
					}
					else {
						if (ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y + l) {
							return false;
						} else if (ships.get(i).getOccupiedSquares().get(j).getRow() == x && ships.get(i).getOccupiedSquares().get(j).getColumn() == y) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public void addShips(int ship_size, int x, char y, Ship ship, boolean isVertical) {
		for (int i = 0; i < ship_size; i++) { // Placing ship location
			if (isVertical) {
				if (i < 4) {        // All ship squares up until the notch in the sub
					shipBoard.add(new Square(x + i, y)); // Adds ship location to the ship board
				} else {
					shipBoard.add(new Square(x + 1, (char) (y - 1))); // Sets the notch in the sub
				}
				if (i == ship_size - 2) {
					if (ship_size < 5) {
						capBoard.add(new Square(x + i, y));
					} else {
						capBoard.add(new Square(x, y));
					}
				}
			} else {
				if (i < 4) {    // All ship squares leading up to the sub
					shipBoard.add(new Square(x, (char) (y + i)));
				} else {
					shipBoard.add(new Square(x - 1, (char) (y + 2)));    // Sets the out-of-ordinary ship square
				}

				if (i == ship_size - 2) {
					capBoard.add(new Square(x, (char) (y + i)));
				}
			}

		}
		ship.setOccupiedSquares(shipBoard);
		ship.setCaptainQuarters(capBoard);
		ships.add(ship); // Add new ship to list of ships
	}

	public void addSub(int ship_size, int x, char y, Ship ship, boolean isVertical) {

			for (int i = 0; i < ship_size; i++) { // Placing ship location
					if (isVertical) {
						if (i < 4) {        // All ship squares up until the notch in the sub
							shipBoardSub.add(new Square(x + i, y)); // Adds ship location to the ship board
						} else {
							shipBoardSub.add(new Square(x + 1, (char) (y - 1))); // Sets the notch in the sub
						}
						if (i == ship_size - 2) {
							if (ship_size < 5) {
								capBoardSub.add(new Square(x + i, y));
							} else {
								capBoardSub.add(new Square(x, y));
							}
						}
					}
					else {
						if (i < 4) {    // All ship squares leading up to the sub
							shipBoardSub.add(new Square(x, (char) (y + i)));
						} else {
							shipBoardSub.add(new Square(x - 1, (char) (y + 2)));    // Sets the out-of-ordinary ship square
						}

						if (i == ship_size - 2) {
							capBoardSub.add(new Square(x, (char) (y + i)));
						}
					}

			}
		ship.setSubSquares(shipBoardSub);
		ship.setCapQuarters(capBoardSub);
		ships.add(ship); // Add new ship to list of ships
	}


	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical, boolean isSubmerged) {
		//System.out.println(isSubmerged);
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
		boolean boundary;
		if(ship_size == 5){
			if(!isVertical && (x-1) < 1){
				return false;
			}
			if(isVertical && ((y - 1) < 'A')){
				return false;
			}
			// THIS BLOCK OF CODE IS HACKY --- MAYBE REFACTOR IF THERE IS TIME
			if(isVertical && (x + ship_size) <= 12) {  //boundary control  WE HAVE A PROBLEM HERE WITH SUB SIZE=5
				boundary = placeBoundaryControl(ship_size, x, y, true);
				if(!boundary) {
					return false;
				}
				if(ship_size == 5 && isSubmerged) {
					addSub(ship_size,x ,y, ship, true);
				}
				else {
					addShips(ship_size, x, y, ship, true);
				}
				return true;
			}
			else if(!isVertical && (k + ship_size) < 12) { // Boundary Control
				boundary = placeBoundaryControl(ship_size, x, y, false);
				if(!boundary) {
					return false;
				}
				if(ship_size == 5 && isSubmerged) {
					addSub(ship_size,x ,y, ship, false);
				}
				else {
					addShips(ship_size, x, y, ship, false);
				}
				return true;
			}
		}
		if(isVertical && (x + ship_size) <= 11) {  //boundary control
			boundary = placeBoundaryControl(ship_size, x, y, true);
			if(!boundary) {
				return false;
			}
			addShips(ship_size, x, y, ship, true);
			return true;
		}
		else if(!isVertical && (k + ship_size) < 11) { // Boundary Control
			boundary = placeBoundaryControl(ship_size, x, y, false);
			if(!boundary) {
				return false;
			}
			addShips(ship_size, x, y, ship, false);
			return true;
		}
		return false;
	}

	public void hitCap(Result result, Square attackedTo,int i) {
	    AtackStatus status;
        if (ships.get(i).getKind().equals("MINESWEEPER")) { // if Ship is minesweeper sink
            status = AtackStatus.SUNK;
            result.setResult(status);
            result.setLocation(attackedTo);
            previousAttacks.add(result);
            ships.remove(ships.get(i));
        }
        else { // If ship is Destroyer or Battleship & health is 0 sink
            if (ships.get(i).getHealth() == 0) {
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
        if (ships.size() == 0) { //
            status = AtackStatus.SURRENDER;
            result.setResult(status);
        }
    }


	public void sunkShip(Result result, int i) {
	    AtackStatus status;
	    status = AtackStatus.SUNK;
	    result.setResult(status);
	    // if the all occupied squares are removed, remove the ship
        ships.remove(ships.get(i));
        if (ships.size() == 0) {
            status = AtackStatus.SURRENDER;
            result.setResult(status);
        }
	}

	public void normalAttack(Result result, int i, int j, Square attackedTo, int index) {
        AtackStatus status;
	    if(index == 0) {
            status = AtackStatus.HIT;
            result.setResult(status);
            result.setLocation(attackedTo);
            previousAttacks.add(result);
            ships.get(i).getOccupiedSquares().remove(ships.get(i).getOccupiedSquares().get(j));
        }
        else {
            status = AtackStatus.MISS;
            result.setResult(status);
            result.setLocation(attackedTo);
            previousAttacks.add(result);
        }
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
            for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                if (ships.get(i).getOccupiedSquares().get(j).getRow() == attackedTo.getRow() && ships.get(i).getOccupiedSquares().get(j).getColumn() == attackedTo.getColumn()) {
                    if (ships.get(i).getCaptainQuarters().get(0).getRow() == attackedTo.getRow() && ships.get(i).getCaptainQuarters().get(0).getColumn() == attackedTo.getColumn()) {
                        hitCap(result, attackedTo, i);
                        return result;
                    } else {
                        normalAttack(result, i, j, attackedTo, 0);
                    }
                    if (ships.get(i).getOccupiedSquares().size() == 0) {
                        sunkShip(result, i);
                        return result;
                    }
                    return result;
                }
            }
        }
		normalAttack(result, 0, 0, attackedTo, 1);
		return result;
	}

	// Returns null if the given square is empty or has already been hit, otherwise it returns the square.
	public Square isOccupiedSquare(Square square) {
		for(int i = 0; i < ships.size(); i++) {
			for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
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

		int yIndex = getIndexFromColumn(y);
		for(int i = x-1; i <= x+1; i++){
			for(int j = yIndex-1; j <= yIndex+1; j++){
				if ( isValidIndex('x', i) && isValidIndex('y', j) ) {
					Square sonarTo = new Square(i, columns[j]);
					Square shipDetected = isOccupiedSquare(sonarTo);
					if(shipDetected == null) {
						// Add square to sonarPulseEmptySquares for game.js to use.
						sonarPulseEmptySquares.add( new Square(i, columns[j]) );
					} else {
						// Add square to sonarPulseOccupiedSquares for game.js to use.
						sonarPulseOccupiedSquares.add( new Square(i, columns[j]) );
					}
				}
			}
		}
		for (int i = -2; i <= 2; i=i+4) {
			if ( isValidIndex('x', x+i) ) {
				Square xSquare = new Square(x + i, columns[yIndex]);
				Square xDetected = isOccupiedSquare(xSquare);
				if (xDetected == null) {
					// Add square to sonarPulseEmptySquares for game.js to use.
					sonarPulseEmptySquares.add(new Square(x + i, columns[yIndex]));
				} else {
					// Add square to sonarPulseOccupiedSquares for game.js to use.
					sonarPulseOccupiedSquares.add(new Square(x + i, columns[yIndex]));
				}
			}
			if ( isValidIndex('y', yIndex+i) ) {
				Square ySquare = new Square(x, columns[yIndex+i]);
				Square yDetected = isOccupiedSquare(ySquare);
				if(yDetected == null) {
					// Add square to sonarPulseEmptySquares for game.js to use.
					sonarPulseEmptySquares.add( new Square(x, columns[yIndex+i]) );
				} else {
					// Add square to sonarPulseOccupiedSquares for game.js to use.
					sonarPulseOccupiedSquares.add( new Square(x, columns[yIndex+i]) );
				}
			}
		}
		result.setResult(AtackStatus.SONARATTACK);
		return result;
	}

    public Result moveFleet(String x){
        Result result = new Result();
        AtackStatus status;
        if(x.equals("NORTH")){
            for (int i = 0; i < ships.size(); i++) {
                boolean canMove = true;
                for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                    if(ships.get(i).getOccupiedSquares().get(j).getRow() < 2) {
                        canMove = false;
                    }
                }
                if (canMove) {
                    for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                        int a = ships.get(i).getOccupiedSquares().get(j).getRow();
                        ships.get(i).getOccupiedSquares().get(j).setRow(a - 1);
                    }
                }
            }
        }
        else if(x.equals("SOUTH")){
            //boundary check
            for (int i = 0; i < ships.size(); i++) {
                boolean canMove = true;
                for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                    if(ships.get(i).getOccupiedSquares().get(j).getRow() > 9) {
                        canMove = false;
                    }
                }
                if (canMove) {
                    for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                        int a = ships.get(i).getOccupiedSquares().get(j).getRow();
                        ships.get(i).getOccupiedSquares().get(j).setRow(a + 1);
                    }
                }
            }
            status = AtackStatus.MOVEFLEET;
            result.setResult(status);
        }
        else if(x.equals("EAST")){
            //boundary check
            for (int i = 0; i < ships.size(); i++) {
                boolean canMove = true;
                for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                    if(ships.get(i).getOccupiedSquares().get(j).getColumn() > 'I') {
                        canMove = false;
                    }
                }
                if (canMove) {
                    for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                        char a = ships.get(i).getOccupiedSquares().get(j).getColumn();
                        char newColumn = 'A';
                        for (int k = 0; k < columns.length; k++) {
                            if (a == columns[k])
                                newColumn = columns[k + 1];
                        }
                        ships.get(i).getOccupiedSquares().get(j).setColumn(newColumn);
                    }
                }
            }
            status = AtackStatus.MOVEFLEET;
            result.setResult(status);
        }
        else if(x.equals("WEST")){
            //boundary check
            for (int i = 0; i < ships.size(); i++) {
                boolean canMove = true;
                for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                    if (ships.get(i).getOccupiedSquares().get(j).getColumn() < 'B') {
                        canMove = false;
                    }
                }
                if (canMove) {
                    for (int j = 0; j < ships.get(i).getOccupiedSquares().size(); j++) {
                        char a = ships.get(i).getOccupiedSquares().get(j).getColumn();
                        char newColumn = 'A';
                        for (int k = 0; k < columns.length; k++) {
                            if (a == columns[k])
                                newColumn = columns[k - 1];
                        }
                        ships.get(i).getOccupiedSquares().get(j).setColumn(newColumn);
                    }
                }
            }
            status = AtackStatus.MOVEFLEET;
            result.setResult(status);
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
