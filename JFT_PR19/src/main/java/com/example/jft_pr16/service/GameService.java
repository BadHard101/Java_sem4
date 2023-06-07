package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {
    @Autowired
    private final GameRepository gameRepository;

    public List<Game> getGames() {
        log.info("Get all games");
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        log.info("Get game by id {}", id);
        return gameRepository.getReferenceById(id);
    }

    public void saveGame(Game game){
        log.info("Save game {}", game);
        gameRepository.save(game);
    }

    public void deleteGameById(Long id){
        log.info("Delete game by id {}", id);
        gameRepository.deleteById(id);
    }

    public void updateGameById(Game game, Long id){
        log.info("Update game {} by id {}", game, id);
        game.setId(id);
        gameRepository.save(game);
    }

    public List<Game> getGamesByGameName(String gameName) {
        log.info("Get games by gameName - {}", gameName);
        return gameRepository.findAllByGameName(gameName);
    }

    public List<Game> getGamesByCreationDate(String creationDate) {
        log.info("Get games by creationDate - {}", creationDate);
        return gameRepository.findAllByCreationDate(creationDate);
    }

}
