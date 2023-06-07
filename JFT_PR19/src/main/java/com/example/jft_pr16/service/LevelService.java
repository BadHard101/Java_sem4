package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Level;
import com.example.jft_pr16.entity.dto.GameDTO;
import com.example.jft_pr16.entity.dto.LevelDTO;
import com.example.jft_pr16.repository.GameRepository;
import com.example.jft_pr16.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LevelService {
    @Autowired
    private final LevelRepository levelRepository;

    @Autowired
    private final GameRepository gameRepository;

    public List<Level> getLevels() {
        log.info("Get all levels");
        return levelRepository.findAll();
    }

    public Level getLevelById(Long id) {
        log.info("Get level by id {}", id);
        return levelRepository.getReferenceById(id);
    }

    public void saveLevel(Level level) {
        log.info("Save level {}", level);
        levelRepository.save(level);
    }

    public void deleteLevelById(Long id) {
        log.info("Delete level by id {}", id);
        levelRepository.deleteById(id);
    }

    public void updateLevelById(Level level, Long id) {
        log.info("Update level {} by id {}", level, id);
        level.setId(id);
        levelRepository.save(level);
    }

    public GameDTO getGameByLevelId(Long levelId) {
        log.info("Get game by level id {}", levelId);
        LevelDTO levelDTO = levelRepository.getById(levelId).makeDTO();
        GameDTO gameDTO = gameRepository.getById(levelDTO.getGameId()).makeDTO();
        return gameDTO;
    }

    public List<Level> getLevelsByLevelName(String levelName) {
        log.info("Get levels by levelName - {}", levelName);
        return levelRepository.findAllByLevelName(levelName);
    }

    public List<Level> getLevelsByComplexity(int complexity) {
        log.info("Get levels by complexity - {}", complexity);
        return levelRepository.findAllByComplexity(complexity);
    }

    public List<Level> getLevelsByGameId(Long game_id) {
        log.info("Get levels by game_id {}", game_id);
        return levelRepository.findAllByGameId(game_id);
    }
}

