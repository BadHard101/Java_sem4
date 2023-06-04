package com.example.jft_pr14.entity;

import java.util.Objects;

public class Game {
    private String creationDate;
    private String name;

    public Game() {
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "creationDate='" + creationDate + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(creationDate, that.creationDate) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, name);
    }
}
