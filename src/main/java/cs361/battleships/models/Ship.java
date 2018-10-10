package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	
	public Ship() {
        	occupiedSquares = new ArrayList<>();
   	}
	
	public Ship(String kind) {
		//TODO implement
		//adding squares for ships. The ships' boxes' row and column will be set while placing
		occupiedSquares = new ArrayList<>();
        if (kind.equals("Minesweeper")){
			for(int i = 0; i < 2; i++){
				occupiedSquares.add(new Square());
			}
        }
        else if (kind.equals("Destroyer")){
			for(int i = 0; i < 3; i++){
				occupiedSquares.add(new Square());
			}
        }
        else if (kind.equals("Battleship")){
			for(int i = 0; i < 4; i++){
				occupiedSquares.add(new Square());
			}
        }
	}

	public List<Square> getOccupiedSquares() {
		//TODO implement
		return occupiedSquares;
	}
}
