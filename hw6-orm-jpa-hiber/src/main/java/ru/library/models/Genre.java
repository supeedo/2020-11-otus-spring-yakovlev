package ru.library.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "genre_name", nullable = false, unique = true)
    private String genreName;

    public Genre() {
    }

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id && Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreName);
    }
}
