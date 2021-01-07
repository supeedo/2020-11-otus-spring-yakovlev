package ru.library.dto;

public class Author {
    private final Long ID;
    private final String fullName;

    public Author(Long ID, String fullName) {
        this.ID = ID;
        this.fullName = fullName;
    }
}
