package ru.library.models;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = true, unique = true)
    private String fullName;

    public Author() {
    }

    public Author(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
