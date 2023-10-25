package com.mike.jakartaeeapp.dao;

import com.mike.jakartaeeapp.entities.Message;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class MessagesDao {

    @PersistenceContext(name = "jakartaEEAppPersistenceUnit")
    private EntityManager entityManager;

    public List<Message> getAll() {
        final TypedQuery<Message> query = this.entityManager.createNamedQuery("messages.getAll", Message.class);
        final List<Message> queryResult = query.getResultList();

        return queryResult;
    }

    public Message getById(long id) {
        final Message message = this.entityManager.find(Message.class, id);

        return message;
    }

    public void save(Message message) {
        entityManager.persist(message);
    }
}
