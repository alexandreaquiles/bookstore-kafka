package br.com.alura.ebooks.domain;

public class Book {

    private String name;
    private String authors;
    private Double price;

    public Book(String name, String authors, Double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", authors='" + authors + '\'' +
                ", price=" + price +
                '}';
    }
}
