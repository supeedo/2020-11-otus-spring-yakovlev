package ru.library.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "book_title", nullable = false, unique = true)
    private String bookTitle;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_author_id"))
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_genre_id"))
    private Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    private List<BookComment> comments;

    public Book() {
    }

    public Book(Long id, String bookTitle, Author author, Genre genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }

    public long getId() {
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

    public List<BookComment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, author, genre);
    }

}
