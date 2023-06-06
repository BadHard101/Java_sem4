package com.example.jft_pr15.entity;

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

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", levelName='" + levelName + '\'' +
                ", complexity='" + complexity + '\'' +
                '}';
    }
}
