// GameController.java
package com.booleanuk.controller;

import com.booleanuk.model.Game;
import com.booleanuk.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public Game getGame() {
        return gameService.getGame();
    }

    @PostMapping("/join")
    public String joinGame(@RequestParam String playerName) {
        try {
            gameService.addPlayer(playerName);
            return "Player " + playerName + " joined the game.";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/reset")
    public String resetGame() {
        gameService.resetGame();
        return "Game has been reset.";
    }

    @PostMapping("/placeShip")
    public String placeShip(@RequestParam int x, @RequestParam int y, @RequestParam int length, @RequestParam boolean horizontal) {
        return gameService.placeShip(x, y, length, horizontal);
    }

    @PostMapping("/attack")
    public String attack(@RequestParam int x, @RequestParam int y) {
        try {
            return gameService.attack(x, y);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}