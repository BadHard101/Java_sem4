package com.example.jft_pr16.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LevelDTO {
    private Long id;
    private String levelName;
    private int complexity;
    private Long gameId;
}
