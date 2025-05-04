CREATE TABLE readings (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    current_page INTEGER NOT NULL CHECK (current_page >= 0),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
