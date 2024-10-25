// Game.java
package com.booleanuk.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private boolean isGameOver;
    private int currentPlayerIndex;

    public Game() {
        this.players = new ArrayList<>();
        this.isGameOver = false;
        this.currentPlayerIndex = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
        } else {
            throw new IllegalStateException("Game already has two players.");
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void switchTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean allShipsPlaced() {
        return players.stream().allMatch(player -> player.getShipsPlaced() >= 3);
    }
}