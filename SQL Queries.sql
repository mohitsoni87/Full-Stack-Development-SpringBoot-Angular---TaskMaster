CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    task_name VARCHAR(255),
    user_id INT,
    description TEXT,
    date DATE,
    status VARCHAR(255) DEFAULT 'INPROGRESS'
);

CREATE SEQUENCE task_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255)
);


CREATE SEQUENCE user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
