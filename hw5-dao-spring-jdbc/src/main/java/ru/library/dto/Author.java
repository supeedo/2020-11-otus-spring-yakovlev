package ru.library.dto;

public class Author {
    private final Long id;
    private final String fullName;

    public Author(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
