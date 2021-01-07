package ru.library.dto;

public class Book {
    private final Long ID;
    private final String bookTitle;
    private final Author author;
    private final Genre genre;

    public Book(Long id, String bookTitle, Author author, Genre genre) {
        ID = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }
}
