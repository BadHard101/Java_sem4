package com.example.jft_pr16.controllers;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
public class GameController {
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping(value = "/games", consumes = {"application/json"})
    public ResponseEntity<?> createGame(@RequestBody Game game){
        System.out.println("log: " + game);
        gameService.saveGame(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getGame(){
        List<Game> games = gameService.getGames();
        if (games != null && !games.isEmpty()){
            return new ResponseEntity<>(games, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<Game> getGame(@PathVariable(name = "id") Long id){
        Game game = gameService.getGameById(id);
        if (game != null){
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/games/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game game){
        gameService.updateGameById(game, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id){
        gameService.deleteGameById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/games/gameName/{gameName}")
    public ResponseEntity<List<Game>> getGamesByGameName(@PathVariable String gameName){
        return new ResponseEntity<>(gameService.getGamesByGameName(gameName),
                HttpStatus.OK);
    }

    @GetMapping(value = "/games/creationDate/{creationDate}")
    public ResponseEntity<List<Game>> getGamesByCreationDate(@PathVariable String creationDate){
        return new ResponseEntity<>(gameService.getGamesByCreationDate(creationDate),
                HttpStatus.OK);
    }
}
