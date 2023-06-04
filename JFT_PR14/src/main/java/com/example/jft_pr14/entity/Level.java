package com.example.jft_pr14.entity;

import java.util.Objects;

public class Level {
    private String levelName;
    private int complexity;

    public String getLevelName() {
        return levelName;
    }

    @Override
    public String toString() {
        return "Level{" +
                "name='" + levelName + '\'' +
                ", complexity=" + complexity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return complexity == level.complexity && Objects.equals(levelName, level.levelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelName, complexity);
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public Level() {
    }
}
