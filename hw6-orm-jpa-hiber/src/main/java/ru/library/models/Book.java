package ru.library.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "book_title", nullable = false, unique = true)
    private String bookTitle;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")//, foreignKey = @ForeignKey(name = "FK_author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")//, foreignKey = @ForeignKey(name = "FK_genre_id")
    private Genre genre;

    public Book() {}

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

    public Object[] getTablePresentation() {
        return new Object[]{id, bookTitle, author.getFullName(), genre.getGenreName()};
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
//                ", comments=" + comments +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
