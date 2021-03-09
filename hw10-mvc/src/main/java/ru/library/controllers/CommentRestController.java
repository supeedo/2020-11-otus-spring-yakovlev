package ru.library.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.library.Dto.CommentDto;
import ru.library.services.CommentServiceImpl;

import java.util.List;

@RestController
public class CommentRestController {

    private final CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showCommentsByBookId(@PathVariable Long id) {
        final List<CommentDto> book = commentService.getAllCommentsByBookId(id);
        return book != null ? ResponseEntity.ok(book)
                : ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }
}
