package com.example.jft_pr14.controllers;

import com.example.jft_pr14.entity.Level;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LevelController {
    ArrayList<Level> levels = new ArrayList<>();

    @GetMapping("/level")
    public List<Level> getLevels(){
        return levels;
    }

    @PostMapping("/level")
    public Level addLevel(@RequestParam String levelName, @RequestParam int complexity) {
        Level level = new Level();
        level.setLevelName(levelName);
        level.setComplexity(complexity);
        levels.add(level);
        return level;
    }

    @DeleteMapping("/level")
    public String deleteLevel(@RequestParam String levelName, @RequestParam int complexity) {
        Level levelToDelete = null;
        for (Level level : levels) {
            if (level.getLevelName().equals(levelName) && level.getComplexity() == complexity) {
                levelToDelete = level;
                break;
            }
        }
        if (levelToDelete != null) {
            levels.remove(levelToDelete);
            return levelToDelete.toString() + " was deleted";
        } else {
            return "Level not found";
        }
    }
}
