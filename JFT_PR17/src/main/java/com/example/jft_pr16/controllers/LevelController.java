package com.example.jft_pr16.controllers;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.entity.Level;
import com.example.jft_pr16.service.GameService;
import com.example.jft_pr16.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
public class LevelController {
    private final LevelService levelTableService;
    private final GameService gameTableService; //!
    @Autowired
    public LevelController(LevelService levelTableService, GameService gameTableService){ //!
        this.levelTableService = levelTableService;
        this.gameTableService = gameTableService; //!
    }

    @PostMapping(value = "/levels", consumes = {"application/json"})
    public ResponseEntity<?> createLevel(@RequestBody Level level){
        System.out.println("log: " + level);
        levelTableService.create(level);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/levels")
    public ResponseEntity<List<Level>> getLevel(){
        List<Level> levels = levelTableService.readAll();
        if (levels != null && !levels.isEmpty()){
            return new ResponseEntity<>(levels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/levels/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable(name = "id") Long id){
        Level level = levelTableService.read(id);
        if (level != null){
            return new ResponseEntity<>(level, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/levels/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateLevel(@PathVariable Long id, @RequestBody Level level){
        boolean isUpdated = levelTableService.update(level, id);
        if (isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/levels/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id){
        boolean isDeleted = levelTableService.delete(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/levels/{id}/game")
    public ResponseEntity<Game> getGameByLevelID(@PathVariable Long id){
        Level level = levelTableService.read(id);
        return new ResponseEntity<>(gameTableService.read(level.getGame_id()),HttpStatus.OK);
    }

    @GetMapping(value = "/levels/level_name/{level_name}")
    public ResponseEntity<List<Level>> getLevelsByLevelName(@PathVariable String level_name){
        return new ResponseEntity<>(levelTableService.getLevelsFilteredBy("level_name", level_name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/levels/complexity/{complexity}")
    public ResponseEntity<List<Level>> getLevelsByComplexity(@PathVariable String complexity){
        return new ResponseEntity<>(levelTableService.getLevelsFilteredBy("complexity", complexity),
                HttpStatus.OK);
    }

    @GetMapping(value = "/levels/game_id/{game_id}")
    public ResponseEntity<List<Level>> getLevelsByGameId(@PathVariable String game_id){
        return new ResponseEntity<>(levelTableService.getLevelsFilteredBy("game_id", game_id),
                HttpStatus.OK);
    }
}