insert into books (ID, `BOOK_TITLE`, `AUTHOR_ID`, `GENRE_ID`)
values (default, 'Clean Code', 1, 1);
insert into books (ID, `BOOK_TITLE`, `AUTHOR_ID`, `GENRE_ID`)
values (default, 'Effective Java', 2, 1);

insert into genres (ID, `GENRE_NAME`)
values (1, 'Computer science');
insert into genres (ID, `GENRE_NAME`)
values (2, 'Science Fiction');

insert into authors (ID, `FULL_NAME`)
values (1, 'Robert Martin');
insert into authors (ID, `FULL_NAME`)
values (2, 'Joshua Bloch');

insert into comments (id, book_id, comment)
values (default, 1, 'test comment 1');
insert into comments (id, book_id, comment)
values (default, 2, 'test comment 2');

insert into users (id, user_name, user_password, is_enabled, role_id)
values (default, 'user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', true, 1);
insert into users (id, user_name, user_password, is_enabled, role_id)
values (default, 'admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', true, 2);