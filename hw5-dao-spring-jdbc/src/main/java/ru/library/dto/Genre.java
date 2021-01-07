package ru.library.dto;

public class Genre {
    private final Long ID;
    private final String genreName;

    public Genre(Long id, String genreName) {
        ID = id;
        this.genreName = genreName;
    }

    public Long getID() {
        return ID;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + ID +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
