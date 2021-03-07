package ru.library.Dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDto(Book book);

    Book bookDtoToBook(BookDTO book);

    default String mapAuthorToString(Author author){
        return author.getFullName();
    }

    default String mapGenreToString(Genre genre){
        return genre.getGenreName();
    }

    default Author mapStringToAuthor(String value){
        return new Author(0L, value);
    }

    default Genre mapStringToGenre(String value){
        return new Genre(0L, value);
    }
}
