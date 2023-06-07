package com.example.jft_pr16.controllers;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.entity.Level;
import com.example.jft_pr16.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelController {
    private final TableService<Level> tableService;
    private final TableService<Game> gameTableService; //!
    @Autowired
    public LevelController(TableService<Level> tableService, TableService<Game> gameTableService){ //!
        this.tableService = tableService;
        this.gameTableService = gameTableService; //!
    }

    @PostMapping(value = "/levels", consumes = {"application/json"})
    public ResponseEntity<?> createLevel(@RequestBody Level level){
        System.out.println("log: " + level);
        tableService.create(level);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/levels")
    public ResponseEntity<List<Level>> getLevel(){
        List<Level> levels = tableService.readAll();
        if (levels != null && !levels.isEmpty()){
            return new ResponseEntity<>(levels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/levels/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable(name = "id") Long id){
        Level level = tableService.read(id);
        if (level != null){
            return new ResponseEntity<>(level, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/levels/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateLevel(@PathVariable Long id, @RequestBody Level level){
        boolean isUpdated = tableService.update(level, id);
        if (isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/levels/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id){
        boolean isDeleted = tableService.delete(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/levels/{id}/game")  //!
    public ResponseEntity<Game> getGameByLevelID(@PathVariable Long id){
        Level level = tableService.read(id);
        return new ResponseEntity<>(gameTableService.read(level.getGameId()),HttpStatus.OK);
    }
}