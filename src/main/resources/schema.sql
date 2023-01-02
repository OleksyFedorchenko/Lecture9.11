DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;

CREATE TABLE country(
                        id BIGINT(20) NOT NULL AUTO_INCREMENT,
                        name VARCHAR(15),
                        UNIQUE (name),
                        PRIMARY KEY (id));

CREATE TABLE city(
                     id BIGINT(20) NOT NULL AUTO_INCREMENT,
                     name VARCHAR(15) NOT NULL,
                     population INT NOT NULL,
                     capital BOOLEAN NOT NULL,
                     country_id BIGINT(20) NOT NULL,
                     INDEX c_id(country_id),
                     UNIQUE (name),
                     PRIMARY KEY (id),
                     FOREIGN KEY (country_id)
                         REFERENCES country(id)
                         ON DELETE CASCADE
);