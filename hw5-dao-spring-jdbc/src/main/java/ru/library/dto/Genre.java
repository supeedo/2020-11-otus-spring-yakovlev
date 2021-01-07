package ru.library.dto;

public class Genre {
    private final Long ID;
    private final String genreName;

    public Genre(Long id, String genreName) {
        ID = id;
        this.genreName = genreName;
    }
}
