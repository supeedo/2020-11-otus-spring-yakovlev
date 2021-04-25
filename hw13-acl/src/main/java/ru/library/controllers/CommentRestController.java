package ru.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.library.dto.CommentDto;
import ru.library.services.CommentServiceImpl;

import java.util.List;

@RestController
public class CommentRestController {

    private static final Logger log = LoggerFactory.getLogger(CommentRestController.class);

    private final CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CommentDto>> showCommentsByBookId(@RequestParam("id") Long id) {
        log.debug("Book id for comment: {}", id);
        final List<CommentDto> book = commentService.getAllCommentsByBookId(id);
        log.debug("Book list from db: {}", book);
        return ResponseEntity.ok(book);
    }
}
