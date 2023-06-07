package com.example.jft_pr16.entity;

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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gameName")
    private String gameName;

    @Column(name = "creationDate")
    private String creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game") //!
    private List<Level> levels = new ArrayList<>();

    @Override
    public String toString() { //!
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", levels=" + levels +
                '}';
    }
}
