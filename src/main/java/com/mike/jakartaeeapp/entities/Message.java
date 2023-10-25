package com.mike.jakartaeeapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = Message.TABLENAME)
@NamedQueries({
        @NamedQuery(
                name = "messages.getAll",
                query = "select m from Message m"
        )
})
public class Message implements Serializable {
    public final static String TABLENAME = "messages";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "message")
    private String message;
}
