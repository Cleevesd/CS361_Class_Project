package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.loader.custom.sql.SQLQueryReturnProcessor;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	@JsonProperty private List<Square> captainQuarters;
	@JsonProperty private List<Square> subSquares;
	@JsonProperty private List<Square> capQuarters;

	private String kind;
	private int ship_size;
	private int health;
	
	public Ship() {
        	occupiedSquares = null;
   	}
	
	public Ship(String kind) {
		//TODO implement
		//adding squares for ships. The ships' boxes' row and column will be set while placing
		//occupiedSquares = new ArrayList<>();
		this.kind = kind;
		if (kind.equals("MINESWEEPER")) {
			this.ship_size = 2;
			this.health = 0;
		} else if (kind.equals("DESTROYER")) {
			this.ship_size = 3;
			this.health = 1;
		} else if (kind.equals("BATTLESHIP")) {
			this.ship_size = 4;
			this.health = 1;
		} else if (kind.equals("SUBMARINE")) {
			this.ship_size = 5;
			this.health = 1;
		}
		occupiedSquares = new ArrayList<>();
		captainQuarters = new ArrayList<>();
	}

	public void setShip_size(int ship_size) {
		this.ship_size = ship_size;
	}

	public int getShip_size() {
		return ship_size;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {

		return health;
	}

	public void setOccupiedSquares(List<Square> occupiedSquares) {

		this.occupiedSquares = occupiedSquares;
	}

	public List<Square> getOccupiedSquares() {
		//TODO implement
		return occupiedSquares;
	}
	public void setSubSquares(List<Square> subSquares) {

		this.subSquares = subSquares;
	}

	public List<Square> getSubSquares() {
		//TODO implement
		return subSquares;
	}

	public void setCapQuarters(List<Square> capQuarters) {
		this.capQuarters = capQuarters;
	}

	public List<Square> getCapQuarters() {
		return capQuarters;
	}

	public void setCaptainQuarters(List<Square> captainQuarters) {
		this.captainQuarters = captainQuarters;
	}

	public List<Square> getCaptainQuarters() {
		return captainQuarters;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}

}
