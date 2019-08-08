
INSERT INTO AUTHORS (name) VALUES ('Lev Tolstoy');
INSERT INTO AUTHORS (name) VALUES ('Harper Lee');
INSERT INTO AUTHORS (name) VALUES ('F. Scott Fitzgerald');
INSERT INTO AUTHORS (name) VALUES ('Gabriel García Márquez');
INSERT INTO AUTHORS (name) VALUES ('Miguel de Cervantes');
INSERT INTO AUTHORS (name) VALUES ('J. D. Salinger');
INSERT INTO AUTHORS (name) VALUES ('James Joyce');


INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('Anna Karenina', 1, 863, '/img/anna.jpg', 1878, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('To Kill a Mockingbird', 2, 225, '/img/mock.jpg', 1960, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('The Great Gatsby', 3, 354, '/img/gatsby.jpeg', 1925, 0);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('One Hundred Years of Solitude', 4, 872, '/img/solitude.jpg', 1967, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('Don Quixote', 5, 374, '/img/don.jpg', 1605, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('War and Peace', 1, 1225, '/img/war.jpg', 1869, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('The Catcher in the Rye', 6, 277, '/img/catcher.jpg', 1951, 50);
INSERT INTO BOOKS (title, author_id, pages,  img_url, year, amount) VALUES ('Ulysses ', 7, 730, '/img/uly.jpg', 1922, 50);



INSERT INTO USERS (id, email, password, phone, username) VALUES (1, '1@1.1', '1', '1', 'admin1');
INSERT INTO USERS (id, email, password, phone, username) VALUES (2, '1@1.1', '1', '1', 'user1');


INSERT INTO USER_ROLE (user_id, role) VALUES (1, 'ADMIN');
INSERT INTO USER_ROLE (user_id, role) VALUES (2, 'USER');
