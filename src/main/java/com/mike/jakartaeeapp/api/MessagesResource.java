package com.mike.jakartaeeapp.api;

import com.mike.jakartaeeapp.entities.Message;
import com.mike.jakartaeeapp.model.Language;
import com.mike.jakartaeeapp.model.MessageRequest;
import com.mike.jakartaeeapp.service.MessagesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.NonNull;

import java.util.List;

@Path("/messages")
public class MessagesResource {

    @Inject
    private MessagesService greetingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages() {
        return this.greetingService.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Message getGreetingById(@NonNull @PathParam("id") Long messageId) {
        return this.greetingService.getMessage(messageId);
    }

    @POST
    public void saveMessage(MessageRequest messageRequest) {
        this.greetingService.saveMessage(messageRequest);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/generates/{name}")
    public String getGreeting(@PathParam("name") String name,
                              @QueryParam("language") Language language) {
        return this.greetingService.getGreetingBasedOnLanguage(name, language);
    }
}