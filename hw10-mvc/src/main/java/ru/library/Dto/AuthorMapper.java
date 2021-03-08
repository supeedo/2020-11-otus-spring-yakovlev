package ru.library.Dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Author;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    default AuthorDto authorToAuthorDto(Author author){
        return new AuthorDto(author.getId().toString(), author.getFullName());
    }

    default Author authorDtoToAuthor(AuthorDto authorDto){
        return new Author(Long.parseLong(authorDto.getId()), authorDto.getFullName());
    }
}
