create database imdb;
create user imdb_user with encrypted password 'imdb';
grant all privileges on database imdb to imdb_user;
