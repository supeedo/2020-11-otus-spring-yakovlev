package ru.library.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document
public class Comment {
    @Id
    private String id;

    @NotNull
    private String comment;

    @NotNull
    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String id, String comment, Book books) {
        this.id = id;
        this.comment = comment;
        this.book = books;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Book getBook() {
        return book;
    }

    public void setId(String id) {
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
        Comment comment1 = (Comment) o;
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
