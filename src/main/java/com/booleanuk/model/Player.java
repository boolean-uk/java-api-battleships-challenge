// Player.java
package com.booleanuk.model;

public class Player {
    private String name;
    private Board ownBoard;
    private Board attackBoard;
    private int shipsPlaced;

    public Player(String name) {
        this.name = name;
        this.ownBoard = new Board();
        this.attackBoard = new Board();
        this.shipsPlaced = 0;
    }

    public String getName() {
        return name;
    }

    public Board getOwnBoard() {
        return ownBoard;
    }

    public Board getAttackBoard() {
        return attackBoard;
    }

    public int getShipsPlaced() {
        return shipsPlaced;
    }

    public void incrementShipsPlaced() {
        this.shipsPlaced++;
    }
}