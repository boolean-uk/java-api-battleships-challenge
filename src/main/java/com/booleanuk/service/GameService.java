// GameService.java
package com.booleanuk.service;

import com.booleanuk.model.Game;
import com.booleanuk.model.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;

    public GameService() {
        this.game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        game.addPlayer(player);
    }

    public void resetGame() {
        this.game = new Game();
    }

    public String placeShip(int x, int y, int length, boolean horizontal) {
        boolean success = game.getBoard().placeShip(x, y, length, horizontal);
        return success ? "Ship placed successfully." : "Failed to place ship.";
    }

    public String attack(int x, int y) {
        boolean hit = game.getBoard().attack(x, y);
        String result = hit ? "Hit!" : "Miss!";
        game.switchTurn();
        return result;
    }
}