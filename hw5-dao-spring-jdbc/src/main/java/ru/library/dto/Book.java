package ru.library.dto;

public class Book {
    private final Long id;
    private final String bookTitle;
    private final Author author;
    private final Genre genre;

    public Book(Long id, String bookTitle, Author author, Genre genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
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
                "ID=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

    public Object[] getTablePresentation() {
        return new Object[]{id.toString(), bookTitle, author.getFullName(), genre.getGenreName()};
    }
}
