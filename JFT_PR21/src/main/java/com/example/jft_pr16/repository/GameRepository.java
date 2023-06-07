package com.example.jft_pr16.repository;

import com.example.jft_pr16.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByGameName(String gameName);
    List<Game> findAllByCreationDate(String creationDate);
}
