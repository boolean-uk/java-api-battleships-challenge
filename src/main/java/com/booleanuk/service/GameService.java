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

    public String placeShip(String playerName, int x, int y, int length, boolean horizontal) {
        Player player = game.getPlayers().stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Player not found"));

        boolean success = player.getOwnBoard().placeShip(x, y, length, horizontal);
        if (success) {
            player.incrementShipsPlaced();
            return "Ship placed successfully.";
        } else {
            return "Failed to place ship.";
        }
    }

    public String attack(String playerName, int x, int y) {
        if (!game.allShipsPlaced()) {
            return "All players must place 3 ships before starting the game.";
        }

        Player attacker = game.getPlayers().stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Player not found"));

        Player opponent = game.getPlayers().stream()
                .filter(p -> !p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Opponent not found"));

        boolean hit = opponent.getOwnBoard().attack(x, y);
        attacker.getAttackBoard().getGrid()[x][y] = hit ? 'H' : 'M';
        String result = hit ? "Hit!" : "Miss!";
        game.switchTurn();
        return result;
    }

    public Player getPlayerBoards(String playerName) {
        return game.getPlayers().stream()
                .filter(p -> p.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Player not found"));
    }
}