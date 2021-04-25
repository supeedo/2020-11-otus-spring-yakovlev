package ru.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.library.models.Comment;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default CommentDto commentToCommentDto (Comment comment){
        return new CommentDto(comment.getId(), comment.getComment(), BookMapper.INSTANCE.bookToBookDto(comment.getBook()));
    }

    default Comment commentDtoToComment (CommentDto commentDto){
        return new Comment(commentDto.getId(), commentDto.getComment(), BookMapper.INSTANCE.bookDtoToBook(commentDto.getBook()));
    }
}
