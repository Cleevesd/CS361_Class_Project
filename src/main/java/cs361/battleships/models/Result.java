package cs361.battleships.models;

public class Result {

	private Ship ship;
	private Square square;
	private AtackStatus status;

	public Result() {
	}

	public AtackStatus getResult() {
		//TODO implement
		return status;
	}

	public void setResult(AtackStatus result) {
		//TODO implement
		status = result;
	}

	public Ship getShip() {
		//TODO implement
		return this.ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
		//TODO implement
	}

	public Square getLocation() {
		//TODO implement
		return this.square;
	}

	public void setLocation(Square square) {
		//TODO implement
		this.square = square;
	}
}
