package ru.library.models;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_name", nullable = false, unique = true)
    private String genreName;


    public Genre() {
    }

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
