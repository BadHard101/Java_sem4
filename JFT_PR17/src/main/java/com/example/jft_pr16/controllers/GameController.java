package com.example.jft_pr16.controllers;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.service.GameService;
import com.example.jft_pr16.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    private final GameService tableService;
    @Autowired
    public GameController(GameService tableService){
        this.tableService = tableService;
    }

    @PostMapping(value = "/games", consumes = {"application/json"})
    public ResponseEntity<?> createGame(@RequestBody Game game){
        System.out.println("log: " + game);
        tableService.create(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/games")
    public ResponseEntity<List<Game>> getGame(){
        List<Game> games = tableService.readAll();
        if (games != null && !games.isEmpty()){
            return new ResponseEntity<>(games, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/games/{id}")
    public ResponseEntity<Game> getGame(@PathVariable(name = "id") Long id){
        Game game = tableService.read(id);
        if (game != null){
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/games/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game game){
        boolean isUpdated = tableService.update(game, id);
        if (isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id){
        boolean isDeleted = tableService.delete(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/games/game_name/{game_name}")
    public ResponseEntity<List<Game>> getGamesByGameName(@PathVariable String game_name){
        return new ResponseEntity<>(tableService.getGamesFilteredBy("game_name", game_name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/games/creation_date/{creation_date}")
    public ResponseEntity<List<Game>> getGamesByCreationDate(@PathVariable String creation_date){
        return new ResponseEntity<>(tableService.getGamesFilteredBy("creation_date", creation_date),
                HttpStatus.OK);
    }
}
