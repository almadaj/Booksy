CREATE TABLE authors (
    id UUID PRIMARY KEY,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    origin_country VARCHAR(150) NOT NULL,
    birth_year INTEGER NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
