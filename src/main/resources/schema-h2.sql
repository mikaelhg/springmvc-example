CREATE MEMORY TABLE examples (
  id IDENTITY PRIMARY KEY,
  name TEXT
);

INSERT INTO examples (name) VALUES ('The Wire'), ('The West Wing'),
    ('The Daily Show'), ('Rent'), ('Rango'), ('Iñtërnâtiônàlizætiøn');
