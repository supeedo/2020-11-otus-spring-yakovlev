package ru.library.dto;

public class Genre {
    private final Long id;
    private final String genreName;

    public Genre(Long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
