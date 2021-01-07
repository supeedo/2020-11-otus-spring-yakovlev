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

    public Long getID() {
        return ID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
