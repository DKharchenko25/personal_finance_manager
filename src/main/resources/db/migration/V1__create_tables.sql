CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS categories (
    id uuid PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    date TIMESTAMP NOT NULL,
    amount DECIMAL NOT NULL,
    category_id uuid NOT NULL REFERENCES categories(id),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS budgets (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL REFERENCES users(id),
    category_id uuid NOT NULL REFERENCES categories(id),
    amount DECIMAL NOT NULL,
    start_date TIMESTAMP NOT NULL CHECK (start_date < end_date),
    end_date TIMESTAMP NOT NULL
);
