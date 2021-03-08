package ru.library.Dto;

public class GenreDto {
    private String id;
    private String genreName;

    public GenreDto() {
    }

    public GenreDto(String id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public GenreDto(String genreName) {
        this.genreName = genreName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
