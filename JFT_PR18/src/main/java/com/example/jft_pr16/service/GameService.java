package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Game;
import com.example.jft_pr16.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    @Autowired
    private final GameRepository gameRepository;

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.getReferenceById(id);
    }

    public void saveGame(Game game){
        gameRepository.save(game);
    }

    public void deleteGameById(Long id){
        gameRepository.deleteById(id);
    }

    public void updateGameById(Game game, Long id){
        game.setId(id);
        gameRepository.save(game);
    }

    public List<Game> getGamesByGameName(String gameName) {
        return gameRepository.findAllByGameName(gameName);
    }

    public List<Game> getGamesByCreationDate(String creationDate) {
        return gameRepository.findAllByCreationDate(creationDate);
    }

}
