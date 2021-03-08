package ru.library.Dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto book);

    default String mapAuthorToString(Author author){
        return author.getFullName();
    }

    default String mapGenreToString(Genre genre){
        return genre.getGenreName();
    }

    default Author mapStringToAuthor(String fullName){
        return new Author(0L, fullName);
    }

    default Genre mapStringToGenre(String genreName){
        return new Genre(0L, genreName);
    }
}
