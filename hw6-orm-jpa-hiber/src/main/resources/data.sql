insert into books (id, `book_title`, `author_id`, `genre_id`)
values (default, 'Clean Code', 1, 1);
insert into books (id, `book_title`, `author_id`, `genre_id`)
values (default, 'Effective Java', 2, 1);
insert into books (id, `book_title`, `author_id`, `genre_id`)
values (default, 'Modern Java in Action: Lambdas, streams, functional and reactive programming', 3, 1);
insert into books (id, `book_title`, `author_id`, `genre_id`)
values (default, 'Pro Spring 5: An In-Depth Guide to the Spring Framework and Its Tools', 4, 1);
insert into books (id, `book_title`, `author_id`, `genre_id`)
values (default, 'Spring in Action', 5, 1);

insert into genres (id, `genre_name`)
values (default, 'Computer science');
insert into genres (id, `genre_name`)
values (default, 'Science Fiction');
insert into genres (id, `genre_name`)
values (default, 'Detective');
insert into genres (id, `genre_name`)
values (default, 'Humor');
insert into genres (id, `genre_name`)
values (default, 'Romance');

insert into authors (id, `full_name`)
values (default, 'Robert Martin');
insert into authors (id, `full_name`)
values (default, 'Joshua Bloch');
insert into authors (id, `full_name`)
values (default, 'Raoul-Gabriel Urma');
insert into authors (id, `full_name`)
values (default, 'Iuliana Cosmina');
insert into authors (id, `full_name`)
values (default, 'Craig Walls');

insert into comments (id, book_id, comment)
values (default, 1, 'test comment 1');
insert into comments (id, book_id, comment)
values (default, 1, 'test comment 2');
insert into comments (id, book_id, comment)
values (default, 2, 'test comment 3');
insert into comments (id, book_id, comment)
values (default, 3, 'test comment 4');
insert into comments (id, book_id, comment)
values (default, 4, 'test comment 5');