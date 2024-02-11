package model;

public class Player {
    public String name;

    public PlayingPiece piece;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.piece = playingPiece;
    }
}
