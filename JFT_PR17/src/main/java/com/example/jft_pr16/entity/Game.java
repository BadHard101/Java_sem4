package com.example.jft_pr16.entity;

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
    private String game_name;

    @Column(name = "creation_date")
    private String creation_date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game") //!
    private List<Level> levels = new ArrayList<>();

    @Override
    public String toString() { //!
        return "Game{" +
                "id=" + id +
                ", gameName='" + game_name + '\'' +
                ", creationDate='" + creation_date + '\'' +
                ", levels=" + levels +
                '}';
    }
}
