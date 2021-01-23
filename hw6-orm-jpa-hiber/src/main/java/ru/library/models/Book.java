package ru.library.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_title", nullable = false, unique = true)
    private String bookTitle;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "FK_author_id"))
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "FK_genre_id"))
    private Genre genre;

//    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "comment_id", foreignKey = @ForeignKey(name = "FK_comment_id"))
//    private List<Comment> comments;

    public Book() {

    }

    public Book(Long id, String bookTitle, Author author, Genre genre, List<Comment> comments) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
//        this.comments = comments;
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
        return new Object[]{id.toString(), bookTitle, author.getFullName(), genre.getGenreName()};
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
}
