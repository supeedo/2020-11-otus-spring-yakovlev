package ru.library.models;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genre_name", nullable = false, unique = true)
    private String genreName;


    public Genre() {
    }

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
