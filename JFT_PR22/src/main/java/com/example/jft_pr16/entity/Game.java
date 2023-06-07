package com.example.jft_pr16.entity;

import com.example.jft_pr16.entity.dto.GameDTO;
import com.example.jft_pr16.entity.dto.LevelDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "creation_date")
    private String creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game") //!
    private List<Level> levels = new ArrayList<>();

    @Override
    public String toString() { //!
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", creationDate='" + creationDate +
                '}';
    }

    public GameDTO makeDTO() {
        List<LevelDTO> levelDTOList = levels.stream().map(Level::makeDTO).toList();
        return new GameDTO(id, gameName, creationDate, levelDTOList);
    }
}
