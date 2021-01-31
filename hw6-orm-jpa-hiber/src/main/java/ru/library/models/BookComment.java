package ru.library.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_book_id"))
    private Book book;

    public BookComment() {
    }

    public BookComment(Long id, String comment, Book books) {
        this.id = id;
        this.comment = comment;
        this.book = books;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Book getBook() {
        return book;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookComment comment1 = (BookComment) o;
        return Objects.equals(id, comment1.id) && Objects.equals(comment, comment1.comment) && Objects.equals(book, comment1.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, book);
    }

    @Override
    public String toString() {
        return "BookComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                '}';
    }
}
