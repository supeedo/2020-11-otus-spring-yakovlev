package ru.library.dto;

import java.util.Objects;

public class GenreDto {
    private long id;
    private String genreName;

    public GenreDto() {
    }

    public GenreDto(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public GenreDto(String genreName) {
        this.genreName = genreName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id='" + id + '\'' +
                ", genreName='" + genreName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genreDto = (GenreDto) o;
        return id == genreDto.id && Objects.equals(genreName, genreDto.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreName);
    }
}
