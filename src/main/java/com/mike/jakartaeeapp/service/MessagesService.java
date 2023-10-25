package com.mike.jakartaeeapp.service;

import com.mike.jakartaeeapp.dao.MessagesDao;
import com.mike.jakartaeeapp.entities.Message;
import com.mike.jakartaeeapp.model.Language;
import com.mike.jakartaeeapp.model.MessageRequest;
import com.mike.jakartaeeapp.utils.Timed;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
public class MessagesService {

    @EJB
    private MessagesDao messagesDao;

    @PostConstruct
    public void init() {
        System.out.println("EJB created!");
    }

    public Message getMessage(final Long messageId) {
        return this.messagesDao.getById(messageId);
    }

    public List<Message> getAllMessages() {
        return this.messagesDao.getAll();
    }

    @Transactional
    public void saveMessage(final MessageRequest messageRequest) {
        final Message message = new Message();
        message.setMessage(messageRequest.getMessage());

        this.messagesDao.save(message);
    }

    //@Timed
    public String getGreetingBasedOnLanguage(final String name, final Language language) {
        if (name.equals("Putin")) {
            throw new IllegalArgumentException("no no no");
        }

        String greetingMessage;
        switch (language) {
            case ENG -> greetingMessage = "Hello";
            case UKR -> greetingMessage = "Привіт";
            default -> throw new IllegalArgumentException("Wrong language parameter");
        }

       return String.format("%s %s ", greetingMessage, name);
    }
}
