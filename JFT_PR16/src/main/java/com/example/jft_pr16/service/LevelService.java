package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Level;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LevelService implements TableService<Level>{
    @PersistenceContext
    private EntityManager entityManager;
    private Session session = null;

    @Autowired //!
    GameService gameService; //!

    @Transactional
    @Override
    public void create(Level level) {
        session = entityManager.unwrap(Session.class);
        session.persist(level);
    }

    @Override
    public List<Level> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Level> levels = session.createQuery("select i from Level i", Level.class).getResultList();
        session.close();
        return levels;
    }

    @Override
    public Level read(Long id) {
        session = entityManager.unwrap(Session.class);
        Level level = session.createQuery(
                        "from Level where id = :id", Level.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return level;
    }

    @Transactional
    @Override
    public Boolean update(Level level, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Level set levelName=:fn, complexity=:ln where id=:id")
                .setParameter("id", id)
                .setParameter("fn", level.getLevelName())
                .setParameter("ln", level.getComplexity());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        return true;
    }
    @Transactional
    @Override
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Level level = session.get(Level.class, id);
        session.remove(level);
        return true;
    }
}
