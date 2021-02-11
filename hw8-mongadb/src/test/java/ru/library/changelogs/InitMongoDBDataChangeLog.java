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
    private Book book1;
    private Book book2;

    @ChangeSet(order = "000", id = "dropDB", author = "me", runAlways = true)
    public void dropDB(MongoDatabase database) {
        log.info("Drop database {}", database.getName());
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "me", runAlways = true)
    public void initGenres(GenreRepositories repository) {
        genre1 = new Genre("1", "Computer science");
        var genre2 = new Genre("2", "Science Fiction");
        repository.save(genre1);
        repository.save(genre2);
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "me", runAlways = true)
    public void initAuthors(AuthorRepositories repository) {
        author1 = new Author("1", "Robert Martin");
        author2 = new Author("2","Joshua Bloch");
        repository.save(author1);
        repository.save(author2);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "me", runAlways = true)
    public void initBooks(BookRepositories repository) {
        book1 = new Book("Clean Code", author1, genre1);
        book2 = new Book("Effective Java", author2, genre1);
        repository.save(book1);
        repository.save(book2);
    }

    @ChangeSet(order = "004", id = "initComments", author = "me", runAlways = true)
    public void initComments(CommentRepositories repository) {
        var comment1 = new Comment("1","nice comment!", book1);
        var comment2 = new Comment("2","great comment!", book2);
        repository.save(comment1);
        repository.save(comment2);
    }
}
