package ru.library.changelogs;

import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Comment;
import ru.library.models.Genre;
import ru.library.repository.AuthorRepositories;
import ru.library.repository.BookRepositories;
import ru.library.repository.CommentRepositories;
import ru.library.repository.GenreRepositories;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private static final Logger log = LoggerFactory.getLogger(InitMongoDBDataChangeLog.class);

    private Genre genre1;
    private Author author1;
    private Author author2;
    private Author author3;
    private Author author4;
    private Author author5;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;

    @ChangeSet(order = "000", id = "dropDB", author = "me", runAlways = true)
    public void dropDB(MongoDatabase database) {
        log.info("Drop database {}", database.getName());
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "me", runAlways = true)
    public void initGenres(GenreRepositories repository) {
        genre1 = new Genre();
        genre1.setGenreName("Computer science");
        var genre2 = new Genre();
        genre2.setGenreName("Science Fiction");
        var genre3 = new Genre();
        genre3.setGenreName("Detective");
        var genre4 = new Genre();
        genre4.setGenreName("Humor");
        var genre5 = new Genre();
        genre5.setGenreName("Romance");
        repository.save(genre1);
        repository.save(genre2);
        repository.save(genre3);
        repository.save(genre4);
        repository.save(genre5);
//        List.of(genre1, genre2, genre3, genre4, genre5).forEach(repository::save);
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "me", runAlways = true)
    public void initAuthors(AuthorRepositories repository) {
        author1 = new Author();
        author1.setFullName("Robert Martin");
        author2 = new Author();
        author2.setFullName("Joshua Bloch");
        author3 = new Author();
        author3.setFullName("Raoul-Gabriel Urma");
        author4 = new Author();
        author4.setFullName("Iuliana Cosmina");
        author5 = new Author();
        author5.setFullName("Craig Walls");
        repository.save(author1);
        repository.save(author2);
        repository.save(author3);
        repository.save(author4);
        repository.save(author5);
//        List.of(author1, author2, author3, author4, author5).forEach(repository::save);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "me", runAlways = true)
    public void initBooks(BookRepositories repository) {
        book1 = new Book();
        book1.setBookTitle("Clean Code");
        book1.setAuthor(author1);
        book1.setGenre(genre1);
        book2 = new Book();
        book2.setBookTitle("Effective Java");
        book2.setAuthor(author2);
        book2.setGenre(genre1);
        book3 = new Book();
        book3.setBookTitle("Modern Java in Action: Lambdas, streams, functional and reactive programming");
        book3.setAuthor(author3);
        book3.setGenre(genre1);
        book4 = new Book();
        book4.setBookTitle("Pro Spring 5: An In-Depth Guide to the Spring Framework and Its Tools");
        book4.setAuthor(author4);
        book4.setGenre(genre1);
        book5 = new Book();
        book5.setBookTitle("Spring in Action");
        book5.setAuthor(author5);
        book5.setGenre(genre1);
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
//        List.of(book1, book2, book3, book4, book5).forEach(repository::save);
    }

    @ChangeSet(order = "004", id = "initComments", author = "me", runAlways = true)
    public void initComments(CommentRepositories repository) {
        var comment1 = new Comment();
        comment1.setComment("nice comment!");
        comment1.setBook(book1);
        var comment2 = new Comment();
        comment1.setComment("great comment!");
        comment1.setBook(book1);
        var comment3 = new Comment();
        comment1.setComment("amazing comment!");
        comment1.setBook(book2);
        var comment4 = new Comment();
        comment1.setComment("magnificently!");
        comment1.setBook(book3);
        var comment5 = new Comment();
        comment1.setComment("amazing!");
        comment1.setBook(book4);
        repository.save(comment1);
        repository.save(comment2);
        repository.save(comment3);
        repository.save(comment4);
        repository.save(comment5);
//        List.of(comment1, comment2, comment3, comment4, comment5).forEach(repository::save);
    }
}
