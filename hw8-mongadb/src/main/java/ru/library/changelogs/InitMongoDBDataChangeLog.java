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
        genre1 = new Genre("Computer science");
        var genre2 = new Genre("Science Fiction");
        var genre3 = new Genre("Detective");
        var genre4 = new Genre("Humor");
        var genre5 = new Genre("Romance");
        repository.save(genre1);
        repository.save(genre2);
        repository.save(genre3);
        repository.save(genre4);
        repository.save(genre5);
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "me", runAlways = true)
    public void initAuthors(AuthorRepositories repository) {
        author1 = new Author("Robert Martin");
        author2 = new Author("Joshua Bloch");
        author3 = new Author("Raoul-Gabriel Urma");
        author4 = new Author("Iuliana Cosmina");
        author5 = new Author("Craig Walls");
        repository.save(author1);
        repository.save(author2);
        repository.save(author3);
        repository.save(author4);
        repository.save(author5);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "me", runAlways = true)
    public void initBooks(BookRepositories repository) {
        book1 = new Book("Clean Code", author1, genre1);
        book2 = new Book("Effective Java", author2, genre1);
        book3 = new Book("Modern Java in Action: Lambdas, streams, functional and reactive programming", author3, genre1);
        book4 = new Book("Pro Spring 5: An In-Depth Guide to the Spring Framework and Its Tools", author4, genre1);
        book5 = new Book("Spring in Action", author5, genre1);
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);
        repository.save(book5);
    }

    @ChangeSet(order = "004", id = "initComments", author = "me", runAlways = true)
    public void initComments(CommentRepositories repository) {
        var comment1 = new Comment("nice comment!", book1);
        var comment2 = new Comment("great comment!", book1);
        var comment3 = new Comment("amazing comment!", book2);
        var comment4 = new Comment("magnificently!", book3);
        var comment5 = new Comment("amazing!", book4);
        repository.save(comment1);
        repository.save(comment2);
        repository.save(comment3);
        repository.save(comment4);
        repository.save(comment5);
    }
}
