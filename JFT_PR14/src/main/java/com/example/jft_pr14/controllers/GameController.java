package com.example.jft_pr14.controllers;

import com.example.jft_pr14.entity.Game;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    ArrayList<Game> games = new ArrayList<>();

    @GetMapping("/game")
    public List<Game> getAllGames(){
        return games;
    }

    @PostMapping("/game")
    public Game addManufacture(@RequestParam String name, @RequestParam String creationDate) {
        Game manufacture = new Game();
        manufacture.setName(name);
        manufacture.setCreationDate(creationDate);
        games.add(manufacture);
        return manufacture;
    }

    @DeleteMapping("/game")
    public String deletePhone(@RequestParam String name, @RequestParam String creationDate) {
        Game gameToDelete = null;
        for (Game game : games) {
            if (game.getName().equals(name) && game.getCreationDate().equals(creationDate)) {
                gameToDelete = game;
                break;
            }
        }
        if (gameToDelete != null) {
            games.remove(gameToDelete);
            return gameToDelete.toString() + " was deleted";
        } else {
            return "Game not found";
        }
    }
}

