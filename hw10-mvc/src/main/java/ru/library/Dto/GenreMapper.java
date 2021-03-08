package ru.library.Dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Genre;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    default GenreDto genreToGenreDto (Genre genre){
        return new GenreDto(genre.getId().toString(), genre.getGenreName());
    }

    default Genre genreDtoToGenre (GenreDto genreDto){
        return new Genre(Long.parseLong(genreDto.getId()), genreDto.getGenreName());
    }
}
