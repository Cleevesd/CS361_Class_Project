package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static cs361.battleships.models.AtackStatus.*;

public class Game {
    @JsonProperty private Board playersBoard = new Board();
    @JsonProperty private Board opponentsBoard = new Board();

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean placeShip(Ship ship, int x, char y, boolean isVertical, boolean isSubmerged) {
        boolean successful = playersBoard.placeShip(ship, x, y, isVertical, isSubmerged);
        if (!successful)
            return false;

        Ship s2 = new Ship(ship.getKind());
        boolean opponentPlacedSuccessfully;
        do {
            // AI places random ships, so it might try and place overlapping ships
            // let it try until it gets it right
//            opponentPlacedSuccessfully = opponentsBoard.placeShip(s2, randRow(), randCol(), randVertical(), false);
            opponentPlacedSuccessfully = opponentsBoard.placeShip(s2, randRow(), randCol(), randVertical(), true);
        } while (!opponentPlacedSuccessfully);

        return true;
    }

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean attack(int x, char  y) {
        Result playerAttack = opponentsBoard.attack(x, y);
        if (playerAttack.getResult() == INVALID) {
            return false;
        }

        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() == INVALID);

        return true;
    }

    public boolean sonarPulseAttack(int x, char y) {
        Result playerSonarPulse = opponentsBoard.sonarPulseAttack(x, y);

        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() == INVALID);

        return true;
    }

    public boolean moveFleet(String direction) {
        Result playerFleetMovement = playersBoard.moveFleet(direction);
        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() == INVALID);
        return true;
    }

    private char randCol() {
        char [] chars = {'A','B','C','D','E','F','G','H','I','J'};
        int rand;
        rand = (int)(Math.random() * 10); // Returns random int [0,9]
        return chars[rand];
    }

    private int randRow() {
        int rand;
        rand = 1 + (int)(Math.random() * ((10 - 1) + 1)); // Returns random int [1,10]
        return rand;
    }

    private boolean randVertical() {
        boolean sender = Math.random() < 0.5; // returns random Bool
        return sender;
    }
}