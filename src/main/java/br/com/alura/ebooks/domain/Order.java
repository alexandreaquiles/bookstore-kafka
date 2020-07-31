package br.com.alura.ebooks.domain;

import java.util.UUID;

public class Order {

    private UUID clientId;
    private Book book;

    public Order(Book book) {
        this.clientId = UUID.randomUUID();
        this.book = book;
    }

    public Order(UUID clientId, Book book) {
        this.clientId = clientId;
        this.book = book;
    }

    public UUID getClientId() {
        return clientId;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", book=" + book +
                '}';
    }
}
