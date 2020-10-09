package model;

public class Move {
	String move;
	String type = "move";

    public Move (String move) {
        this.move = move;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
