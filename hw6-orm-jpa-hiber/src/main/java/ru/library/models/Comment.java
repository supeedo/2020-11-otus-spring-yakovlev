package ru.library.models;

import javax.persistence.*;

@Entity
@Table(name= "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {
    }

    public Comment(Long id, String comment, Book books) {
        this.id = id;
        this.comment = comment;
        this.book = books;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Book getBook() {
        return book;
    }
}
