package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	private String kind;
	private int ship_size;
	
	public Ship() {
        	occupiedSquares = null;
   	}
	
	public Ship(String kind) {
		//TODO implement
		//adding squares for ships. The ships' boxes' row and column will be set while placing
		//occupiedSquares = new ArrayList<>();
		this.kind = kind;
		if (kind.equals("Minesweeper")) {
			this.ship_size = 2;
		} else if (kind.equals("Destroyer")) {
			this.ship_size = 3;
		} else if (kind.equals("Battleship")) {
			this.ship_size = 4;
		}
		occupiedSquares = new ArrayList<>();
	}

	public void setShip_size(int ship_size) {
		this.ship_size = ship_size;
	}

	public int getShip_size() {
		return ship_size;
	}

	public void setOccupiedSquares(List<Square> occupiedSquares) {
		this.occupiedSquares = occupiedSquares;
	}

	public List<Square> getOccupiedSquares() {
		//TODO implement
		return occupiedSquares;
	}


}
