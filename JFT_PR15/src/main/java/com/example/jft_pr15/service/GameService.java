package com.example.jft_pr15.service;

import com.example.jft_pr15.entity.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService implements TableService<Game>{
    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @Transactional
    @Override
    public void create(Game game) {
        session = entityManager.unwrap(Session.class);
        session.persist(game);
        session.close();
    }

    @Override
    public List<Game> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Game> doctors = session.createQuery("select i from Game i", Game.class).getResultList();
        session.close();
        return doctors;
    }

    @Override
    public Game read(Long id) {
        session = entityManager.unwrap(Session.class);
        Game game = session.createQuery(
                "from Game where id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return game;
    }

    @Transactional
    @Override
    public Boolean update(Game o, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Game set gameName=:fn, creationDate=:ln where id=:id")
                .setParameter("id", id)
                .setParameter("fn", o.getGameName())
                .setParameter("ln", o.getCreationDate());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        session.close();
        return true;
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Game game = session.get(Game.class, id);
        session.remove(game);
        session.close();
        return true;
    }
}
