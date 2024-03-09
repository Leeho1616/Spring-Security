DROP TABLE IF EXISTS items;

CREATE TABLE items ( 
    id              SERIAL          PRIMARY KEY,
    name            VARCHAR(100)    UNIQUE NOT NULL,
    price           INTEGER         NOT NULL,
    stock_quantity  INTEGER         NOT NULL
);