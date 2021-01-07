package ru.library.dto;

public class Author {
    private final Long ID;
    private final String fullName;

    public Author(Long ID, String fullName) {
        this.ID = ID;
        this.fullName = fullName;
    }

    public Long getID() {
        return ID;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID=" + ID +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
