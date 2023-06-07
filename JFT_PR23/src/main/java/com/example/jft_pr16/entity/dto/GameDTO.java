package com.example.jft_pr16.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private String gameName;
    private String creationDate;
    private List<LevelDTO> levels;
}
