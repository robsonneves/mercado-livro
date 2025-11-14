CREATE TABLE customer(
	id int auto_increment key,
    name varchar(255) not null,
    email varchar(255) not null unique
)