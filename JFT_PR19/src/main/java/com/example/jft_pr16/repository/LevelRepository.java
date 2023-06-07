package com.example.jft_pr16.repository;

import com.example.jft_pr16.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findAllByLevelName(String levelName);
    List<Level> findAllByComplexity(int complexity);
    List<Level> findAllByGameId(Long gameId);
}
