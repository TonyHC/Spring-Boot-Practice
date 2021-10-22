DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name  VARCHAR(250) NOT NULL,
    career     VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career)
VALUES ('Aliko', 'Dangote', 'Billionaire Industrialist'),
       ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
       ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');


INSERT INTO category (description)
VALUES ('American');
INSERT INTO category (description)
VALUES ('Italian');
INSERT INTO category (description)
VALUES ('Mexican');
INSERT INTO category (description)
VALUES ('Fast Food');
INSERT INTO unit_of_measure (description)
VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description)
VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description)
VALUES ('Cup');
INSERT INTO unit_of_measure (description)
VALUES ('Pinch');
INSERT INTO unit_of_measure (description)
VALUES ('Ounce');
INSERT INTO unit_of_measure (description)
VALUES ('Each');
INSERT INTO unit_of_measure (description)
VALUES ('Dash');
INSERT INTO unit_of_measure (description)
VALUES ('Pint');