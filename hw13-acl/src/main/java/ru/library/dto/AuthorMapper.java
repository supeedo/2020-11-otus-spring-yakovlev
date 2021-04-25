package ru.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Author;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    default AuthorDto authorToAuthorDto(Author author){
        return new AuthorDto(author.getId(), author.getFullName());
    }

    default Author authorDtoToAuthor(AuthorDto authorDto){
        return new Author(authorDto.getId(), authorDto.getFullName());
    }
}
