package com.example.jft_pr16.controllers;

import com.example.jft_pr16.entity.Level;
import com.example.jft_pr16.entity.dto.GameDTO;
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
    private final LevelService levelService;
    private final GameService gameService;
    @Autowired
    public LevelController(LevelService levelService, GameService gameService){ //!
        this.levelService = levelService;
        this.gameService = gameService;
    }

    @PostMapping(value = "/levels", consumes = {"application/json"})
    public ResponseEntity<?> createLevel(@RequestBody Level level){
        System.out.println("log: " + level);
        levelService.saveLevel(level);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/levels")
    public ResponseEntity<List<Level>> getLevel(){
        List<Level> levels = levelService.getLevels();
        if (levels != null && !levels.isEmpty()){
            return new ResponseEntity<>(levels, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/levels/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable(name = "id") Long id){
        Level level = levelService.getLevelById(id);
        if (level != null){
            return new ResponseEntity<>(level, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/levels/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateLevel(@PathVariable Long id, @RequestBody Level level){
        levelService.updateLevelById(level, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/levels/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id){
        levelService.deleteLevelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/levels/{id}/game")
    public ResponseEntity<GameDTO> getGameByPatientID(@PathVariable Long id){
        return new ResponseEntity<>(levelService.getGameByLevelId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/levels/levelName/{levelName}")
    public ResponseEntity<List<Level>> getLevelsByLevelName(@PathVariable String levelName){
        return new ResponseEntity<>(levelService.getLevelsByLevelName(levelName),
                HttpStatus.OK);
    }

    @GetMapping(value = "/levels/complexity/{complexity}")
    public ResponseEntity<List<Level>> getLevelsByComplexity(@PathVariable int complexity){
        return new ResponseEntity<>(levelService.getLevelsByComplexity(complexity),
                HttpStatus.OK);
    }

    @GetMapping(value = "/levels/game_id/{gameId}")
    public ResponseEntity<List<Level>> getLevelsByGameId(@PathVariable Long gameId){
        return new ResponseEntity<>(levelService.getLevelsByGameId(gameId),
                HttpStatus.OK);
    }
}