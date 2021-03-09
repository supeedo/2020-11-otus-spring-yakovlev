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

    default String mapAuthorToString(Author author) {
        return author.getFullName();
    }

    default GenreDto mapGenreToGenreDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreName());
    }

    default Author mapStringToAuthor(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getFullName());
    }

    default Genre mapStringToGenre(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getGenreName());
    }
}
