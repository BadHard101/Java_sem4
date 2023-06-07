package com.example.jft_pr16.service;

import java.util.List;

public interface TableService<T> {
    void create(T t);

    List<T> readAll();

    T read(Long id);

    Boolean update(T t, Long id);

    Boolean delete(Long id);
}
