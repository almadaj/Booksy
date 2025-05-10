CREATE TABLE books (
    id UUID PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    isbn VARCHAR(255) UNIQUE,
    pages_number INTEGER NOT NULL,
    release_date INTEGER NOT NULL,
    author_id UUID,
    upload_id VARCHAR(100),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id)
);
