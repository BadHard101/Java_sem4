package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Level;
import com.example.jft_pr16.entity.dto.GameDTO;
import com.example.jft_pr16.entity.dto.LevelDTO;
import com.example.jft_pr16.repository.GameRepository;
import com.example.jft_pr16.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {
    @Autowired
    private final LevelRepository levelRepository;

    @Autowired
    private final GameRepository gameRepository;

    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    public Level getLevelById(Long id) {
        return levelRepository.getReferenceById(id);
    }

    public void saveLevel(Level level) {
        levelRepository.save(level);
    }

    public void deleteLevelById(Long id) {
        levelRepository.deleteById(id);
    }

    public void updateLevelById(Level level, Long id) {
        level.setId(id);
        levelRepository.save(level);
    }

    public GameDTO getGameByLevelId(Long levelId) {
        LevelDTO levelDTO = levelRepository.getById(levelId).makeDTO();
        GameDTO gameDTO = gameRepository.getById(levelDTO.getGameId()).makeDTO();
        return gameDTO;
    }

    public List<Level> getLevelsByLevelName(String levelName){
        return levelRepository.findAllByLevelName(levelName);
    }

    public List<Level> getLevelsByComplexity(int complexity) {
        return levelRepository.findAllByComplexity(complexity);
    }

    public List<Level> getLevelsByGameId(Long game_id) {
        return levelRepository.findAllByGameId(game_id);
    }
}

