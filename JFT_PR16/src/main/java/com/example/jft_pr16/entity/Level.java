package com.example.jft_pr16.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "levelName")
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
                ", complexity=" + complexity +
                ", game=" + game +
                ", gameId=" + gameId +
                '}';
    }
}
