package com.example.jft_pr15.controllers;

import com.example.jft_pr15.entity.Game;
import com.example.jft_pr15.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    private final TableService<Game> tableService;
    @Autowired
    public GameController(TableService<Game> tableService){
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
}
