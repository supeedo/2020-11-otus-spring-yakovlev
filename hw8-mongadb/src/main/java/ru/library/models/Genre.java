package ru.library.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document
public class Genre {
    @Id
    private String id;

    @NotNull
    private String genreName;

    public Genre() {
    }

    public Genre(String id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public String getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setId(String id) {
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
