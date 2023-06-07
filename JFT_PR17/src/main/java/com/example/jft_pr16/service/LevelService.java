package com.example.jft_pr16.service;

import com.example.jft_pr16.entity.Level;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LevelService {
    @PersistenceContext
    private EntityManager entityManager;
    private Session session = null;

    @Autowired //!
    GameService gameService; //!

    @Transactional
    public void create(Level level) {
        session = entityManager.unwrap(Session.class);
        session.persist(level);
    }

    public List<Level> readAll() {
        session = entityManager.unwrap(Session.class);
        List<Level> levels = session.createQuery("select i from Level i", Level.class).getResultList();
        session.close();
        return levels;
    }

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
    public Boolean update(Level level, Long id) {
        session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update Level set level_name=:fn, complexity=:ln where id=:id")
                .setParameter("id", id)
                .setParameter("fn", level.getLevel_name())
                .setParameter("ln", level.getComplexity());
        int status = query.executeUpdate();
        System.out.println(status);
        transaction.commit();
        return true;
    }
    @Transactional
    public Boolean delete(Long id) {
        session = entityManager.unwrap(Session.class);
        Level level = session.get(Level.class, id);
        session.remove(level);
        return true;
    }

    public <T> List<Level> getLevelsFilteredBy(String attribute, T value) {
        session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Level> query = cb.createQuery(Level.class);
        Root<Level> root = query.from(Level.class);
        Predicate predicate = cb.equal(root.get(attribute), value);

        query.select(root).where(predicate);

        return session.createQuery(query).getResultList();
    }
}
