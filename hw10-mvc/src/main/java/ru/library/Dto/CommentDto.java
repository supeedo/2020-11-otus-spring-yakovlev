package ru.library.Dto;

public class CommentDto {
    private long id;
    private String comment;
    private BookDto book;

    public CommentDto() {
    }

    public CommentDto(long id, String comment, BookDto book) {
        this.id = id;
        this.comment = comment;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                '}';
    }
}
