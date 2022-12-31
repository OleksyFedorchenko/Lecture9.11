drop table if exists city;
drop table if exists country;

create table country(
                        id BIGINT(20) NOT NULL AUTO_INCREMENT,
                        name VARCHAR(15),
                        PRIMARY KEY (id));

create table city(
                     id BIGINT(20) NOT NULL AUTO_INCREMENT,
                     name VARCHAR(15) NOT NULL,
                     population INT NOT NULL,
                     capital BOOLEAN NOT NULL,
                     country_id BIGINT(20) NOT NULL,
                     INDEX c_id(country_id),
                     PRIMARY KEY (id),
                     FOREIGN KEY (country_id)
                         REFERENCES country(id)
                         ON DELETE CASCADE
);