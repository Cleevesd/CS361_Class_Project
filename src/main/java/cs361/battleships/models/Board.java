package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		// TODO Implement
		ArrayList<Square> board = new ArrayList<Square>();
		char [] columns = {'A','B','C','D','E','F','G','H','I','J'};
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				board.add(new Square(i,columns[j]));
			}
		}
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		// TODO Implement
		if(isVertical && (x-1) + ship.getOccupiedSquares().size() < 10) {  //boundary control
			int j = 0; //for updating the ship's row and column
			for (int i = x-1; i < (x-1) + ship.getOccupiedSquares().size(); i++) {
				ship.getOccupiedSquares().get(j).setRow(i);
				ship.getOccupiedSquares().get(j).setColumn(y);
				//update board?
				j++;
			}
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
		return null;
	}

	public void setShips(List<Ship> ships) {
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
