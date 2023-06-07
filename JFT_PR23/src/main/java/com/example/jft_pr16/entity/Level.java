package com.example.jft_pr16.entity;

import com.example.jft_pr16.entity.dto.LevelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "levels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "complexity")
    private int complexity;

    @ManyToOne(fetch = FetchType.LAZY) //!
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    @JsonIgnore
    private Game game;
    @Column(name = "game_id")
    private Long gameId;

    @Override
    public String toString() { //!
        return "Level{" +
                "id=" + id +
                ", levelName='" + levelName + '\'' +
                ", complexity=" + complexity + '\'' +
                ", gameId=" + gameId +
                '}';
    }

    public LevelDTO makeDTO() {
        return new LevelDTO(id, levelName, complexity, gameId);
    }
}
