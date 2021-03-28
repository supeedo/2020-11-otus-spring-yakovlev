package ru.library.changelog;

import java.util.ArrayList;
import java.util.List;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;

@ChangeLog
public class DatabaseChangelog {

    private static final String GENRE = "genre";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String AND = "$and";
    private static final String MIDDLENAME = "middlename";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String NAME = "name";
    
    @ChangeSet(order = "001", id = "addGenres", author = "dlekhanov")
    public void insertGenres(DB db) {
        DBCollection myCollection = db.getCollection("genres");
        myCollection.insert(
                new BasicDBObject().append(NAME, "Russian classics"),
                new BasicDBObject().append(NAME, "Foreign classics"),
                new BasicDBObject().append(NAME, "Fantasy"),
                new BasicDBObject().append(NAME, "Fiction"),
                new BasicDBObject().append(NAME, "Contemporary prose"),
                new BasicDBObject().append(NAME, "Adventure"),
                new BasicDBObject().append(NAME, "Mystique"),
                new BasicDBObject().append(NAME, "Journalistic literature"),
                new BasicDBObject().append(NAME, "Romance novels"),
                new BasicDBObject().append(NAME, "Science and education"),
                new BasicDBObject().append(NAME, "Ancient literature"),
                new BasicDBObject().append(NAME, "Ancient literature"),
                new BasicDBObject().append(NAME, "Myths, legends, epic"),
                new BasicDBObject().append(NAME, "Old Russian literature"));
    }
    
    @ChangeSet(order = "002", id = "addAuthors", author = "dlekhanov")
    public void insertAuthors(DB db) {
        DBCollection myCollection = db.getCollection("authors");
        myCollection.insert(
                new BasicDBObject().append(LASTNAME, "Pushkin").append(FIRSTNAME, "Alexander").append(MIDDLENAME, "Sergeevich"),
                new BasicDBObject().append(LASTNAME, "Tolstoy").append(FIRSTNAME, "Lev").append(MIDDLENAME, "Nikolaevich"),
                new BasicDBObject().append(LASTNAME, "Dostoevsky").append(FIRSTNAME, "Fedor").append(MIDDLENAME, "Mikhailovich"),
                new BasicDBObject().append(LASTNAME, "Chekhov").append(FIRSTNAME, "Anton").append(MIDDLENAME, "Pavlovich"),
                new BasicDBObject().append(LASTNAME, "Gorkiy").append(FIRSTNAME, "Maksim"),
                new BasicDBObject().append(LASTNAME, "Garrot").append(FIRSTNAME, "Николай").append(MIDDLENAME, "Vasilevich"),
                new BasicDBObject().append(LASTNAME, "Tolstoy").append(FIRSTNAME, "Alexei").append(MIDDLENAME, "Nikolaevich"),
                new BasicDBObject().append(LASTNAME, "Turgenev").append(FIRSTNAME, "Ivan").append(MIDDLENAME, "Sergeevich"),
                new BasicDBObject().append(LASTNAME, "Lermontov").append(FIRSTNAME, "Michael").append(MIDDLENAME, "Yurievich"),
                new BasicDBObject().append(LASTNAME, "Kuprin").append(FIRSTNAME, "Alexander").append(MIDDLENAME, "Ivanovich")
        );
    }

    @ChangeSet(order = "003", id = "addBooks", author = "dlekhanov")
    public void insertBooks(DB db) {
        DBRef author = getAuthor(db);
        DBRef genre = getGenre(db);
        
        DBCollection myCollection = db.getCollection("books");
        myCollection.insert(
                new BasicDBObject().append(TITLE, "War and Peace").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(TITLE, "Anna Karenina").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(TITLE, "Sunday").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(TITLE, "Family happiness").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(TITLE, "Childhood, Adolescence, Youth").append(AUTHOR, author).append(GENRE, genre),
                new BasicDBObject().append(TITLE, "Sevastopol stories").append(AUTHOR, author).append(GENRE, genre)
        );
    }

    private DBRef getGenre(DB db) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Russian classics");
        DBObject genre = db.getCollection("genres").findOne(query);
        return new DBRef("genres", genre.get("_id"));
    }

    private DBRef getAuthor(DB db) {
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject(LASTNAME, "Tolstoy"));
        obj.add(new BasicDBObject(FIRSTNAME, "Lev"));
        andQuery.put(AND, obj);
        DBObject author = db.getCollection("authors").findOne(andQuery);
        return new DBRef("authors", author.get("_id"));
    }
}
