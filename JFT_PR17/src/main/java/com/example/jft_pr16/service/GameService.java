package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    @PersistenceContext
    private EntityManager entityManager;
    private Session session;

    @Transactional
    public void create(Game game) {
        session = entityManager.unwrap(Session.class);
        session.persist(game);
        session.close();
    }

    public List<Game> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Game> doctors = session.createQuery("select i from Game i", Game.class).getResultList();
        session.close();
        return doctors;
    }

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
    public Boolean update(Game o, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Game set game_name=:gn, creation_date=:cd where id=:id")
                .setParameter("id", id)
                .setParameter("gn", o.getGame_name())
                .setParameter("cd", o.getCreation_date());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        session.close();
        return true;
    }

    @Transactional
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Game game = session.get(Game.class, id);
        session.remove(game);
        session.close();
        return true;
    }

    public <T> List<Game> getGamesFilteredBy(String attribute, T value) {
        session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Game> query = cb.createQuery(Game.class);
        Root<Game> root = query.from(Game.class);
        Predicate predicate = cb.equal(root.get(attribute), value);

        query.select(root).where(predicate);

        return session.createQuery(query).getResultList();
    }
}
