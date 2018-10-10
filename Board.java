package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
	private List<Ship> ships;
	private Square [][] board; // Saw this implementation of the array and decided to try it
    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		board = new Square[10][10];
		char col = 'A';
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				board[i][j] = new Square(i,col);
				col++;
			}
		}
		ships = new ArrayList<Ship>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		int k = 0; //for the column number of the placement
		for(int i = 0; i < 10; i++) {
			if(columns[i] == y)
				k = i;
		}
		x = x-1;

		List <Square> tmp = new ArrayList<Square>();
		int ship_size = ship.getShip_size();

		if(isVertical && (x + ship_size) < 9) {  //boundary control
			for (int i = x; i < (x + ship_size); i++) {
			    if (board[i][k].isFilled() == true) { // Error msg for if square if filled with a ship already
			        System.out.println("A ship is already placed here");
			        return false;
                }
                else {
                    tmp.add(board[i][k]); // Add location to board
                }
			}
            for(int i = 0; i < tmp.size(); i++) {
                tmp.get(i).setFilled(true); // Set filled to true
            }
            ship.setOccupiedSquares(tmp); // Set the occupied squares to the ship
            ships.add(ship); // Add new ship to list of ships
            return true;
		}
		else if(!isVertical && (k + ship_size) < 9) {
			for (int i = k; i < k+ship_size; i++) {
                if (board[x][i].isFilled() == true) {
                    System.out.println("A ship is already placed here");
                    return false;
                }
                else {
                    tmp.add(board[x][i]);
                    tmp.get(i).setFilled(true);
                    ship.setOccupiedSquares(tmp);
                    ships.add(ship);
                }
			}
			//for(int i = 0; i < tmp.size(); i++) {
			//    tmp.get(i).setFilled(true);
           // }

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
